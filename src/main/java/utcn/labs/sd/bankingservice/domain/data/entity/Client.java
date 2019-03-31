package utcn.labs.sd.bankingservice.domain.data.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "client_table")
public class Client {

    @Id
    @Column(name = "SSN")
    @Size(min = 13, max = 13, message = "Client ssn must be 13 characters long")
    private String ssn;

    @Column(name = "firstname")
    @Size(max=45, message = "First name field cannot be longer than 45 characters")
    private String firstname;

    @Column(name = "lastname")
    @Size(max=45, message = "Last name field cannot be longer than 45 characters")
    private String lastname;

    @Column(name = "identity_card_number")
    @Pattern(regexp = "[A-Z]{2}(\\d{6})")
    private String identityCardNumber;

    @Column(name = "address")
    @Size(max=45, message = "Address field cannot be longer than 45 characters")
    private String address;

    @Column(name = "email")
    @Email
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_ssn")
    private List<Account> accountList;

    public Client() {

    }

    public Client(String ssn, String firstname, String lastname, String identityCardNumber, String address, String email, List<Account> accountList) {
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
        this.email = email;
        this.accountList = accountList;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("ssn='").append(ssn).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", identityCardNumber='").append(identityCardNumber).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", accountList=").append(accountList);
        sb.append('}');
        return sb.toString();
    }
}
