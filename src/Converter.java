public class Converter {
    static double converStepsToDistance(int step) {
        return step * 75 / 1000;
    }

    static double converStepsToKilocalories(int step) {
        return step * 50 / 1000;
    }
}