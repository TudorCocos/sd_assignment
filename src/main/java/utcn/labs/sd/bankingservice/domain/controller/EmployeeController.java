package utcn.labs.sd.bankingservice.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;
import utcn.labs.sd.bankingservice.domain.data.entity.Employee;
import utcn.labs.sd.bankingservice.domain.dto.EmployeeDTO;
import utcn.labs.sd.bankingservice.domain.dto.EmployeeDTO;
import utcn.labs.sd.bankingservice.domain.service.EmployeeService;
import utcn.labs.sd.bankingservice.domain.service.Report;

import java.util.Collections;
import java.util.List;

@Api(tags = {SwaggerTags.BANKING_SERVICE_TAG})
@RestController
@RequestMapping("/bank/admin/employee")
@CrossOrigin
class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "getAllEmployees", tags = SwaggerTags.EMPLOYEE_TAG)
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() { return employeeService.getAllEmployees();
    }

    @ApiOperation(value = "findEmployeeById", tags = SwaggerTags.EMPLOYEE_TAG)
    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<?> findEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        try {
            EmployeeDTO employeeDto = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "insertEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
    @PostMapping
    public ResponseEntity<?> insertEmployee(@RequestBody EmployeeDTO employeeDto) {
        EmployeeDTO employeeDtoToBeInserted;
        try {
            employeeDtoToBeInserted = employeeService.createEmployee(employeeDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeDtoToBeInserted, HttpStatus.CREATED);
    }

    @ApiOperation(value = "updateEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
    @PutMapping(value = "/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") Integer employeeId, @RequestBody EmployeeDTO employeeDto) {
        try {
            employeeService.updateEmployee(employeeId, employeeDto);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "deleteEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "generateReport", tags = SwaggerTags.EMPLOYEE_TAG)
    @GetMapping(value = "/generateReport")
    public ResponseEntity<?> deleteEmployee() {
        try {
            Report.createPDF();
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
