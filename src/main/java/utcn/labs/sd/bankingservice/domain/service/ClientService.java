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

    public List<ClientDTO> getAllClients() {
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- getAllClients -- ");
        return ClientConverter.toDto(clientRepository.findAll());
    }

    public ClientDTO getClientById(String clientId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) throw new NotFoundException("No client found with that clientId");
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- getClientById -- ssn:"+client.getSsn());
        return ClientConverter.toDto(client);
    }

    public ClientDTO createClient(ClientDTO clientDto) throws Exception {
        Client client = new Client(clientDto.getSsn(), clientDto.getFirstname(), clientDto.getLastname(), clientDto.getIdentityCardNumber(),
                clientDto.getAddress(), clientDto.getEmail(), clientDto.getAccountList());
        Client possibleAlreadyExistingClient = clientRepository.findById(clientDto.getSsn()).orElse(null);
        if (possibleAlreadyExistingClient == null) {
            Client newClient = clientRepository.save(client);
            Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
            Report.addReport(currentTimestamp.toString()+" -- createClient -- ssn:"+newClient.getSsn());
            return ClientConverter.toDto(newClient);
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
        client.setIdentityCardNumber(clientId);
        client.setAddress(clientDto.getAddress());
        client.setEmail(clientDto.getEmail());
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- changeClient -- ssn:"+client.getSsn());
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
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- deleteClient -- ssn:"+client.getSsn());
        clientRepository.delete(client);
    }

    public ClientDTO addAccountToClient(String clientId, AccountDTO accountDto) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Account account = new Account(accountDto.getAccountId(), accountDto.getAccountType(), currentTimestamp.toString(), accountDto.getBalance());
        client.getAccountList().add(account);
        accountRepository.save(account);
        Report.addReport(currentTimestamp.toString()+" -- addAccountToClient -- ssn:"+client.getSsn()+" accountId:"+account.getAccountId());
        return ClientConverter.toDto(client);
    }

    public ClientDTO deleteAccountFromClient(String clientId, Integer accountId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        accountRepository.deleteById(accountId);
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- deleteAccountFromClient -- ssn:"+client.getSsn()+" accountId:"+accountId);
        return ClientConverter.toDto(client);
    }
}
