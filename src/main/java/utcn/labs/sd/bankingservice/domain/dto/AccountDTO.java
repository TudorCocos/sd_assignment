package utcn.labs.sd.bankingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private final AccountType accountType;
    private final float balance;

    @JsonCreator
    public AccountDTO(@JsonProperty("accountType") AccountType accountType,
                      @JsonProperty("balance") float balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    @JsonProperty("accountType")
    public AccountType getAccountType() {
        return accountType;
    }

    @JsonProperty("balance")
    public float getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountDto{");
        sb.append("accountType=").append(accountType);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
