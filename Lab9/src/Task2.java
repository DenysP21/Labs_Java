class CircularBuffer {
    private final String[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    public CircularBuffer(int size) {
        this.buffer = new String[size];
    }

    public synchronized void put(String item) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }

        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;

        notifyAll();
    }

    public synchronized String get() throws InterruptedException {
        while (count == 0) {
            wait();
        }

        String item = buffer[head];
        head = (head + 1) % buffer.length;
        count--;

        notifyAll();

        return item;
    }
}

public class Task2 {
    private static final int BUFFER_SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        CircularBuffer buffer1 = new CircularBuffer(BUFFER_SIZE);
        CircularBuffer buffer2 = new CircularBuffer(BUFFER_SIZE);

        for (int i = 1; i <= 5; i++) {
            final int threadId = i;
            Thread producer = new Thread(() -> {
                int messageNumber = 1;
                try {
                    while (true) {
                        String message = "Потік №" + threadId + " згенерував повідомлення " + messageNumber++;
                        buffer1.put(message);
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            producer.setDaemon(true);
            producer.start();
        }

        for (int i = 1; i <= 2; i++) {
            final int threadId = i;
            Thread translator = new Thread(() -> {
                try {
                    while (true) {
                        String originalMessage = buffer1.get();
                        String translatedMessage = "Потік №" + threadId + " переклав повідомлення [" + originalMessage + "]";
                        buffer2.put(translatedMessage);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            translator.setDaemon(true);
            translator.start();
        }

        for (int i = 1; i <= 100; i++) {
            String finalMessage = buffer2.get();
            System.out.println(i + ": " + finalMessage);
        }
    }
}