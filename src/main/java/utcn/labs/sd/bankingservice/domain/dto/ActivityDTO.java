package utcn.labs.sd.bankingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityDTO {

    private final int id;
    private final int id_employee;
    private final String activity;
    private final String date;

    @JsonCreator
    public ActivityDTO(@JsonProperty("id") int id,
                       @JsonProperty("id_employee") int id_employee,
                       @JsonProperty("activity") String activity,
                       @JsonProperty("date") String date) {
        this.id = id;
        this.id_employee = id_employee;
        this.activity = activity;
        this.date = date;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id_employee")
    public int getId_employee() {
        return id_employee;
    }

    @JsonProperty("activity")
    public String getActivity() {
        return activity;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActivityDto{");
        sb.append("id=").append(id).append('\'');
        sb.append(", id_employee=").append(id_employee).append('\'');
        sb.append(", activity='").append(activity).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
