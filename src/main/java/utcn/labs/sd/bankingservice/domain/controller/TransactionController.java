package utcn.labs.sd.bankingservice.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;
import utcn.labs.sd.bankingservice.domain.dto.TransactionDTO;
import utcn.labs.sd.bankingservice.domain.service.TransactionService;

import java.util.List;

@Api(tags = {SwaggerTags.BANKING_SERVICE_TAG})
@RestController
@RequestMapping("/bank/employee/transaction")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @ApiOperation(value = "getAllTransactions", tags = SwaggerTags.TRANSACTION_TAG)
    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @ApiOperation(value = "findTransactionById", tags = SwaggerTags.TRANSACTION_TAG)
    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<?> findEmployeeById(@PathVariable("transactionId") Integer transactionId) {
        try {
            TransactionDTO transactionDTO = transactionService.getTransactionById(transactionId);
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
