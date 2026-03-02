import java.util.ArrayList;

public class StockPriceAnalysis {

    public static double calculateAveragePrice(int[] stockPrices) {
        int sum = 0;
        for (int price : stockPrices) {
            sum += price;
        }
        return (double) sum / stockPrices.length;
    }

    public static int findMaximumPrice(int[] stockPrices) {
        int maxPrice = stockPrices[0];
        for (int i = 1; i < stockPrices.length; i++) {
            if (stockPrices[i] > maxPrice) {
                maxPrice = stockPrices[i];
            }
        }
        return maxPrice;
    }

    public static int countOccurrences(int[] stockPrices, int targetPrice) {
        int count = 0;
        for (int price : stockPrices) {
            if (price == targetPrice) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<Integer> computeCumulativeSum(ArrayList<Integer> stockPriceList) {
        ArrayList<Integer> cumulativeSumList = new ArrayList<>();
        int runningSum = 0;
        for (int price : stockPriceList) {
            runningSum += price;
            cumulativeSumList.add(runningSum);
        }
        return cumulativeSumList;
    }

    public static void main(String[] args) {
        int[] stockPricesArray = {100, 102, 101, 105, 107, 103, 104, 106, 108, 110};

        ArrayList<Integer> stockPricesList = new ArrayList<>();
        for (int price : stockPricesArray) {
            stockPricesList.add(price);
        }

        double averagePrice = calculateAveragePrice(stockPricesArray);
        int maximumPrice = findMaximumPrice(stockPricesArray);
        int targetPrice = 105;
        int occurrenceCount = countOccurrences(stockPricesArray, targetPrice);
        ArrayList<Integer> cumulativeSumList = computeCumulativeSum(stockPricesList);

        System.out.println("Average stock price: " + averagePrice);
        System.out.println("Maximum stock price: " + maximumPrice);
        System.out.println("Target price: " + targetPrice + ", Occurrence count: " + occurrenceCount);
        System.out.println("Cumulative sum of stock prices:");
        for (int i = 0; i < cumulativeSumList.size(); i++) {
            System.out.println("Day " + (i + 1) + ": " + cumulativeSumList.get(i));
        }
    }
}

