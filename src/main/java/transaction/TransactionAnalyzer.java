package transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {
    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Метод для підрахунку транзакцій за конкретний місяць і рік
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return (int) transactions.stream()
                .filter(transaction -> {
                    LocalDate date = LocalDate.parse(transaction.getDate(), formatter);
                    return date.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear);
                })
                .count();
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }

    // Додати функцію для визначення найбільших витрат за вказаний період.
    public static Transaction findLargestExpense(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    // Додати функцію для визначення найменших витрат за вказаний період.
    public static Transaction findSmallestExpense(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }
}
