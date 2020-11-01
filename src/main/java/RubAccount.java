import java.math.BigDecimal;
public class RubAccount extends Account{
    public RubAccount() {
        balance     = new BigDecimal("0");
        currency    = "RUB";
        multiplier  = new BigDecimal("1");
    }
    public RubAccount(BigDecimal balance) {
        this.balance= balance;
        currency    = "RUB";
        multiplier  = new BigDecimal("1");
    }
}
