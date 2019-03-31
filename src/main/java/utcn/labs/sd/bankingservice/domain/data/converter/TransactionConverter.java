package utcn.labs.sd.bankingservice.domain.data.converter;

import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.data.entity.Transaction;
import utcn.labs.sd.bankingservice.domain.dto.TransactionDTO;

import java.util.ArrayList;
import java.util.List;

public class TransactionConverter {

    private TransactionConverter(){

    }

    public static TransactionDTO toDto(Transaction model){
        TransactionDTO dto = null;
        if(model != null){
            dto = new TransactionDTO(model.getId(),model.getAcc_from(),model.getAcc_to(),
                    model.getBill(),model.getAmount(),model.getDate());
        }
        return dto;
    }

    public static List<TransactionDTO> toDto(List<Transaction> models){
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        if((models != null) && (!models.isEmpty())){
            for(Transaction transaction : models){
                transactionDTOS.add(toDto(transaction));
            }
        }
        return transactionDTOS;
    }

}
