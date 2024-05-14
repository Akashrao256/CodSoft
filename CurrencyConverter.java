import java.util.Scanner;
import java.io.IOException;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the base currency: ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = 0;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return;
        }

        System.out.print("Enter the target currency: ");
        String targetCurrency = scanner.nextLine().toUpperCase();
        double convertedAmount = convertCurrency(baseCurrency, amount, targetCurrency);
        if (convertedAmount == -1) {
            System.out.println("Invalid base or target currency.");
            return;
        }

        System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
    }

    private static double convertCurrency(String baseCurrency, double amount, String targetCurrency) {
        ExchangeRatesAPI api = new ExchangeRatesAPI();
        try {
            double rate = api.getExchangeRate(baseCurrency, targetCurrency);
            if (rate == -1) {
                return -1;
            }
            return amount * rate;
        } catch (IOException e) {
            System.out.println("Error fetching exchange rates. Please try again later.");
            return -1;
        }
    }
}
