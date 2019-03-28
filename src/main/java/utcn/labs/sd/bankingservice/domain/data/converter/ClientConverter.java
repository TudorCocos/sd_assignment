package utcn.labs.sd.bankingservice.domain.data.converter;

import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class ClientConverter {

    private ClientConverter() {
    }

    public static ClientDTO toDto(Client model) {
        ClientDTO dto = null;
        if (model != null) {
            dto = new ClientDTO(model.getSsn(), model.getFirstname(), model.getLastname(), model.getIdentityCardNumber(), model.getAddress(),
                    model.getEmail(), model.getAccountList());
        }
        return dto;
    }

    public static List<ClientDTO> toDto(List<Client> models) {
        List<ClientDTO> clientDtos = new ArrayList<>();
        if ((models != null) && (!models.isEmpty())) {
            for (Client model : models) {
                clientDtos.add(toDto(model));
            }
        }
        return clientDtos;
    }
}
