package transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    // Текстовий звіт, що показує сумарні витрати по категоріях та по місяцях.
    public static void printCategoryExpensesReport(List<Transaction> transactions) {
        Map<String, Double> categoryExpenses = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                categoryExpenses.merge(transaction.getDescription(), -transaction.getAmount(), Double::sum);
            }
        }

        System.out.println("Звіт витрат по категоріях:");
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();
            System.out.println(category + ": " + amount + " грн " + generateVisualization(amount));
        }
    }

    private static String generateVisualization(double amount) {
        int symbolCount = (int) (amount / 1000); // 1 символ = 1000 грн
        return "*".repeat(Math.max(0, symbolCount)); // генерує рядок з символів '*'
    }
}
