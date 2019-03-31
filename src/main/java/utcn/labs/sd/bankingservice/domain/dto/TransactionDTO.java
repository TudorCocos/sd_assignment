package utcn.labs.sd.bankingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    private final int id;
    private final int acc_from;
    private final int acc_to;
    private final String bill;
    private final float amount;
    private final String date;

    @JsonCreator
    public TransactionDTO(@JsonProperty("id") int id,
                          @JsonProperty("acc_from") int acc_from,
                          @JsonProperty("acc_to") int acc_to,
                          @JsonProperty("bill") String bill,
                          @JsonProperty("amount") float amount,
                          @JsonProperty("date") String date) {
        this.id = id;
        this.acc_from = acc_from;
        this.acc_to = acc_to;
        this.bill = bill;
        this.amount = amount;
        this.date = date;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("acc_from")
    public int getAcc_from() {
        return acc_from;
    }

    @JsonProperty("acc_to")
    public int getAcc_to() {
        return acc_to;
    }

    @JsonProperty("bill")
    public String getBill() {
        return bill;
    }

    @JsonProperty("amount")
    public float getAmount() {
        return amount;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionDto{");
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
