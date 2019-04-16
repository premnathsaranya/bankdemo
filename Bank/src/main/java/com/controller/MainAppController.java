package com.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Transaction;
import com.model.User;
import com.service.BankServiceinf;

@Controller
public class MainAppController {
	@Autowired
	BankServiceinf service;

	// redirect to login page
	@RequestMapping(value = "/login")
	public ModelAndView login(@ModelAttribute("user") User user) {
		return new ModelAndView("Login");
	}

	// validate and redirect to particular page
	@RequestMapping(value = "/checkuser", method = RequestMethod.POST)
	public ModelAndView check(@ModelAttribute("user") User user, HttpServletRequest request) {
		String path = null;
		HttpSession session = request.getSession();
		User result = service.checkuser(user);
		if (result == null) {
			System.out.println("no user");
			path = "Login";
		} else if (result.getRole().equals("admin")) {
			path = "AdminHome";
		} else if (result.getRole().equals("user")) {
			session.setAttribute("userid", result.id);
			session.setAttribute("accountno", result.accountno);
			session.setAttribute("amount", result.balance);
			path = "UserHome";
		}
		return new ModelAndView(path);

	}
	
	//logout
	@RequestMapping(value = "/logout")
	public ModelAndView logout(@ModelAttribute("user") User user) {
		return new ModelAndView("Login");
	}

	// customer function

	// redirect to addcustomer page

	@RequestMapping(value = "/addcustomer")
	public ModelAndView addcustomer(@ModelAttribute("user") User user) {
		// System.out.println("login");
		return new ModelAndView("AddCustomer");
	}

	// save customer
	@RequestMapping(value = "/savecustomer", method = RequestMethod.GET)
	public ModelAndView save( @ModelAttribute("user") User user, HttpServletRequest request) {
		if (user.id == 0) {
			// System.out.println("inside save customer");
			service.saveCustomer(user);
			return new ModelAndView("redirect:/viewcustomer");
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			service.UpdateCustomer(user);
			return new ModelAndView("redirect:/viewcustomer");
		}

	}
	// edit customer

	@RequestMapping(value = "/Editcustomer", method = RequestMethod.GET)
	public ModelAndView Edit(@ModelAttribute("user") User user, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Edit User");
		User result = service.getUserDetail(id);
		System.out.println(result);
		return new ModelAndView("AddCustomer", "user", result);

	}

	// view customer

/*	@RequestMapping(value = "/viewcustomer", method = RequestMethod.GET)
	
	public ModelAndView view(@ModelAttribute("user") User user,@PathVariable Map<String,String> pathvariable , HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("redirect:/showcustomer");

	}*/
	
	@RequestMapping(value = {"/viewcustomer", "/{page}"}, method = RequestMethod.GET)
	public ModelAndView viewCustomer(@ModelAttribute("user") User user,@PathVariable Map<String,String> pathvariable , HttpServletRequest request, HttpServletResponse response) {
		//List<User> customer = service.getallCustomer();
		//pagination
	ModelAndView mv = new ModelAndView();
		 PagedListHolder<User> userList;
	        String page= pathvariable.get("page");
	        if(page == null) {
	            userList = new PagedListHolder<User>();
	            List<User> customer = service.getallCustomer();
	            // Setting the source for PagedListHolder
	            userList.setSource(customer);
	            userList.setPageSize(2);
	            // Setting PagedListHolder instance to session
	            request.getSession().setAttribute("userList", userList);
	        }else if(page.equals("prev")) {
	            // get the user list from session
	            userList = (PagedListHolder<User>)request.getSession().getAttribute("userList");
	            // switch to previous page
	            userList.previousPage();
	        }else if(page.equals("next")) {
	            userList = (PagedListHolder<User>)request.getSession().getAttribute("userList");
	            // switch to next page
	            userList.nextPage();
	        }else {
	            int pageNum = Integer.parseInt(page);
	            userList = (PagedListHolder<User>)request.getSession().getAttribute("userList");
	            // set the current page number
	            // page number starts from zero in PagedListHolder that's why subtracting 1
	            userList.setPage(pageNum - 1);
	        }
	        
		//end of pagination
	        mv.setViewName("ViewCustomer");
	        return mv;
	}
	
	

	// Transaction function

	// addtransaction

	@RequestMapping(value = "/addtransaction")
	public ModelAndView addtransaction(@ModelAttribute("transaction") Transaction transaction) {
		// System.out.println("login");

		return new ModelAndView("AddTransaction");
	}

	// maketransaction

	@RequestMapping(value = "/maketransaction",method = RequestMethod.POST)
	public ModelAndView maketransaction(@ModelAttribute("transaction") Transaction transaction,HttpServletRequest request) {
		System.out.println("transaction began");
		LocalDate date = LocalDate.now();
		transaction.setTransactiondate(date);
		String response = service.saveTransaction(transaction,request);
		if(response == "success"){
			System.out.println(transaction.transactiondate);
			return new ModelAndView("redirect:/viewtransaction");
		}else{
			return new ModelAndView("redirect:/addtransaction");
		}
		
	}
	
	//viewtransaction
	@RequestMapping(value = "/viewtransaction", method = RequestMethod.GET)
	public ModelAndView viewtransaction(@ModelAttribute("transaction") Transaction transaction,HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("userid");
		System.out.println(id);
		List<Transaction> result = service.getallofCustomer(id);
		//System.out.println(result);
		return new ModelAndView("ViewTransaction", "transaction", result);

	}
	

}
