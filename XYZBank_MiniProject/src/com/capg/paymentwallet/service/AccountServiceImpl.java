package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;

public class AccountServiceImpl implements IAccountService{
	IAccountDao dao=new AccountDAOImpl();
	@Override
	public boolean createAccount(CustomerBean customerBean) throws CustomerException {
		
		if(validateDetails(customerBean)){
		return dao.createAccount(customerBean);
		}
		return false;
	}

	@Override
	public CustomerBean findAccount(int custId) throws Exception {
		
		return dao.findAccount(custId);
		
	}

	@Override
	public boolean deposit(CustomerBean customerBean, double depositAmount)
			throws Exception {
		
		
		return dao.deposit(customerBean, depositAmount);
	
		
	}

	@Override
	public boolean withdraw(CustomerBean customerBean, double withdrawAmount)
			throws Exception {
		
		return dao.withdraw(customerBean, withdrawAmount);
		
		
	}

	@Override
	public boolean fundTransfer(CustomerBean transferingCustomerBean,
			CustomerBean beneficiaryCustomerBean, double transferAmount)
			throws Exception {
		
	/*	
		boolean result1=dao.updateAccount(transferingAccountBean);
		boolean result2=dao.updateAccount(beneficiaryAccountBean);
		return result1 && result2;*/

		return dao.fundTransfer(transferingCustomerBean, beneficiaryCustomerBean, transferAmount);
	}

	
	
	public boolean validateDetails(CustomerBean customerBean) throws CustomerException{
		
		boolean isValid=true;
		if(!( customerBean.getFirstName().matches("[A-Za-z0-9]{4,}"))){
			
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR1);
			
		}
		if(!( customerBean.getLastName().matches("[A-Za-z0-9]{4,}"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR2);
		}
		if(!(customerBean.getEmailId().matches("[a-zA-Z0-9-.]*@[a-zA-Z0-9]+([.][A-Za-z)]+)+"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR3);
		}
		if(!(customerBean.getPanNum().matches("[A-Z0-9]{10}"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR4);
		}
		if(!(customerBean.getPhoneNo().matches("^[7-9][0-9]{9}"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR5);
		}
		
		if(!( customerBean.getAddress().matches("[A-Za-z0-9]{1,20}"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR7);
		}
		
		return isValid;
		
	}

}
