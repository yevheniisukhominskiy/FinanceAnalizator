import transaction.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        System.out.println();

        // Знайти найбільші та найменші витрати
        Transaction largestExpense = TransactionAnalyzer.findLargestExpense(transactions);
        Transaction smallestExpense = TransactionAnalyzer.findSmallestExpense(transactions);
        System.out.println("Найбільша витрата: " + largestExpense.getDescription() + " - " + largestExpense.getAmount() + " грн");
        System.out.println("Найменша витрата: " + smallestExpense.getDescription() + " - " + smallestExpense.getAmount() + " грн");

        System.out.println();

        // Звіт по категоріях
        TransactionReportGenerator.printCategoryExpensesReport(transactions);
    }
}
