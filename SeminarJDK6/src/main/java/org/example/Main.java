package org.example;

import java.util.Random;
import java.util.HashMap;

class MontyHallParadox {
    public static void main(String[] args) {
        Random random = new Random();
        int numberOfDoors = 3; // Количество дверей

        // Генерация случайного номера двери, за которой находится автомобиль
        int carDoor = random.nextInt(numberOfDoors);

        // Ваш первоначальный выбор
        int initialChoice = random.nextInt(numberOfDoors);

        // Ведущий открывает одну из оставшихся дверей с козой
        int openedDoor;
        do {
            openedDoor = random.nextInt(numberOfDoors);
        } while (openedDoor == carDoor || openedDoor == initialChoice);

        // Расчет вероятностей
        int switchChoiceWins = 0;
        int stayChoiceWins = 0;

        // HashMap для хранения результатов
        HashMap<Integer, String> results = new HashMap<>();

        // Проводим эксперименты много раз и считаем вероятности
        int trials = 1000;
        for (int i = 0; i < trials; i++) {
            // Ваш первоначальный выбор
            int choice = random.nextInt(numberOfDoors);

            // Ведущий открывает одну из оставшихся дверей с козой
            int opened;
            do {
                opened = random.nextInt(numberOfDoors);
            } while (opened == carDoor || opened == choice);

            // Выбор после изменения выбора
            int switchDoor;
            do {
                switchDoor = random.nextInt(numberOfDoors);
            } while (switchDoor == opened || switchDoor == choice);

            // Проверяем, была ли выбрана дверь с автомобилем
            if (switchDoor == carDoor) {
                switchChoiceWins++;
                results.put(i, "Win");
            } else if (choice == carDoor) {
                stayChoiceWins++;
                results.put(i, "Loss");
            }
        }

        // Вывод статистики по победам и поражениям
        int totalWins = switchChoiceWins + stayChoiceWins;
        int totalLosses = trials - totalWins;

        System.out.println("Статистика:");
        System.out.println("Победы при смене выбора: " + switchChoiceWins);
        System.out.println("Поражения при смене выбора: " + (totalWins - switchChoiceWins));
        System.out.println("Победы при оставании на первоначальном выборе: " + stayChoiceWins);
        System.out.println("Поражения при оставании на первоначальном выборе: " + (totalLosses - stayChoiceWins));
        System.out.println("Общее количество побед: " + totalWins);
        System.out.println("Общее количество поражений: " + totalLosses);
        System.out.println("Процент побед: " + ((double) totalWins / trials) * 100 + "%");

        // Вывод результатов для каждого шага теста
        System.out.println("\nРезультаты по каждому шагу теста:");
        for (int step : results.keySet()) {
            System.out.println("Шаг " + (step + 1) + ": " + results.get(step));
        }
    }
}
