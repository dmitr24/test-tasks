package siberteam.testperiod.mt2;

import org.junit.jupiter.api.*;
import siberteam.testperiod.mt2.stub.ProducerStub;
import siberteam.testperiod.mt2.third.Consumer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProducerConsumerTests {
    private ProducerStub[] producers;
    private ArrayBlockingQueue<String> queue;

    @BeforeAll
    void loadProducers() {
        queue = new ArrayBlockingQueue<>(10000);
        producers = new ProducerStub[10];
        for (int i = 0; i < 10; i++) {
            producers[i] = new ProducerStub((i + 1) * 1000, queue);
        }
    }

    @Test
    @DisplayName("Consumer wait for poison pill")
    void consumerWaitsForSignalTest() {
        Consumer consumer = new Consumer(queue);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future consumerFuture = executorService.submit(consumer::consume);
        try {
            Thread.sleep(100);
            Assertions.assertFalse(consumerFuture.isDone());
            Thread.sleep(100);
            Assertions.assertFalse(consumerFuture.isDone());
            queue.put("\0");
            Thread.sleep(100);
            Assertions.assertTrue(consumerFuture.isDone());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Consumer doesn't write poison pill to dictionary")
    void consumerDontWriteEmptyWordToDictionaryTest() {
        Consumer consumer = new Consumer(queue);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(consumer::consume);
        try {
            queue.put("\0");
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(consumer.getDictionary().isEmpty());
    }

    @Test
    @DisplayName("Consumer doesn't have dictionary inconsistency with high load")
    void consumerHaveConsistentDictionaryWithHighLoadTest() {
        Consumer consumer = new Consumer(queue);
        ExecutorService consumerExecutor = Executors.newSingleThreadExecutor();
        Future consumerAwaiter = consumerExecutor.submit(consumer::consume);
        ExecutorService producerExecutor = Executors.newCachedThreadPool();
        List<Future> producersAwaiters = new ArrayList<>();
        for (ProducerStub producer : producers) {
            producersAwaiters.add(producerExecutor.submit(producer::produce));
        }
        Set<String> predictedResult = new HashSet<>();
        for (int i = 0; i < 10_000; i++) {
            predictedResult.add("test " + i);
        }

        producersAwaiters.forEach(producersAwaiter -> {
            try {
                producersAwaiter.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            queue.put("\0");
            consumerAwaiter.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        Set<String> result = consumer.getDictionary();

        Assertions.assertTrue(result.containsAll(predictedResult));
        Assertions.assertTrue(predictedResult.containsAll(result));
    }
}
