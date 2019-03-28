package utcn.labs.sd.bankingservice.domain.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.EmployeeConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.data.repository.EmployeeRepository;
import utcn.labs.sd.bankingservice.domain.dto.EmployeeDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository EmployeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return EmployeeConverter.toDto(EmployeeRepository.findAll());
    }

    public EmployeeDTO getEmployeeById(Integer EmployeeId) throws Exception {
        Employee employee = EmployeeRepository.findById(EmployeeId).orElse(null);
        if (employee == null) throw new NotFoundException("No Employee found with that EmployeeId");
        return EmployeeConverter.toDto(employee);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDto) throws Exception {
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Employee employee = new Employee(employeeDto.getUsername(),employeeDto.getPassword(),employeeDto.getFirstName(),employeeDto.getLastName(),employeeDto.getEmployeeType());
        Employee newEmployee = EmployeeRepository.save(employee);
        return EmployeeConverter.toDto(newEmployee);
    }

    public EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employeeDto) throws Exception {
        Employee employee = EmployeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NotFoundException("No Employee found with that id");
        }
        employee.setPassword(employeeDto.getPassword());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmployeeType(employeeDto.getEmployeeType());
        return EmployeeConverter.toDto(EmployeeRepository.save(employee));
    }


    public void deleteEmployee(Integer employeeId) throws Exception {
        Employee employee = EmployeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NotFoundException("No Employee with that EmployeeId");
        }
        EmployeeRepository.delete(employee);
    }
}
