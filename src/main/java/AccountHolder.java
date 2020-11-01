import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.*;

public class AccountHolder {
    List<Account> accountList;

    AccountHolder(){
        accountList = new LinkedList<>();
    }
    AccountHolder(List<Account> accountList){
        this.accountList = accountList;
    }

    public void addNewAccount(@NotNull Account newAccount){
        try{
            this.accountList.add(newAccount);
            System.out.println("Adding new account successful.");
        }catch (Exception e){
            System.out.println("Adding new account deny.");
        }
    }
    public void removeAccount(@NotNull Account accountForRemove){
        try{
            this.accountList.remove(accountForRemove);
            System.out.println("Removing account successful.");
        }catch (Exception e){
            System.out.println("Removing account deny.");
        }
    }
    public void removeAccountById(int id){
        removeAccount(accountList.get(id));
    }
    public void showBalanceById(int id){
        try {
            accountList.get(id).showInfo();
        }catch (Exception e){
            System.out.println("\nIllegal ID");
        }
    }
    public void showFullBalanceByRUB(){
        BigDecimal fullBalance = new BigDecimal("0");
        for (Account account : accountList) {
            fullBalance = fullBalance.add(account.balance.divide(account.multiplier, 0));
        }
        System.out.printf("\nFull balance = %f RUB", fullBalance);
    }
    public void showAllAccounts(){
        System.out.print("\n\nAll accounts:");
        for(int i = 0; i < accountList.size(); ++i){
            System.out.printf("\nID: %d\t", i);
            accountList.get(i).showInfo();
        }
    }
    public void transfer(@NotNull BigDecimal amountTransfer, Account accountFromTransfer, Account accountToTransfer){
        try{
            accountFromTransfer.withdraw(amountTransfer);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Transfer deny.");
        }
        accountToTransfer.deposit(amountTransfer.multiply(
                accountToTransfer.multiplier.divide(
                        accountFromTransfer.multiplier)));
        System.out.println("Transfer successful.");
    }
    public void transfer(@NotNull BigDecimal amountTransfer, int idAccountFromTransfer, int idAccountToTransfer){
        transfer(amountTransfer, accountList.get(idAccountFromTransfer), accountList.get(idAccountToTransfer));
    }
    public void withdrawById(int id, @NotNull BigDecimal amountWithdraw){
        try{
            accountList.get(id).withdraw(amountWithdraw);
            System.out.println("Withdraw successful.");
        }catch (Exception e){
            System.out.println("Withdraw deny.");
        }
    }
    public void depositById(int id, @NotNull BigDecimal amountDeposit){
        try{
            accountList.get(id).deposit(amountDeposit);
            System.out.println("Deposit successful.");
        }catch (Exception e){
            System.out.println("Deposit deny.");
        }
    }

    public void save(String pathToFile){
        Gson gson = new Gson();
        String json = gson.toJson(this.accountList);
        try(FileOutputStream fileOutputStream= new FileOutputStream(pathToFile)){
            fileOutputStream.write(json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save file failed.");
        }
    }
    public void load(String pathToFile){
        Gson gson = new Gson();
        try(FileInputStream fileInputStream = new FileInputStream(pathToFile)){
            Type listType = new TypeToken<List<CashAccount>>() {}.getType();
            accountList = gson.fromJson(new String (fileInputStream.readAllBytes()), listType);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Load file failed.");
        }
    }
}