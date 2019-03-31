package utcn.labs.sd.bankingservice.domain.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.EmployeeConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.ReportType;
import utcn.labs.sd.bankingservice.domain.data.repository.ActivityRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.EmployeeRepository;
import utcn.labs.sd.bankingservice.domain.dto.EmployeeDTO;
import utcn.labs.sd.bankingservice.domain.reports.ReportFactory;
import utcn.labs.sd.bankingservice.domain.reports.ReportInterface;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRepository activityRepository;

    public List<EmployeeDTO> getAllEmployees() {
        activityService.addNewActivity("getAllEmployees");
        return EmployeeConverter.toDto(employeeRepository.findAll());
    }

    public EmployeeDTO getEmployeeById(Integer employeeId) throws Exception {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) throw new NotFoundException("No Employee found with that EmployeeId");
        activityService.addNewActivity("getEmployeeById: "+employeeId);
        return EmployeeConverter.toDto(employee);
    }

    public Employee getEmployeeByUsername(String username){
        for(Employee employee : employeeRepository.findAll()){
            if(employee.getUsername().equals(username)){
                return employee;
            }
        }
        return null;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDto) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(employeeDto.getPassword());
        Employee employee = new Employee(employeeDto.getUsername(),hashedPassword,employeeDto.getFirstName(),employeeDto.getLastName(),employeeDto.getEmployeeType());
        employeeRepository.save(employee);
        activityService.addNewActivity("createdEmployee: "+employee.getId());
        return EmployeeConverter.toDto(employee);
    }

    public EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employeeDto) throws Exception {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NotFoundException("No Employee found with that id");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(employeeDto.getPassword());
        employee.setPassword(hashedPassword);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmployeeType(employeeDto.getEmployeeType());
        activityService.addNewActivity("updatedEmployee: "+employeeId);
        return EmployeeConverter.toDto(employeeRepository.save(employee));
    }

    public void deleteEmployee(Integer employeeId) throws Exception {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NotFoundException("No Employee with that EmployeeId");
        }
        activityService.addNewActivity("deleteddEmployee: "+employeeId);
        employeeRepository.delete(employee);
    }

    public void generateReport(String fromDate, String toDate, ReportType reportType, String employeeUsername){
        ReportInterface reportInterface = ReportFactory.getReport(reportType);
        try {
            Employee employee = getEmployeeByUsername(employeeUsername);
            reportInterface.generateReport(fromDate,toDate, employee, activityRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
