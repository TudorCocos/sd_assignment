package utcn.labs.sd.bankingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

    private String ssn;
    private String firstname;
    private String lastname;
    private String identityCardNumber;
    private String address;
    private String email;
    private List<Account> accountList;

    @JsonCreator
    public ClientDTO(@JsonProperty("ssn") String ssn,
                     @JsonProperty("firstname") String firstname,
                     @JsonProperty("lastname") String lastname,
                     @JsonProperty("identityCardNumber") String identityCardNumber,
                     @JsonProperty("address") String address,
                     @JsonProperty("email") String email,
                     @JsonProperty("accountList") List<Account> accountList) {
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
        this.email = email;
        this.accountList = new ArrayList<Account>();
    }

    @JsonProperty("ssn")
    public String getSsn() {
        return ssn;
    }

    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("identityCardNumber")
    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("accountList")
    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientDto{");
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
