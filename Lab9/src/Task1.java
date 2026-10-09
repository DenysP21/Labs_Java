import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Account {
    private final int id;
    private int balance;

    public Account(int id, int initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}

class Bank {
    public void transfer(Account from, Account to, int amount) {
        if (from.getId() == to.getId()) {
            return;
        }

        Account firstLock = from.getId() < to.getId() ? from : to;
        Account secondLock = from.getId() < to.getId() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }
}

public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        int numberOfAccounts = 100;
        int numberOfThreads = 5000;
        Random random = new Random();
        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();

        for (int i = 0; i < numberOfAccounts; i++) {
            accounts.add(new Account(i, 1000 + random.nextInt(4001)));
        }

        long initialTotalBalance = 0;
        for (Account acc : accounts) {
            initialTotalBalance += acc.getBalance();
        }
        System.out.println("Сума на рахунку ДО переказів: " + initialTotalBalance);

        ExecutorService executor = Executors.newFixedThreadPool(50);

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                Account from = accounts.get(random.nextInt(numberOfAccounts));
                Account to = accounts.get(random.nextInt(numberOfAccounts));
                int amount = random.nextInt(500);

                bank.transfer(from, to, amount);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long finalTotalBalance = 0;
        for (Account acc : accounts) {
            finalTotalBalance += acc.getBalance();
        }

        System.out.println("Сума на рахунку ПІСЛЯ переказів: " + finalTotalBalance);

        if (initialTotalBalance == finalTotalBalance) {
            System.out.println("Успіх! Баланс однаковий.");
        } else {
            System.out.println("Помилка синхронізації! Баланс не сходиться.");
        }
    }
}