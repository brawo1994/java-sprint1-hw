import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        while (true) {

            printMenu();
            int command = scanner.nextInt();

            switch (command) {
                case (1):
                    stepTracker.enterStepsCountPerDay();
                    break;
                case (2):
                    stepTracker.printStatistics();
                    break;
                case (3):
                    stepTracker.changeTargetStepsCount();
                    break;
                case (0):
                    System.out.println("Выход");
                    scanner.close();
                    return;
                default:
                    System.out.println("Извините, такой команды пока нет.");
                    break;
            }
        }

    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выйти из приложения");
    }
}
