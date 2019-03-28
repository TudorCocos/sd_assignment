package utcn.labs.sd.bankingservice.domain.data.converter;

import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConverter {

    private EmployeeConverter(){

    }

    public static EmployeeDTO toDto(Employee model) {
        EmployeeDTO dto = null;
        if (model != null) {
            dto = new EmployeeDTO(model.getId(),model.getUsername(),model.getPassword(),model.getFirstName(),model.getLastName(),model.getEmployeeType());
        }
        return dto;
    }

    public static List<EmployeeDTO> toDto(List<Employee> models) {
        List<EmployeeDTO> employeeDtos = new ArrayList<>();
        if ((models != null) && (!models.isEmpty())) {
            for (Employee model : models) {
                employeeDtos.add(toDto(model));
            }
        }
        return employeeDtos;
    }
}
