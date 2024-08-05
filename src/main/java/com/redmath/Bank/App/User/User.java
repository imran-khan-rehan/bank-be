package com.redmath.Bank.App.User;

import com.redmath.Bank.App.Balance.Balance;
import com.redmath.Bank.App.Transaction.Transaction;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

import java.util.List;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountNumber;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    private String role;

    @OneToMany(mappedBy = "accountHolder", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Balance> balances;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Transaction> receivedTransactions;

    public User() {

    }
    public User(User other) {
        this.id = other.id;
        this.accountNumber = other.accountNumber;
        this.email = other.email;
        this.name = other.name;
        this.password = other.password;
        this.role = other.role;
//        this.balances = other.balances;
//        this.sentTransactions = other.sentTransactions;
//        this.receivedTransactions = other.receivedTransactions;
    }
    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;  
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
