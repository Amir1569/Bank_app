package com.DistributedSystemsLab2.Bank_app;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // <- Dit ontbrak
@Table(name = "bank_accounts") // Optioneel, maar helpt naamgeving in DB
@NoArgsConstructor // <- Dit is vereist voor JPA
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Zorgt voor automatische ID generatie
    private long id;
    private String name;
    @Getter
    private double balance;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0)
            this.balance += amount;
        else throw new IllegalArgumentException("Amount must be positive");
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance)
            this.balance -= amount;
        else throw new IllegalArgumentException("Invalid withdrawal amount");
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

}
