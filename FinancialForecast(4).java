import java.util.Scanner;

public class FinancialForecast {

    public static void main(String[] args) {

        // sample revenue data for 12 months, just made up numbers
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        double[] revenue = {120.5, 132.0, 128.4, 145.2, 150.8, 142.6,
                             158.3, 165.0, 160.7, 172.4, 180.1, 175.9};

        System.out.println("Monthly revenue data:");
        for (int i = 0; i < revenue.length; i++) {
            System.out.println(months[i] + " -> " + revenue[i]);
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter window size for moving average (try 3): ");
        int window = sc.nextInt();

        System.out.print("How many months do you want to predict? ");
        int monthsToPredict = sc.nextInt();

        // we will store the old data + the predicted values in one bigger array
        double[] allData = new double[revenue.length + monthsToPredict];

        // copy the original revenue into the new array first
        for (int i = 0; i < revenue.length; i++) {
            allData[i] = revenue[i];
        }

        System.out.println("\nPredicted revenue:");

        int currentSize = revenue.length;

        for (int m = 0; m < monthsToPredict; m++) {
            double average = getAverage(allData, currentSize, window);
            allData[currentSize] = average;
            currentSize++;

            System.out.println("Month +" + (m + 1) + " prediction: " + round(average));
        }

        // check how accurate this method would have been on the real past data
        double totalError = 0;
        int count = 0;

        for (int i = window; i < revenue.length; i++) {
            double predicted = getAverage(revenue, i, window);
            double actual = revenue[i];
            double error = Math.abs(actual - predicted);
            totalError = totalError + error;
            count++;
        }

        double averageError = totalError / count;
        System.out.println("\nAverage error when tested on past months: " + round(averageError));
        System.out.println("(smaller number means the prediction was closer to the real value)");

        sc.close();
    }

    // this just adds up the last "window" numbers before position "size" and divides by window
    public static double getAverage(double[] data, int size, int window) {
        if (window > size) {
            window = size;
        }

        double sum = 0;
        for (int i = size - window; i < size; i++) {
            sum = sum + data[i];
        }

        return sum / window;
    }

    // rounds to 2 decimal places so the output looks clean
    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}