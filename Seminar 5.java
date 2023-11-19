/*
Для решения проблемы представим каждого филоссофа отдельным потоком
В этом примере вилки представлены в виде блокировок (`Lock`), используя `ReentrantLock`
для синхронизации доступа к ресурсам. Каждый философ пытается захватить обе вилки с
помощью метода `tryLock`, который не блокирует поток, если вилка уже занята.
Если философ успешно взял обе вилки, он ест и затем освобождает вилки.
Философ повторяет этот процесс до тех пор, пока не поест три раза.

Последний философ берет вилки в обратном порядке, чтобы избежать взаимной блокировки (deadlock),
когда все философы берут левую вилку и ждут правую.
*/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    private static final int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        Lock[] forks = new ReentrantLock[NUM_PHILOSOPHERS];

        // Инициализация вилок
        for (int i = 0; i < NUM_PHILOSOPHERS; ++i) {
            forks[i] = new ReentrantLock();
        }

        // Инициализация философов
        for (int i = 0; i < NUM_PHILOSOPHERS; ++i) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % NUM_PHILOSOPHERS];

            // Последний философ меняет порядок взятия вилок, чтобы избежать взаимной блокировки
            if (i == NUM_PHILOSOPHERS - 1) {
                philosophers[i] = new Philosopher(rightFork, leftFork, i);
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork, i);
            }

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}

class Philosopher implements Runnable {
    private final Lock leftFork;
    private final Lock rightFork;
    private final int id;
    private int mealsEaten = 0;

    public Philosopher(Lock leftFork, Lock rightFork, int id) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (mealsEaten < 3) {
                // Размышление
                think();

                // Попытка взять обе вилки
                if (leftFork.tryLock()) {
                    try {
                        if (rightFork.tryLock()) {
                            try {
                                // Поесть
                                eat();
                            } finally {
                                rightFork.unlock();
                            }
                        }
                    } finally {
                        leftFork.unlock();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep(((int) (Math.random() * 1000)));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        mealsEaten++;
        Thread.sleep(((int) (Math.random() * 1000)));
    }
}
