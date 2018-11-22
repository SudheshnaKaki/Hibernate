package com.capg.paymentwallet.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;

public interface IAccountDao {

	 public boolean createAccount(CustomerBean customerBean) throws  CustomerException ;
	    public CustomerBean findAccount(int custId) throws Exception;
	    public boolean deposit(CustomerBean customerBean,double depositAmount) throws Exception ;
	    public boolean withdraw(CustomerBean customerBean,double withdrawAmount) throws Exception;
	    public boolean fundTransfer(CustomerBean transferingCustomerBean, CustomerBean beneficiaryCustomerBean, double transferAmount) throws Exception ;
	   
  
	
	 
    
}
