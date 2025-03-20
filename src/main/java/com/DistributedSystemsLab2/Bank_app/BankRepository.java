package com.DistributedSystemsLab2.Bank_app;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BankRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByName(String name);
}
