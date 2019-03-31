package utcn.labs.sd.bankingservice.domain.service;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.data.entity.Transaction;
import utcn.labs.sd.bankingservice.domain.data.repository.AccountRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.ClientRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.TransactionRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ActivityService activityService;

    public List<AccountDTO> getAllAccounts() {
        activityService.addNewActivity("getAllAccounts");
        return AccountConverter.toDto(accountRepository.findAll());
    }

    public AccountDTO getAccountById(Integer accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) throw new NotFoundException("No account found with that accountId");
        activityService.addNewActivity("getAccountById: "+accountId);
        return AccountConverter.toDto(account);
    }

    public AccountDTO createAccount(AccountDTO accountDto) throws Exception {
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Account account = new Account(accountDto.getAccountType(), currentTimestamp.toString(), accountDto.getBalance());
        accountRepository.save(account);
        activityService.addNewActivity("createdAccount with id: "+account.getAccountId());
        return AccountConverter.toDto(account);
    }


    public AccountDTO updateAccount(Integer accountId, float newBalance) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account found with that id");
        }
        account.setBalance(newBalance);
        activityService.addNewActivity("updatedAccount with id: "+accountId+" with new balance of: "+newBalance);
        accountRepository.save(account);
        return AccountConverter.toDto(account);
    }


    public void deleteAccount(Integer accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account with that accountId");
        }
        Client client = clientRepository.findById(account.getClient()).orElse(null);
        if(client == null){
            throw new NotFoundException("Account hasn't been added to a client yet");
        }
        client.getAccountList().remove(account);
        activityService.addNewActivity("deletedAccount with id: "+accountId);
        accountRepository.delete(account);
        clientRepository.save(client);
    }

    public void transferMoney(Integer fromAccountId, Integer toAccountId, float sum) throws Exception{
        if(fromAccountId.equals(toAccountId)){
            throw new Exception("Cannot perform a transfer to and from the same account");
        }
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        if (fromAccount == null) {
            throw new NotFoundException("No account found with that id");
        }
        Account toAccount = accountRepository.findById(toAccountId).orElse(null);
        if (toAccount == null) {
            throw new NotFoundException("No account found with that id");
        }
        if (sum<0.0) {
            throw new Exception("Cannot have a negative sum");
        }
        fromAccount.setBalance(fromAccount.getBalance()-sum);
        toAccount.setBalance(toAccount.getBalance()+sum);
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Transaction transaction = new Transaction(fromAccountId,toAccountId,null,sum,currentTimestamp.toString());
        transactionRepository.save(transaction);

        activityService.addNewActivity("transferedMoney from: "+fromAccountId+" to: "+toAccountId+" the sum: "+sum);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public void payBill(Integer fromAccountId, String billDetails, float amount) throws Exception{
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        if (fromAccount == null) {
            throw new NotFoundException("No account found with that id");
        }
        if (amount<0.0) {
            throw new Exception("Cannot have a negative amount to pay");
        }
        fromAccount.setBalance(fromAccount.getBalance()-amount);
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Transaction transaction = new Transaction(fromAccountId,-1,billDetails,amount,currentTimestamp.toString());
        transactionRepository.save(transaction);

        activityService.addNewActivity("payedBill from: "+fromAccountId+" the sum: "+amount+", details: "+billDetails);
        accountRepository.save(fromAccount);
    }
}
