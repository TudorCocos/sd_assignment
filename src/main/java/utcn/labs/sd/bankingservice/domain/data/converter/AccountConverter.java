package utcn.labs.sd.bankingservice.domain.data.converter;


import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;


public class AccountConverter {

    private AccountConverter() {
    }

    public static AccountDTO toDto(Account model) {
        AccountDTO dto = null;
        if (model != null) {
            dto = new AccountDTO(model.getAccountType(), model.getBalance());
        }
        return dto;
    }

    public static List<AccountDTO> toDto(List<Account> models) {
        List<AccountDTO> accountDtos = new ArrayList<>();
        if ((models != null) && (!models.isEmpty())) {
            for (Account model : models) {
                accountDtos.add(toDto(model));
            }
        }
        return accountDtos;
    }
}
