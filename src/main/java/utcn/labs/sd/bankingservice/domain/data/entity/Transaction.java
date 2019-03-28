package utcn.labs.sd.bankingservice.domain.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="transaction_table")
public class Transaction {

    private Integer id;

    private Timestamp date;

    private Integer employeeId;

    private String operation;

    private String details;

}
