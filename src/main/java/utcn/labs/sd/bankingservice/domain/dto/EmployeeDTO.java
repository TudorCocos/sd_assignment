package utcn.labs.sd.bankingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.EmployeeType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {

    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final EmployeeType employeeType;

    @JsonCreator
    public EmployeeDTO(  @JsonProperty("username") String username,
                         @JsonProperty("password") String password,
                         @JsonProperty("firstName") String firstName,
                         @JsonProperty("lastName") String lastName,
                         @JsonProperty("employeeType") EmployeeType employeeType) {
        this.username=username;
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.employeeType=employeeType;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmployeeDTO{");
        sb.append(", employeeType=").append(employeeType);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append('}');
        return sb.toString();
    }
}
