package com.capg.paymentwallet.dao;

import javax.persistence.EntityManager;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;

public class AccountDAOImpl implements IAccountDao {

	EntityManager em;

	@Override
	public boolean createAccount(CustomerBean customerBean) throws CustomerException {
		try {

			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();

			em.persist(customerBean);

			em.getTransaction().commit();
			JPAManager.closeResources(em);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	
	/*private boolean updateAccount(AccountBean accountBean) throws Exception {
		try {
			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();

			em.merge(accountBean);

			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}*/

	@Override
	public CustomerBean findAccount(int custId) throws Exception {
		try {
		/*	AccountBean bean=dao.findAccount(accountId);*/
			em = JPAManager.createEntityManager();
			CustomerBean customerBean1 = em.find(CustomerBean.class, custId);
			JPAManager.closeResources(em);
			return customerBean1;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean deposit(CustomerBean customerBean, double depositAmount) throws Exception {
		try {
		customerBean.getAccountBean().setBalance(customerBean.getAccountBean().getBalance()+depositAmount);
		
			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();

			em.merge(customerBean);

			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean withdraw(CustomerBean customerBean, double withdrawAmount) throws Exception {
		try {
			if(withdrawAmount>0 && withdrawAmount<=customerBean.getAccountBean().getBalance()){
			customerBean.getAccountBean().setBalance(customerBean.getAccountBean().getBalance()-withdrawAmount);
			
				this.em = JPAManager.createEntityManager();
				em.getTransaction().begin();
				
				em.merge(customerBean);

				em.getTransaction().commit();
				JPAManager.closeResources(em);
				return true;
			}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		return false;
			}

	@Override
	public boolean fundTransfer(CustomerBean transferingCustomerBean, CustomerBean beneficiaryCustomerBean,
			double transferAmount) throws Exception {

		try {
			if(transferAmount <= transferingCustomerBean.getAccountBean().getBalance()){
				if(!(transferingCustomerBean.equals(beneficiaryCustomerBean))){
	
			transferingCustomerBean.getAccountBean().setBalance(transferingCustomerBean.getAccountBean().getBalance()-transferAmount);
			beneficiaryCustomerBean.getAccountBean().setBalance(beneficiaryCustomerBean.getAccountBean().getBalance()+transferAmount);
				this.em = JPAManager.createEntityManager();
				em.getTransaction().begin();
				
				em.merge(transferingCustomerBean);
				em.merge(beneficiaryCustomerBean);
				em.getTransaction().commit();
				JPAManager.closeResources(em);
				return true;
				}
				else{
					System.out.println("\n\n\t\tTwo customer ids cannot be same..!!");
					return false;
				}
			}
			else{
				System.out.println("\n\n\t\tfund transfer not possible..!! Insufficient Balance..!!");
				return false;
			}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		
	}

}
