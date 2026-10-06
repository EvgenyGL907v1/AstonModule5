package carcounter;

import car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarCounter {
    public static int countMatches(List<Car> cars, Car sample) {
        if (cars == null || cars.isEmpty()) {
            return 0;
        }

        int threadsCount = Runtime.getRuntime().availableProcessors();
        if (threadsCount > cars.size()) {
            threadsCount = cars.size();
        }

        // Общий разделяемый ресурс для подсчёта
        final int[] counter = {0};

        // Разбиваем список на части
        int chunkSize = (cars.size() + threadsCount - 1) / threadsCount;

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            int from = i * chunkSize;
            int to = Math.min(from + chunkSize, cars.size());

            if (from >= to) {
                break;
            }

            List<Car> part = cars.subList(from, to);

            Thread thread = new Thread(new CarSearcher(part, sample, counter));
            threads.add(thread);
            thread.start();
        }

        // Ждём завершения всех потоков
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Поток был прерван", e);
            }
        }

        return counter[0];
    }

    private static class CarSearcher implements Runnable {
        private final List<Car> cars;
        private final Car sample;
        private final int[] counter;

        CarSearcher(List<Car> cars, Car sample, int[] counter) {
            this.cars = cars;
            this.sample = sample;
            this.counter = counter;
        }

        @Override
        public void run() {
            int localCount = 0;
            for (Car car : cars) {
                if (matches(car, sample)) {
                    localCount++;
                }
            }
            if (localCount > 0) {
                synchronized (counter) {
                    counter[0] += localCount;
                }
            }
        }

        private boolean matches(Car a, Car b) {
            return a.getPower() == b.getPower()
                    && a.getYear() == b.getYear()
                    && a.getModel().equals(b.getModel());
        }
    }
}
