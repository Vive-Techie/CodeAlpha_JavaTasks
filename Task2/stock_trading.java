import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    Portfolio(double balance) {
        this.balance = balance;
    }

    void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cost > balance) {
            System.out.println("Not enough balance to buy " + quantity + " of " + stock.name);
        } else {
            balance -= cost;
            holdings.put(stock.name, holdings.getOrDefault(stock.name, 0) + quantity);
            System.out.println("Bought " + quantity + " of " + stock.name);
        }
    }

    void sellStock(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.name, 0);
        if (quantity > owned) {
            System.out.println("You don’t own enough shares to sell.");
        } else {
            balance += stock.price * quantity;
            holdings.put(stock.name, owned - quantity);
            System.out.println("Sold " + quantity + " of " + stock.name);
        }
    }

    void showPortfolio() {
        System.out.println("\n--- Portfolio ---");
        System.out.println("Balance: Rs. " + String.format("%.2f", balance));
        if (holdings.isEmpty()) {
            System.out.println("No stocks owned yet.");
        } else {
            for (String stock : holdings.keySet()) {
                System.out.println(stock + ": " + holdings.get(stock) + " shares");
            }
        }
    }
}

public class stock_trading {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stock[] stocks = {
            new Stock("TCS", 3200),
            new Stock("INFY", 1450),
            new Stock("RELIANCE", 2500)
        };

        Portfolio portfolio = new Portfolio(10000); // Starting balance: Rs. 10,000

        while (true) {
            System.out.println("\n--- Stock Trading Simulator ---");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Show Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Stocks:");
                    for (int i = 0; i < stocks.length; i++) {
                        System.out.println((i + 1) + ". " + stocks[i].name + " - Rs. " + stocks[i].price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock number to buy: ");
                    int buyIndex = scanner.nextInt() - 1;
                    System.out.print("Enter quantity: ");
                    int buyQty = scanner.nextInt();
                    if (buyIndex >= 0 && buyIndex < stocks.length) {
                        portfolio.buyStock(stocks[buyIndex], buyQty);
                    } else {
                        System.out.println("Invalid stock.");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock number to sell: ");
                    int sellIndex = scanner.nextInt() - 1;
                    System.out.print("Enter quantity: ");
                    int sellQty = scanner.nextInt();
                    if (sellIndex >= 0 && sellIndex < stocks.length) {
                        portfolio.sellStock(stocks[sellIndex], sellQty);
                    } else {
                        System.out.println("Invalid stock.");
                    }
                    break;

                case 4:
                    portfolio.showPortfolio();
                    break;

                case 5:
                    System.out.println("Exiting simulator. Thank you!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
