package siberteam.testperiod.mt2.stub;

import lombok.RequiredArgsConstructor;
import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
public class ProducerStub {
    private final Integer count;
    private final BlockingQueue<String> queue;

    public void produce() {
        for (int i = 0; i < count; i++) {
            try {
                queue.put("test " + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
