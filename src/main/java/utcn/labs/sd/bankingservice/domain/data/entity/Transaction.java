package utcn.labs.sd.bankingservice.domain.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "transaction_table")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "acc_from")
    private int acc_from;

    @Column(name = "acc_to")
    private int acc_to;

    @Column(name = "bill")
    @Size(max=45, message = "Bill details field cannot be longer than 45 characters")
    private String bill;

    @Column(name = "amount")
    private float amount;

    @Column(name = "date")
    @Size(max=45, message = "Date field cannot be longer than 45 characters")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcc_from() {
        return acc_from;
    }

    public void setAcc_from(int acc_from) {
        this.acc_from = acc_from;
    }

    public int getAcc_to() {
        return acc_to;
    }

    public void setAcc_to(int acc_to) {
        this.acc_to = acc_to;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Transaction(int acc_from, int acc_to, String bill, float amount, String date) {
        this.acc_from = acc_from;
        this.acc_to = acc_to;
        this.bill = bill;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(){

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("id=").append(id).append('\'');
        sb.append(", acc_from=").append(acc_from).append('\'');
        sb.append(", acc_to='").append(acc_to).append('\'');
        sb.append(", bill=").append(bill).append('\'');
        sb.append(", amount=").append(amount).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
