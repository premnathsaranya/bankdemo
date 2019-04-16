package com.Dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.model.Transaction;
import com.model.User;

public interface BankDaoinf {



	User checkuser(User user);

	void saveCustomer(User user);

	List<User> getallCustomer();

	User getUserDetail(int id);

	void UpdateCustomer( User user);

	String saveTransaction(Transaction transaction,HttpServletRequest request);

	List<Transaction> getallofCustomer(int id);

}
