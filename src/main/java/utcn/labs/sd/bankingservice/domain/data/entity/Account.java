package utcn.labs.sd.bankingservice.domain.data.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account_table")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private int accountId;

    @Column(name= "client_ssn")
    @Size(min = 13, max = 13, message = "Client ssn must be 13 characters long")
    private String clientSsn;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "date_of_creation")
    @Size(max=45, message = "Date field must be 45 characters long")
    private String creationDate;

    @Column(name = "balance")
    @Min(value = 0, message="Cannot have a negative balance")
    private float balance;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getClient(){
        return this.clientSsn;
    }

    public void setClient(String clientSsn){
        this.clientSsn = clientSsn;
    }

    public Account(AccountType accountType, String creationDate, float balance) {
        this.accountType = accountType;
        this.creationDate = creationDate;
        this.balance = balance;
    }

    public Account() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("accountId=").append(accountId);
        sb.append(", accountType=").append(accountType);
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
