package siberteam.testperiod.mt2.third;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.TransferQueue;

public class Producer {
    private final TransferQueue<String> queue;
    private final String path;

    public Producer(String path, TransferQueue<String> queue) {
        this.path = path;
        this.queue = queue;
    }

    public void produce() {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {
            int newChar = reader.read();
            StringBuilder wordBuilder = new StringBuilder();
            while (newChar != -1) {
                if ((newChar >= 'а' && newChar <= 'я') ||
                        (newChar >= 'А' && newChar <= 'Я')) {
                    wordBuilder.append((char) newChar);
                } else {
                    if (wordBuilder.length() >= 3) {
                        String word = wordBuilder.toString();
                        queue.transfer(word);
                    }
                    wordBuilder = new StringBuilder();
                }
                newChar =  reader.read();
            }
            if (wordBuilder.length() >= 3) {
                queue.transfer(wordBuilder.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading from file: " + path);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
