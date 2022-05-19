public class Converter {
    static double convertStepsToDistance(int step) {
        return step * 75 / 100000;
    }

    static double convertStepsToKilocalories(int step) {
        return step * 50 / 1000;
    }
}