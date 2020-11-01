import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountHolder accountHolder = new AccountHolder();
        Scanner scanner = new Scanner(System.in);
        new Menu().
                withExitOnZero().
                withMenuItem(1, "Add RUB account",  ()-> accountHolder.addNewAccount(new RubAccount())).
                withMenuItem(2, "Add cash account", ()->
                {
                    System.out.print("\nInput cash currency: ");
                    String currency = scanner.nextLine();
                    System.out.print("\nInput course cash to RUB: ");
                    try{
                        BigDecimal multiplier = scanner.nextBigDecimal();
                        scanner.nextLine();
                        accountHolder.addNewAccount(new CashAccount(currency, multiplier));
                    }catch (Exception e){
                        System.out.println("\nAdding cash account failed");
                    }
                }).
                withMenuItem(3, "Remove account by ID", ()->
                {
                    System.out.print("\nInput ID: ");
                    try{
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        accountHolder.removeAccountById(id);
                    }catch (Exception e){ System.out.println("\nRemove by ID failed."); }
                }).
                withMenuItem(4, "Show balance by ID", ()->
                {
                    System.out.println("Input id: ");
                    try{
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        accountHolder.showBalanceById(id);
                    }catch (Exception e){
                        System.out.println("\nIllegal ID.");
                    }
                }).
                withMenuItem(5, "Show full balance by RUB", accountHolder::showFullBalanceByRUB).
                withMenuItem(6, "Show all accounts", accountHolder::showAllAccounts).
                withMenuItem(7, "Withdraw by ID", ()->
                {
                    int id = 0;
                    System.out.print("\nInput ID: ");
                    try{
                        id = scanner.nextInt();
                        scanner.nextLine();
                    }catch (Exception e){
                        System.out.println("\nIllegal ID.");
                    }
                    System.out.println("\nInput amount of currency: ");
                    try {
                        BigDecimal amountWithdraw = scanner.nextBigDecimal();
                        scanner.nextLine();
                        accountHolder.withdrawById(id, amountWithdraw);
                    }catch (Exception e){
                        System.out.println("Illegal amount");
                    }
                }).
                withMenuItem(8, "Deposit by ID", ()->
                {
                    int id = 0;
                    System.out.print("\n\nInput ID: ");
                    try{
                        id = scanner.nextInt();
                        scanner.nextLine();
                    }catch (Exception e){
                        System.out.println("\nIllegal ID.");
                    }
                    System.out.println("\nInput amount of currency: ");
                    try {
                        BigDecimal amountDeposit = scanner.nextBigDecimal();
                        scanner.nextLine();
                        accountHolder.depositById(id, amountDeposit);
                    }catch (Exception e){
                        System.out.println("Illegal amount");
                    }
                }).
                withMenuItem(9, "Transfer currency", ()->
                {
                    BigDecimal amountTransfer;
                    int idFrom = 0;
                    int idTo = 0;
                    System.out.print("\n\nInput ID transfer from : ");
                    try{
                        idFrom = scanner.nextInt();
                        scanner.nextLine();
                    }catch (Exception e){
                        System.out.println("\nIllegal ID transfer from.");
                    }
                    System.out.print("\n\nInput ID transfer to : ");
                    try{
                        idTo = scanner.nextInt();
                        scanner.nextLine();
                    }catch (Exception e){
                        System.out.println("\nIllegal ID transfer to.");
                    }
                    System.out.println("\nInput amount of currency for transfer: ");
                    try {
                        if(!scanner.hasNextBigDecimal()) throw new Exception("Нет числа");
                        amountTransfer = scanner.nextBigDecimal();
                        scanner.nextLine();
                        accountHolder.transfer(amountTransfer, idFrom, idTo);
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Illegal amount");
                    }
                }).withMenuItem(10, "Save data", ()->accountHolder
                    .save("D:\\JavaProg\\TMPLab\\personalAccount\\src\\main\\Saves\\save.txt")
                ).withMenuItem(11, "Load data", ()-> accountHolder
                    .load("D:\\JavaProg\\TMPLab\\personalAccount\\src\\main\\Saves\\save.txt")).
                runInfinityLoop();
    }
}
