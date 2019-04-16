package com.Dao;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Transaction;
import com.model.User;

@Repository
public class BankDaoimp implements BankDaoinf {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User checkuser(User user) {

		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from user where name=:name and password=:password and role=:role");
		query.addEntity(User.class);
		query.setParameter("name", user.getName());
		query.setParameter("password", user.getPassword());
		query.setParameter("role", user.getRole());
		List<User> result = query.list();
		System.out.println(query);
		User response = null;
		Iterator i = result.iterator();
		while (i.hasNext()) {
			response = (User) i.next();
		}
		if (response == null) {
			System.out.println("no user");
			return response;
		} else {
			System.out.println(response + "test");
			return response;
		}

	}

	@Override
	public void saveCustomer(User user) {
		sessionFactory.getCurrentSession().save(user);

	}

	@Override
	public List<User> getallCustomer() {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
		List<User> user = c.list();
		System.out.println(user);
		return user;
	}

	@Override
	public User getUserDetail(int id) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from user where id=:id");
		query.addEntity(User.class);
		query.setParameter("id", id);
		List<User> result = query.list();
		System.out.println(query);
		User response = null;
		Iterator i = result.iterator();
		while (i.hasNext()) {
			response = (User) i.next();
		}
		return response;
	}

	@Override
	public void UpdateCustomer(User user) {
		System.out.println("inside edit" + user.id);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				"Update user set name=?,address=?,dob=?,email=?,mobileno=?,accountno=?,accounttype=?,balance=?,password=?,role=? where id=?");
		query.addEntity(User.class);
		query.setParameter(0, user.getName());
		query.setParameter(1, user.getAddress());
		query.setParameter(2, user.getDob());
		query.setParameter(3, user.getEmail());
		query.setParameter(4, user.getMobileno());
		query.setParameter(5, user.getAccountno());
		query.setParameter(6, user.getAccounttype());
		query.setParameter(7, user.getBalance());
		query.setParameter(8, user.getPassword());
		query.setParameter(9, user.getRole());
		query.setParameter(10, user.getId());
		query.executeUpdate();
	}

	@Override
	public String saveTransaction(Transaction transaction, HttpServletRequest request) {
		String result=null;
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("userid") + "  amount: " + session.getAttribute("amount"));
		int customerid = (int) session.getAttribute("userid");
		int amount = (int) session.getAttribute("amount");
		Long accountno = (Long) session.getAttribute("accountno");
		System.out.println("inside save Transaction" + transaction.transactiondate);
		if(amount>=transaction.getAmount()){
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					"insert into transaction(transactiontype,transactiondate,accountno,recepientaccno,amount,customerid) value(?,?,?,?,?,?)");
			query.addEntity(User.class);
			query.setParameter(0, transaction.getTransactiontype());
			query.setParameter(1, transaction.getTransactiondate());
			query.setParameter(2, accountno);
			query.setParameter(3, transaction.getRecepientaccno());
			query.setParameter(4, transaction.getAmount());
			query.setParameter(5, customerid);
			query.executeUpdate();
			int transactionamount = transaction.getAmount();
			int balance = amount - transactionamount;
			System.out.println("update balance:" + balance);
			SQLQuery user = sessionFactory.getCurrentSession().createSQLQuery("Update user set balance=? where id=?");
			user.addEntity(User.class);
			user.setParameter(0, balance);
			user.setParameter(1, customerid);
			user.executeUpdate();
			session.setAttribute("amount", balance);
			System.out.println(session.getAttribute("amount"));
			result="success";
			
		}else{
			result="insufficient balance";
		}
		return result;


	}

	// get transaction of particular user
	@Override
	public List<Transaction> getallofCustomer(int id) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from transaction where customerid=:id");
		query.addEntity(Transaction.class);
		query.setParameter("id", id);
		System.out.println(id);
		List<Transaction> result = query.list();
		if (result == null) {
			return result;
		} else {
			return result;
		}

	}
}
