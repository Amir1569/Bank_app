package com.DistributedSystemsLab2.Bank_app;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BankApplicationCommandLineRunner implements CommandLineRunner {

    private final BankRepository bankRepository;

    public BankApplicationCommandLineRunner(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void run(String... args) {
        bankRepository.saveAll(List.of(
                new BankAccount("Michail", 100.0),
                new BankAccount("Khatchik", 150.0),
                new BankAccount("Wassim", 0.0),
                new BankAccount("Afaf", 180.0)
        ));
        bankRepository.findAll().forEach(System.out::println);
    }
}
