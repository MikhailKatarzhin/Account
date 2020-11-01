import java.math.BigDecimal;
import org.jetbrains.annotations.*;

public abstract class Account {
    protected BigDecimal  balance;
    protected BigDecimal  multiplier;
    protected String      currency;

    public void withdraw(@NotNull BigDecimal amountWithdraw)
            throws IllegalArgumentException, NotEnoughMoneyException{
        if(amountWithdraw.compareTo(BigDecimal.valueOf(0)) < 0){
            throw new IllegalArgumentException("Can't withdraw negative amount");
        }
        if(balance.compareTo(amountWithdraw) < 0){
            throw new NotEnoughMoneyException(String.format(
                    "Can't withdraw %f, because balance is %f.", amountWithdraw, this.balance)
            );
        }
        balance = balance.subtract(amountWithdraw);
    }

    public void deposit(@NotNull BigDecimal amountDeposit)
        throws IllegalArgumentException{
        if(amountDeposit.compareTo(new BigDecimal("0")) < 0){
            throw new IllegalArgumentException("Can't deposit negative amount");
        }
        balance = balance.add(amountDeposit);
    }

    public void showInfo(){
        System.out.printf("Balance: %f %s%n", this.balance, this.currency);
    }

    protected static class NotEnoughMoneyException extends Exception{
        NotEnoughMoneyException(String message) {
            super(String.format("Too low money at account: %s", message));
        }
    }
}

