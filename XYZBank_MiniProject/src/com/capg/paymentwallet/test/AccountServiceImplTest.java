package com.capg.paymentwallet.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class AccountServiceImplTest {
	
	static IAccountService service = null;
	static AccountBean accBean = null;
	static CustomerBean custBean = null;
	@BeforeClass
	public static void createInstance(){
		service= new AccountServiceImpl();
		accBean = new AccountBean();
		custBean = new CustomerBean();
	}
	
	@Test(expected = CustomerException.class)
	public void testForValidFirstName() throws Exception{
		custBean.setFirstName("ppp");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setBalance(10000);
		custBean.setAccountBean(accBean);
		boolean result = service.createAccount(custBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidLastName() throws Exception{
		custBean.setFirstName("Priya");
		custBean.setLastName("Jo");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		custBean.setAccountBean(accBean);
		boolean result = service.createAccount(custBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidPhoneNumber() throws Exception{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("122333");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		custBean.setAccountBean(accBean);
		boolean result = service.createAccount(custBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidEmail() throws Exception{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priya");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		custBean.setAccountBean(accBean);
		boolean result = service.createAccount(custBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidAddress() throws Exception{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("122333");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress(null);
		custBean.setAccountBean(accBean);
		boolean result = service.createAccount(custBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidBalance() throws Exception{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("122333");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		custBean.setAccountBean(accBean);
		boolean result = service.createAccount(custBean);
		assertFalse(result);
	}
	
	@Test
	public void testDeposit() throws Exception{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setBalance(10000);
		custBean.setAccountBean(accBean);
		service.deposit(custBean, 2000);
		assertEquals(12000,custBean.getAccountBean().getBalance(),0);
	
}

	@Test
	public void testWithdraw() throws Exception {
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setBalance(10000);
		custBean.setAccountBean(accBean);
		service.withdraw(custBean, 2000);
		assertEquals(8000,custBean.getAccountBean().getBalance(),0);
	}
	
	/*@Test
	public void testWithdrawForNegativeCase() throws Exception {
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		service.withdraw(accBean, 12000);
		assertEquals(10000,accBean.getBalance(),0);
	}
*/
	@Test
	public void testFundTransfer() throws Exception {
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setBalance(10000);
		custBean.setAccountBean(accBean);
		CustomerBean custBean1 = new CustomerBean();
		AccountBean accBean1 = new AccountBean();
		custBean1.setFirstName("John");
		custBean1.setLastName("Joseph");
		custBean1.setPhoneNo("9988776688");
		custBean1.setEmailId("johnjoseph@gmail.com");
		custBean1.setPanNum("KLM7890654");
		custBean1.setAddress("Chennai");
		accBean1.setBalance(10000);
		custBean1.setAccountBean(accBean1);
		service.fundTransfer(custBean, custBean1, 5000);
		assertEquals(15000,custBean.getAccountBean().getBalance(),0);
	}

	

}
