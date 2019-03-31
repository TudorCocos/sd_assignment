package utcn.labs.sd.bankingservice.domain.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.TransactionConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Transaction;
import utcn.labs.sd.bankingservice.domain.data.repository.TransactionRepository;
import utcn.labs.sd.bankingservice.domain.dto.TransactionDTO;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ActivityService activityService;

    public List<TransactionDTO> getAllTransactions(){
        activityService.addNewActivity("getAllTransactions");
        return TransactionConverter.toDto(transactionRepository.findAll());
    }

    public TransactionDTO getTransactionById(Integer transactionId) throws Exception {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction == null) throw new NotFoundException("No employee found with that employeeId");
        activityService.addNewActivity("getTransactionById: "+transactionId);
        return TransactionConverter.toDto(transaction);
    }

}
