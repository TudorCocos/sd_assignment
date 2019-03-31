package utcn.labs.sd.bankingservice.domain.data.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "activity_table")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "id_employee")
    private int id_employee;

    @Column(name = "activity")
    @Size(max=200, message = "Activity field must be at most 200 characters long")
    private String activity;

    @Column(name = "date")
    @Size(max=45, message = "Date field must be 45 characters long")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Activity(int id_employee, String activity, String date) {
        this.id_employee = id_employee;
        this.activity = activity;
        this.date = date;
    }

    public Activity(){

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Activity{");
        sb.append("id=").append(id).append('\'');
        sb.append(", id_employee=").append(id_employee).append('\'');
        sb.append(", activity='").append(activity).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
