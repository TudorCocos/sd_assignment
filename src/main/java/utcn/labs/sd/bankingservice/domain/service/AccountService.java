package utcn.labs.sd.bankingservice.domain.service;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.repository.AccountRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountDTO> getAllAccounts() {
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- getAllAccounts -- ");
        return AccountConverter.toDto(accountRepository.findAll());
    }

    public AccountDTO getAccountById(Integer accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) throw new NotFoundException("No account found with that accountId");
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- getAccountById -- id:"+account.getAccountId());
        return AccountConverter.toDto(account);
    }

    public AccountDTO createAccount(AccountDTO accountDto) throws Exception {
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        if (accountDto.getBalance() < 0) {
            throw new Exception("Impossible to have a negative balance");
        }
        Account account = new Account(accountDto.getAccountId(), accountDto.getAccountType(), currentTimestamp.toString(), accountDto.getBalance());
        Account newAccount = accountRepository.save(account);
        Report.addReport(currentTimestamp.toString()+" -- createAccount -- id:"+account.getAccountId());
        return AccountConverter.toDto(newAccount);
    }


    public AccountDTO updateAccount(Integer accountId, AccountDTO accountDto) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account found with that id");
        }
        account.setBalance(accountDto.getBalance());
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- updateAccount -- id:"+account.getAccountId());
        return AccountConverter.toDto(accountRepository.save(account));
    }


    public void deleteAccount(Integer accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account with that accountId");
        }
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- deleteAccount -- id:"+account.getAccountId());
        accountRepository.delete(account);
    }

    public void transferMoney(Integer fromAccountId, Integer toAccountId, float sum) throws Exception{
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        if (fromAccount == null) {
            throw new NotFoundException("No account found with that id");
        }
        if (fromAccount.getBalance()<sum) {
            throw new Exception("Insufficient funds");
        }
        if (sum<0.0) {
            throw new Exception("Cannot have a negative sum");
        }
        Account toAccount = accountRepository.findById(toAccountId).orElse(null);
        if (toAccount == null) {
            throw new NotFoundException("No account found with that id");
        }
        fromAccount.setBalance(fromAccount.getBalance()-sum);
        toAccount.setBalance(toAccount.getBalance()+sum);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- transferMoney -- from:"+fromAccount.getAccountId()+ " to:"+toAccount.getAccountId()+" sum:"+sum);
    }

    public void payBill(Integer fromAccountId, String billDetails, float amount) throws Exception{
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        if (fromAccount == null) {
            throw new NotFoundException("No account found with that id");
        }
        if (fromAccount.getBalance()<amount) {
            throw new Exception("Insufficient funds");
        }
        fromAccount.setBalance(fromAccount.getBalance()-amount);
        accountRepository.save(fromAccount);
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Report.addReport(currentTimestamp.toString()+" -- payBill -- from:"+fromAccount.getAccountId()+ " amount:"+amount+" details:"+billDetails);
    }
}
