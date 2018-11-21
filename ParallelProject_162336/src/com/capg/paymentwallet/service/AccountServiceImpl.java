package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;

public class AccountServiceImpl implements IAccountService{
	IAccountDao dao=new AccountDAOImpl();
	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		
		
		return dao.createAccount(accountBean);
		
	}

	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		
		return dao.findAccount(accountId);
		
	}

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {
		
		
		return dao.deposit(accountBean, depositAmount);
	
		
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		
		return dao.withdraw(accountBean, withdrawAmount);
		
		
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean,
			AccountBean beneficiaryAccountBean, double transferAmount)
			throws Exception {
		
	/*	
		boolean result1=dao.updateAccount(transferingAccountBean);
		boolean result2=dao.updateAccount(beneficiaryAccountBean);
		return result1 && result2;*/

		return dao.fundTransfer(transferingAccountBean, beneficiaryAccountBean, transferAmount);
	}

	
	
	public boolean validateDetails(AccountBean accountBean) throws CustomerException{
		
		boolean isValid=true;
		if(!( accountBean.getCustomerBean().getFirstName().matches("[A-Za-z0-9]{4,}"))){
			
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR1);
			
		}
		if(!( accountBean.getCustomerBean().getLastName().matches("[A-Za-z0-9]{4,}"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR2);
		}
		if(!(accountBean.getCustomerBean().getEmailId().matches("[a-zA-Z0-9-.]*@[a-zA-Z0-9]+([.][A-Za-z)]+)+"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR3);
		}
		if(!(accountBean.getCustomerBean().getPanNum().matches("[A-Z0-9]{10}"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR4);
		}
		if(!(accountBean.getCustomerBean().getPhoneNo().matches("^[7-9][0-9]{9}"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR5);
		}
		if(!(accountBean.getBalance()>500)){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR6);
		}
		if(!( accountBean.getCustomerBean().getAddress().matches("[A-Za-z0-9]{1,20}"))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR7);
		}
		
		return isValid;
		
	}

}
