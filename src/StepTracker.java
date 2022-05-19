import java.util.Scanner;

public class StepTracker {

    int targetStepsCount;
    Scanner scanner = new Scanner(System.in);
    MonthData[] monthToData;

    //Конструктор
    StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
        targetStepsCount = 10000;
    }

    void changeTargetStepsCount() {
        System.out.println("Введите новое значение цели по количеству шагов в день:");
        int count = scanner.nextInt();
        if (count >= 0) {
            targetStepsCount = count;
            System.out.println("Успех! Новая цель по количеству шагов в день: " + targetStepsCount);
        } else {
            System.out.println("Ошибка! Целевое количество шагов не может быть отрицательным.");
        }

    }

    void enterStepsCountPerDay() {
        System.out.println("Введите порядковый номер месяца, за который хотите ввести количество шагов:");
        int month = scanner.nextInt();
        if (month < 1 || month > 12) {
            System.out.println("Ошибка! Введенное значение находится вне диапазона 1 ... 12");
        } else {
            System.out.println("Введите порядковый номер дня, за который хотите ввести количество шагов:");
            int day = scanner.nextInt();
            if (day < 1 || day > 30) {
                System.out.println("Ошибка! Введенное значение находится вне диапазона 1 ... 30");
            } else {
                System.out.println("Введите количество пройденных шагов:");
                int steps = scanner.nextInt();
                if (steps < 0) {
                    System.out.println("Ошибка! Введенное значение меньше 0");
                } else {
                    monthToData[month - 1].numberOfSteps[day - 1] = steps;
                    System.out.println("Успех! Количество шагов за " + day + " " + getMonthName(month) + ": " + monthToData[month - 1].numberOfSteps[day - 1]);
                }
            }
        }
    }

    String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case (1):
                monthName = "Января";
                break;
            case (2):
                monthName = "Февраля";
                break;
            case (3):
                monthName = "Марта";
                break;
            case (4):
                monthName = "Апреля";
                break;
            case (5):
                monthName = "Мая";
                break;
            case (6):
                monthName = "Июня";
                break;
            case (7):
                monthName = "Июля";
                break;
            case (8):
                monthName = "Августа";
                break;
            case (9):
                monthName = "Сентября";
                break;
            case (10):
                monthName = "Октября";
                break;
            case (11):
                monthName = "Ноябра";
                break;
            case (12):
                monthName = "Декабря";
                break;
        }
        return monthName;
    }

    int getMaxSteps(int month) {
        int maxSteps = 0;
        for (int i = 0; i < monthToData[month - 1].numberOfSteps.length; i++) {
            if (maxSteps < monthToData[month - 1].numberOfSteps[i]) {
                maxSteps = monthToData[month - 1].numberOfSteps[i];
            }
        }
        return maxSteps;
    }

    double getAverageSteps(int month) {
        int stepsCount = 0;
        for (int i = 0; i < monthToData[month - 1].numberOfSteps.length; i++) {
            stepsCount = stepsCount + monthToData[month - 1].numberOfSteps[i];
        }
        return stepsCount / monthToData[month - 1].numberOfSteps.length;
    }

    int getSumSteps(int month) {
        int sumSteps = 0;
        for (int i = 0; i < monthToData[month - 1].numberOfSteps.length; i++) {
            sumSteps = sumSteps + monthToData[month - 1].numberOfSteps[i];
        }
        return sumSteps;
    }

    int getBestSeries(int month) {
        int bestSeriesLocal = 0;
        int bestSeries = 0;
        for (int i = 0; i < monthToData[month - 1].numberOfSteps.length; i++) {
            if (monthToData[month - 1].numberOfSteps[i] >= targetStepsCount) {
                bestSeriesLocal = bestSeriesLocal + 1;
            } else {
                if (bestSeries < bestSeriesLocal) {
                    bestSeries = bestSeriesLocal;
                    bestSeriesLocal = 0;
                } else {
                    bestSeriesLocal = 0;
                }

            }
        }
        return bestSeries;
    }

    void printStatistics() {
        String steps = "";
        System.out.println("Введите порядковый номер месяца, за который хотите увидеть статистику:");
        int month = scanner.nextInt();
        if (month < 0 || month > 12) {
            System.out.println("Ошибка! Введенное значение находится вне диапазона 1 ... 12");
        } else {
            for (int i = 0; i < monthToData[month - 1].numberOfSteps.length; i++) {
                steps = steps + (i + 1) + " день: " + monthToData[month - 1].numberOfSteps[i];
                if (i < monthToData[month - 1].numberOfSteps.length - 1) {
                    steps = steps + ", ";
                }
            }
            int stepsPerMonth = getSumSteps(month);
            System.out.println("Статистика за " + getMonthName(month) + ":");
            System.out.println(steps);
            System.out.println("Общее количество шагов за месяц: " + stepsPerMonth);
            System.out.println("Максимальное пройденное количество шагов в день за месяц: " + getMaxSteps(month));
            System.out.println("Среднее количество шагов: " + getAverageSteps(month));
            System.out.println("Пройденная дистанция (в км): " + Converter.convertStepsToDistance(stepsPerMonth));
            System.out.println("Количество сожжённых килокалорий: " + Converter.convertStepsToKilocalories(stepsPerMonth));
            System.out.println("Лучшая серия: " + getBestSeries(month));
        }

    }

    static class MonthData {
        int[] numberOfSteps;

        //Конструктор
        MonthData() {
            numberOfSteps = new int[30];
        }
    }
}