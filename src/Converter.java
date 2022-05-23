public class Converter {
    static double convertStepsToDistance(int step) {
        final double kilometersInStep = 0.00075;
        return step * kilometersInStep;
    }

    static double convertStepsToKilocalories(int step) {
        final double kilocaloriesInStep = 0.05;
        return step * kilocaloriesInStep;
    }
}