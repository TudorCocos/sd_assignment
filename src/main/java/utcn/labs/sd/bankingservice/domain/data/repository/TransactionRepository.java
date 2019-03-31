package utcn.labs.sd.bankingservice.domain.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.labs.sd.bankingservice.domain.data.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
