package com.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	public String transactiontype;
	@DateTimeFormat(pattern="yy-mm-dd")
	public LocalDate transactiondate;
	public long accountno;
	public long recepientaccno;
	public int amount;
	@ManyToOne
	@JoinColumn(name = "customerid")
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public LocalDate getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(LocalDate transactiondate) {
		this.transactiondate = transactiondate;
	}
	public long getAccountno() {
		return accountno;
	}
	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}
	public long getRecepientaccno() {
		return recepientaccno;
	}
	public void setRecepientaccno(long recepientaccno) {
		this.recepientaccno = recepientaccno;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public User getTransaction() {
		return user;
	}
	public void setTransaction(User transaction) {
		this.user = transaction;
	}
	public Transaction() {
		super();
	}
	
	
	
	
}
