package com.capg.paymentwallet.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.WalletTransaction;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class Client {

	IAccountService service = new AccountServiceImpl();
	CustomerBean customer = new CustomerBean();
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		char ch;
		Client client = new Client();
		do {
			System.out.println("========Payment wallet application========");
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance ");
			System.out.println("3. Deposit ");
			System.out.println("4. Withdraw ");
			System.out.println("5. Fund Transfer");
			System.out.println("6. Print Transactions");
			System.out.println("7. Exit");
			System.out.println("Choose an option");
			int option = client.scanner.nextInt();

			switch (option) {
			case 1:
				client.create();
				break;
			case 2:
				client.showbalance();

				break;

			case 3:
				client.deposit();

				break;

			case 4:
				client.withdraw();

				break;

			case 5:
				client.fundtransfer();

				break;

			case 6:
				client.printTransaction();

				break;
			case 7:
				System.exit(0);

				break;

			default:
				System.out.println("invalid option");
				break;
			}

			System.out.println("Do you want to continue press Y/N");
			ch = client.scanner.next().charAt(0);

		} while (ch == 'y' || ch == 'Y');

	}
	

	void create() throws CustomerException, Exception {

		System.out.print("\tEnter Customer firstname\t:");
		String fname = scanner.next();

		System.out.print("\tEnter Customer lastname\t\t:");
		String lname = scanner.next();

		System.out.print("\tEnter  Customer  email id\t:");
		String email = scanner.next();

		System.out.print("\tEnter  Customer  phone number\t:");
		String phone = scanner.next();

		System.out.print("\tEnter  Customer PAN number\t:");
		String pan = scanner.next();

		System.out.print("\tEnter  Customer  address:\t\t");
		String address = scanner.next();

		CustomerBean customerBean = new CustomerBean();
		customerBean.setAddress(address);
		customerBean.setEmailId(email);
		customerBean.setPanNum(pan);
		customerBean.setPhoneNo(phone);
		customerBean.setFirstName(fname);
		customerBean.setLastName(lname);
		System.out.print("\tEnter Date of Opening (DD/MM/YYYY)\t:");
		String accDateInput = scanner.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfOpeining = sdf.parse(accDateInput);
		System.out.print("\tEnter balance to create account\t:");
		double balance = scanner.nextDouble();
		AccountBean accountBean = new AccountBean();
		/* accountBean.setAccountId(accId); */
		accountBean.setBalance(balance);
		accountBean.setInitialDeposit(balance);
		accountBean.setCustomerBean(customerBean);
		System.out.println(accountBean);
		try{
		boolean result = service.createAccount(accountBean);
		if (result) {
			System.out
					.println("\n\nCongratulations Customer account has been created successfully...\t\t\n\n");}
		}catch(CustomerException ce){
		System.out.println(ce.getMessage());	
		}
	
	}

	void showbalance() throws CustomerException, Exception {
		System.out.println("Enter Account ID");
		int accId = scanner.nextInt();
		AccountBean accountBean = service.findAccount(accId);
		if (accountBean == null) {
			System.out.println("Account Does not exist");
			return;
		}
		double balance = accountBean.getBalance();
		System.out.print("\tFirst Name\t:"
				+ accountBean.getCustomerBean().getFirstName() + "\n "
				+ "\tLast Name\t:"
				+ accountBean.getCustomerBean().getLastName() + "\n "
				+ "\t Email Id\t:" + accountBean.getCustomerBean().getEmailId()
				+ "\n " + "\tPhone no\t:"
				+ accountBean.getCustomerBean().getPhoneNo() + " \n");
		System.out.println("Your balance is: " + balance);
	}

	void deposit() throws Exception {
		System.out.print("\tEnter Account ID\t:");
		int accId = scanner.nextInt();
		AccountBean accountBean = service.findAccount(accId);
		System.out.print("\tFirst Name\t:"
				+ accountBean.getCustomerBean().getFirstName() + "\n "
				+ "\tLast Name\t:"
				+ accountBean.getCustomerBean().getLastName() + "\n "
				+ "\t Email Id\t:" + accountBean.getCustomerBean().getEmailId()
				+ "\n " + "\tPhone no\t:"
				+ accountBean.getCustomerBean().getPhoneNo() + " \n"
				+ "\t Current Balance:" + accountBean.getBalance());
		System.out.print("\n\nEnter amount that you want to deposit\t\t:");
		double depositAmt = scanner.nextDouble();
		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(1);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(depositAmt);
		wt.setBeneficiaryAccountBean(null);
		accountBean.addTransation(wt);

		if (accountBean == null) {
			System.out.println("Account Does not exist");
			return;
		}

		boolean result = service.deposit(accountBean, depositAmt);
		if (result) {
			System.out.println("\n\nDeposited Money into Account\t\t\n\n ");
			System.out.println("Your total balance after deposted is: "
					+ accountBean.getBalance());
		} else {
			System.out.println("\n\nNOT Deposited Money into Account\t\t\n\n ");
		}
	}

	void withdraw() throws Exception {
		System.out.println("Enter Account ID");
		int accId = scanner.nextInt();
		AccountBean accountBean = service.findAccount(accId);
		System.out.print("\tFirst Name\t:"
				+ accountBean.getCustomerBean().getFirstName() + "\n "
				+ "\tLast Name\t:"
				+ accountBean.getCustomerBean().getLastName() + "\n "
				+ "\t Email Id\t:" + accountBean.getCustomerBean().getEmailId()
				+ "\n " + "\tPhone no\t:"
				+ accountBean.getCustomerBean().getPhoneNo() + " \n"
				+ "\t Current Balance:" + accountBean.getBalance());
		System.out.println("\n\nEnter amount that you want to withdraw\t:");
		double withdrawAmt = scanner.nextDouble();
		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(2);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(withdrawAmt);
		wt.setBeneficiaryAccountBean(null);
		accountBean.addTransation(wt);
		if (accountBean == null) {
			System.out.println("\n\nAccount Does not exist\n\n");
			return;
		}
		boolean result = service.withdraw(accountBean, withdrawAmt);
		if (result) {
			System.out.println("\n\nWithdaw Money from Account done\t\t:\n\n");
			System.out.println("Your total balance after withdrawal is: "
					+ accountBean.getBalance());
		} else {
			System.out.println("\n\nWithdaw Money from Account -Failed\n\n ");
		}

	}

	void fundtransfer() throws Exception {
		System.out.println("Enter Account ID to Transfer Money From");
		int srcAccId = scanner.nextInt();
		AccountBean accountBean1 = service.findAccount(srcAccId);
		System.out.println("Enter Account ID to Transfer Money to");
		int targetAccId = scanner.nextInt();
		AccountBean accountBean2 = service.findAccount(targetAccId);
		System.out.println("Enter amount that you want to transfer");
		double transferAmt = scanner.nextDouble();
		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(3);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(transferAmt);
		wt.setBeneficiaryAccountBean(accountBean2);
		accountBean1.addTransation(wt);
		boolean result = service.fundTransfer(accountBean1, accountBean2,
				transferAmt);
		if (result) {
			System.out.println("\n\nTransfering Money from Account done\n\n");
		} else {
			System.out
					.println("\n\nTransfering Money from Account Failed \n\n");
		}
	}

	void printTransaction() throws Exception {
		System.out
				.println("Enter Account ID (for printing Transaction Details");
		int accId = scanner.nextInt();
		AccountBean accountBean = service.findAccount(accId);
		List<WalletTransaction> transactions = accountBean.getAllTransactions();
		System.out.println(accountBean);
		System.out.println(accountBean.getCustomerBean());
		System.out
				.println("-------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Transcation Type" + "" + "Transcation Details"
				+ " " + "Amount\n\n");
		System.out
				.println("-------------------------------------------------------------------------------------------------------------------------------------");
		for (WalletTransaction wt : transactions) {
			System.out.println("\tFirst Name\t:"
					+ accountBean.getCustomerBean().getFirstName() + "\n "
					+ "\tLast Name\t:"
					+ accountBean.getCustomerBean().getLastName() + "\n "
					+ "\t Email Id\t:"
					+ accountBean.getCustomerBean().getEmailId() + "\n "
					+ "\tPhone no\t:"
					+ accountBean.getCustomerBean().getPhoneNo() + " \n");
			String str = "";
			if (wt.getTransactionType() == 1) {

				str = str + "DEPOSIT" + " ";
			}
			if (wt.getTransactionType() == 2) {
				str = str + "WITHDRAW" + " ";
			}
			if (wt.getTransactionType() == 3) {
				str = str + "FUND TRANSFER" + " ";
			}

			str = str + "\t\t" + wt.getTransactionDate();
			str = str + "\t\t" + wt.getTransactionAmt();
			System.out.println(str);
		}
		System.out
				.println("------------------------------------------------------------------");

	}

}
