package utcn.labs.sd.bankingservice.domain.data.entity;


import utcn.labs.sd.bankingservice.domain.data.entity.enums.EmployeeType;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="employee_table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="username")
    @Size(max=45, message = "Username cannot be longer than 45 characters")
    private String username;

    @Column(name="password")
    @Size(max=128, message = "Password cannot be longer than 128 characters")
    private String password;

    @Column(name="first_name")
    @Size(max=45, message = "First name cannot be longer than 45 characters")
    private String firstName;

    @Column(name="last_name")
    @Size(max=45, message = "Last name cannot be longer than 45 characters")
    private String lastName;

    @Column(name="employee_type")
    @Size(max=45, message = "Employee type field cannot be longer than 45 characters")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    public Employee(String username, String password, String firstName, String lastName, EmployeeType employeeType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeType = employeeType;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("employeeId=").append(id);
        sb.append(", employeeType=").append(employeeType);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append('}');
        return sb.toString();
    }
}
