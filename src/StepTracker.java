import java.util.Scanner;

public class StepTracker {

    static int targetStepsCount;
    static Scanner scanner = new Scanner(System.in);
    static MonthData[] monthToData;

    //Конструктор
    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
        targetStepsCount = 10000;
    }

    static void changeTargetStepsCount() {
        System.out.println("Введите новое значение цели по количеству шагов в день:");
        int count = scanner.nextInt();
        if (count >= 0) {
            targetStepsCount = count;
            System.out.println("Успех! Новая цель по количеству шагов в день: " + targetStepsCount);
        } else {
            System.out.println("Ошибка! Целевое количество шагов не может быть отрицательным.");
        }

    }

    static void enterStepsCountPerDay() {
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
                    monthToData[month - 1].steps = monthToData[month - 1].steps + steps;
                    monthToData[month - 1].distance = monthToData[month - 1].distance + Converter.converStepsToDistance(steps);
                    monthToData[month - 1].kilocalories = monthToData[month - 1].kilocalories + Converter.converStepsToKilocalories(steps);
                    monthToData[month - 1].maxSteps = getMaxSteps(month);
                    monthToData[month - 1].averageSteps = getAverageSteps(month);
                    monthToData[month - 1].bestSeries = getBestSeries(month);
                    System.out.println("Количество шагов за " + day + " " + getMonthName(month));
                }
            }
        }
    }

    static String getMonthName(int month) {
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

    static int getMaxSteps(int month) {
        int maxSteps = 0;
        for (int i = 0; i < monthToData[month - 1].numberOfSteps.length; i++) {
            if (maxSteps < monthToData[month - 1].numberOfSteps[i]) {
                maxSteps = monthToData[month - 1].numberOfSteps[i];
            }
        }
        return maxSteps;
    }

    static double getAverageSteps(int month) {
        int stepsCount = 0;
        for (int i = 0; i < monthToData[month - 1].numberOfSteps.length; i++) {
            stepsCount = stepsCount + monthToData[month - 1].numberOfSteps[i];
        }
        return stepsCount / monthToData[month - 1].numberOfSteps.length;
    }

    static int getBestSeries(int month) {
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

    static void printStatistics() {
        String steps = "";
        System.out.println("Введите порядковый номер месяца, за который хотите ввести количество шагов:");
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
            System.out.println("Статистика за " + getMonthName(month) + ":");
            System.out.println(steps);
            System.out.println("Общее количество шагов за месяц: " + monthToData[month - 1].steps);
            System.out.println("Максимальное пройденное количество шагов за месяц: " + monthToData[month - 1].maxSteps);
            System.out.println("Среднее количество шагов: " + monthToData[month - 1].averageSteps);
            System.out.println("Пройденная дистанция (в км): " + monthToData[month - 1].distance);
            System.out.println("Количество сожжённых килокалорий: " + monthToData[month - 1].kilocalories);
            System.out.println("Лучшая серия: " + monthToData[month - 1].bestSeries);
        }

    }

    class MonthData {
        int[] numberOfSteps;
        int steps;
        int maxSteps;
        double averageSteps;
        double distance;
        double kilocalories;
        int bestSeries;

        //Конструктор
        public MonthData() {
            numberOfSteps = new int[30];
            steps = 0;
            maxSteps = 0;
            averageSteps = 0;
            distance = 0;
            kilocalories = 0;
            bestSeries = 0;
        }
    }
}