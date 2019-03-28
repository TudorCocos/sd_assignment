package utcn.labs.sd.bankingservice.domain.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
