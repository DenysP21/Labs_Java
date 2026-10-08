import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSum {
    static class ArraySumTask extends RecursiveTask<Long> {
        private final int[] array;
        private final int start;
        private final int end;

        private static final int THRESHOLD = 20;

        public ArraySumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start < THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int mid = start + (end - start) / 2;

                ArraySumTask leftTask = new ArraySumTask(array, start, mid);
                ArraySumTask rightTask = new ArraySumTask(array, mid, end);

                leftTask.fork();

                long rightResult = rightTask.compute();

                long leftResult = leftTask.join();

                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        int arraySize = 1_000_000;
        int[] array = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(101);
        }

        ForkJoinPool pool = new ForkJoinPool();

        ArraySumTask rootTask = new ArraySumTask(array, 0, array.length);

        System.out.println("Обчислення суми масиву з " + arraySize + " елементів...");
        long startTime = System.currentTimeMillis();

        long totalSum = pool.invoke(rootTask);

        long endTime = System.currentTimeMillis();

        System.out.println("Загальна сума: " + totalSum);
        System.out.println("Час виконання: " + (endTime - startTime) + " мс");
    }
}