package transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Виклик методу, який потрібно протестувати
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        // Перевірка результату
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions,"02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions,"03-2023");

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testReadTransactionsFromCSV() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv"; // URL вашого CSV файлу
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        // Перевірка, що транзакції не порожні
        Assertions.assertFalse(transactions.isEmpty(), "Транзакції не були прочитані з CSV");
    }

    @Test
    public void testFindTopExpenses() {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", -100.0, "Витрата 1");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата 2");
        Transaction transaction3 = new Transaction("2023-01-03", -150.0, "Витрата 3");
        Transaction transaction4 = new Transaction("2023-01-04", 200.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3, transaction4);

        // Виклик методу для знаходження 10 найбільших витрат
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        // Перевірка, що ми отримали 3 витрати
        Assertions.assertEquals(3, topExpenses.size(), "Кількість витрат не відповідає очікуваній");
        Assertions.assertEquals(-150.0, topExpenses.get(0).getAmount(), "Найбільша витрата неправильна");
    }
}