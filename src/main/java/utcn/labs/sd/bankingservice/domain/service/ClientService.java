package utcn.labs.sd.bankingservice.domain.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.ClientConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.data.repository.AccountRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.ClientRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.ClientDTO;
import utcn.labs.sd.bankingservice.domain.exception.CreateClientException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ActivityService activityService;

    public List<ClientDTO> getAllClients() {
        activityService.addNewActivity("getAllClients");
        return ClientConverter.toDto(clientRepository.findAll());
    }

    public ClientDTO getClientById(String clientId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) throw new NotFoundException("No client found with that clientId");
        activityService.addNewActivity("getClientById: "+clientId);
        return ClientConverter.toDto(client);
    }

    public ClientDTO createClient(ClientDTO clientDto) throws Exception {
        Client client = new Client(clientDto.getSsn(), clientDto.getFirstname(), clientDto.getLastname(), clientDto.getIdentityCardNumber(),
                clientDto.getAddress(), clientDto.getEmail(), clientDto.getAccountList());
        Client possibleAlreadyExistingClient = clientRepository.findById(clientDto.getSsn()).orElse(null);
        if (possibleAlreadyExistingClient == null) {
            activityService.addNewActivity("createdClient with ssn: "+client.getSsn());
            clientRepository.save(client);
            return ClientConverter.toDto(client);
        } else {
            throw new CreateClientException("Client already exists!");
        }
    }

    public ClientDTO changeClient(String clientId, ClientDTO clientDto) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("No client found with that clientId");
        }
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setIdentityCardNumber(clientDto.getIdentityCardNumber());
        client.setAddress(clientDto.getAddress());
        client.setEmail(clientDto.getEmail());
        activityService.addNewActivity("changedClient with ssn: "+client.getSsn());
        return ClientConverter.toDto(clientRepository.save(client));
    }

    public void deleteClient(String clientId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("No client with that clientId");
        }
        List<Account> clientListOfAccounts = client.getAccountList();
        for (Account account : clientListOfAccounts) {
            accountRepository.delete(account);
        }
        activityService.addNewActivity("deletedClient with ssn: "+client.getSsn());
        clientRepository.delete(client);
    }

    public ClientDTO addAccountToClient(String clientId, Integer accountId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("No client with that clientId");
        }
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account with that accountId");
        }
        if (account.getClient()!=null){
            throw new Exception("Account already in use by another client");
        }
        client.getAccountList().add(account);
        account.setClient(clientId);
        accountRepository.save(account);
        clientRepository.save(client);
        activityService.addNewActivity("addedAccount: "+accountId+" toClient: "+clientId);
        return ClientConverter.toDto(client);
    }

    public ClientDTO deleteAccountFromClient(String clientId, Integer accountId) throws Exception{
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("No client with that clientId");
        }
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account with that accountId");
        }
        if(client.getAccountList().contains(account)){
            client.getAccountList().remove(account);
        }else {
            throw new NotFoundException("This account does not belong to this client. Cannot perform delete");
        }
        accountRepository.deleteById(accountId);
        clientRepository.save(client);
        activityService.addNewActivity("deletedAccount: "+accountId+" fromClient: "+clientId);
        return ClientConverter.toDto(client);
    }
}
