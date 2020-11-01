import java.math.BigDecimal;

public class CashAccount extends Account{
    public CashAccount(String currency, BigDecimal multiplier) {
        balance         = new BigDecimal("0");
        this.currency   = currency;
        this.multiplier = multiplier;
    }
    public CashAccount(String currency, BigDecimal multiplier, BigDecimal balance) {
        this.balance    = balance;
        this.currency   = currency;
        this.multiplier = multiplier;
    }
}
