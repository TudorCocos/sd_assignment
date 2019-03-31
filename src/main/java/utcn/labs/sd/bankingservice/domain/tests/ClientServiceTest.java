package utcn.labs.sd.bankingservice.domain.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;
import utcn.labs.sd.bankingservice.domain.data.repository.AccountRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.ClientRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.ClientDTO;
import utcn.labs.sd.bankingservice.domain.service.AccountService;
import utcn.labs.sd.bankingservice.domain.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private ClientService clientService;

    private Client clientTest;
    private ClientDTO clientDTO;
    private AccountDTO accountDTO;
    private Account accountTest;


    @Before
    public void setUp() throws Exception {
        //clientTest = new Client("1970424123456","Claudiu","Mirciulescu","SM123456","Satu Mare","clau@email.com",new ArrayList<Account>());
        clientDTO = new ClientDTO("1970424123456","Claudiu","Mirciulescu","SM123456","Satu Mare","clau@email.com",new ArrayList<Account>());
        accountTest = new Account(AccountType.SAVINGS,"2019-03-30 16:18:25.861",20000);
        accountDTO = new AccountDTO(AccountType.SAVINGS,2000);

        clientService.createClient(clientDTO);
        accountService.createAccount(accountDTO);
    }

//    @Test
//    public void testAddAccountToClient() throws Exception {
//        clientDTO = clientService.addAccountToClient(clientDTO.getSsn(),accountDTO.);
//
//        Assert.assertEquals(clientDTO.getAccountList().get(0).getAccountId(),accountTest.getAccountId());
//    }
//
//    @Test
//    public void testDeleteAccountFromClient() throws Exception {
//        clientService.addAccountToClient(clientTest.getSsn(),accountTest.getAccountId());
//        clientDTO = clientService.deleteAccountFromClient(clientTest.getSsn(),accountTest.getAccountId());
//
//        Assert.assertEquals(clientDTO.getAccountList().size(),0);
//    }

    @After
    public void tearDown() throws Exception {
        clientService.deleteClient(clientTest.getSsn());
    }
}
