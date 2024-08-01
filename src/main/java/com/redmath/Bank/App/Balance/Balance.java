package com.redmath.Bank.App.Balance;

import com.redmath.Bank.App.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Balance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "account_holder", nullable = false)
  private User accountHolder;

  private Double amount;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(User accountHolder) {
    this.accountHolder = accountHolder;
  }
}
