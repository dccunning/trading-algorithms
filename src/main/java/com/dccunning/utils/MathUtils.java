package com.dccunning.utils;


/**
 * Utility class to calculate low level mathematical functions.
 */
public class MathUtils {

    /** Calculate the mean of an array of numbers.
     *
     * @param data the values to average.
     * @return the average or {@code Double.NaN} if the data is empty.
     */
    public static double mean(double[] data) {
        if (data.length == 0) return Double.NaN;
        double sum = 0;
        for (double d : data) sum += d;
        return sum / data.length;
    }

    /** Calculate the standard deviation of an array of numbers. √(((x1-mean)^2 + (x2-mean)^2 + ... (xn-mean)^2)/n)
     *
     * @param data the values to calculate stdDev for
     * @return the standard deviation or {@code Double.NaN} if the data is empty.
     */
    public static double stdDev(double[] data) {
        if (data.length == 0) return Double.NaN;
        double mean = mean(data);
        double sum = 0;
        for (double d : data) sum += Math.pow(d - mean, 2);
        return Math.sqrt(sum / data.length);
    }

    public static double movingAverage(double[] data, int period, int endIndex) {
        if (endIndex < period - 1) return Double.NaN;
        double sum = 0;
        for (int i = endIndex - period + 1; i <= endIndex; i++) {
            sum += data[i];
        }
        return sum / period;
    }

    public static double max(double[] data) {
        if (data.length == 0) return Double.NaN;
        double max = data[0];
        for (double d : data) max = Math.max(max, d);
        return max;
    }

    public static double min(double[] data) {
        if (data.length == 0) return Double.NaN;
        double min = data[0];
        for (double d : data) min = Math.min(min, d);
        return min;
    }

    public static double zScore(double value, double mean, double stdDev) {
        if (stdDev == 0.0) return Double.NaN;
        return (value - mean) / stdDev;
    }
}
