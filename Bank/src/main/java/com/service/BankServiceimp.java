package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BankDaoinf;
import com.model.Transaction;
import com.model.User;

@Service
@Transactional
public class BankServiceimp implements BankServiceinf{
@Autowired
BankDaoinf dao;

@Override
public User checkuser(User user) {
	return dao.checkuser(user);
}

@Override
public void saveCustomer(User user) {
	dao.saveCustomer(user);
	
}

@Override
public List<User> getallCustomer() {
	
	return dao.getallCustomer();
}

@Override
public User getUserDetail(int id) {
	
	return dao.getUserDetail(id);
}

@Override
public void UpdateCustomer(User user) {
	dao.UpdateCustomer(user);
	
}



@Override
public String saveTransaction(Transaction transaction,HttpServletRequest request) {
	return dao.saveTransaction(transaction,request);
	
}

@Override
public List<Transaction> getallofCustomer(int id) {
	
	return dao.getallofCustomer(id);
}
}
