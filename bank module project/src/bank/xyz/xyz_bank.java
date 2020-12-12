package bank.xyz;

import java.util.*;

import bank.xyz.xyz_bank.UserHome.Transaction;

public class xyz_bank {
	Scanner s = new Scanner(System.in);
	Scanner sInt= new Scanner(System.in);
	int scanInt() {
		Scanner scanInt= new Scanner(System.in);
		int returnInt=0;
		try {
		returnInt=scanInt.nextInt();
		}
		catch (Exception e) {
			System.out.println("please enter numeric values"); 
			returnInt=scanInt();
		}
		return returnInt;
		}
//	String scan() {
//		Scanner scan = new Scanner(System.in);
//		String returnString = "";
//		try {
//		returnString =scan.nextLine();
//		}
//		catch (Exception e) {
//			System.out.println("please enter numeric values"); 
//			returnString=scan();
//		}
//		finally{
//			scan.close();
//		}
//		return returnString;
//		}
	static HashSet<NewUser> list= new HashSet<NewUser>();
	static int minBalance = 500;
	
	static NewUser currentLogin;
	
	
	
	public static void main(String [] args) {
	
	xyz_bank bank = new xyz_bank();
	bank.intro();

}

 void intro() {
	System.out.println("\t\t welcome to XYZ bank\n\n");
	
	System.out.println("\t\t 1. login \n \t\t 2. register \n");
	System.out.println("\t(please press 1 or 2 then press enter)");	

	int login_options =this.scanInt();
//	int login_options =sInt.nextInt();

	 
	 if (login_options==1)
		 this.loginPage();
	 else if (login_options==2)
		 this.register();
	
	 else {
		 System.out.println("please provide valid option");
		 System.out.println("*************************************************************");
		 this.intro();
	 }
	 
}


private void loginPage() {
//	s.next();
	System.out.println("*************************************************************");
	 System.out.println("login");
	 System.out.println("enter account number");
	 
	 String accountNo = s.nextLine();
	 System.out.println(accountNo);
	 System.out.println("enter password");
	 String accountPassword = s.nextLine();
	 System.out.println(accountPassword);
	 
	 System.out.println("*************************************************************");
//		this.intro();
	 validateCredentials(accountNo,accountPassword);
}
public void validateCredentials(String accountNo, String password) {
//Iterator<NewUser> i= list.iterator();	
	if (!list.isEmpty())
	{for (NewUser data : list) {

		if (data.accountNo.equals(accountNo) && data.password.equals(password)) {
			currentLogin = data;
//			new UserHome().acDetails(data);
//			xyz_bank.UserHome.userMenu();
	new UserHome().userMenu();
		}
		else {
			currentLogin = null;
//		System.out.println("account no or password doesnt match");
//		this.intro();
		}
}
	if (currentLogin==null)
	{
		System.out.println("login failed. try again");
		loginPage();
	}
	}
	
	else {
		this.intro();
	}
}


public void register() {

	 System.out.println("*************************************************************");
	 System.out.println("register");
	 System.out.println("name");
	 String name = s.nextLine() ;
	 System.out.println("address");
	 String address = s.nextLine();
	 System.out.println("mobile ");
	 String mobile = s.nextLine();
	 System.out.println("email");
	 String email = s.nextLine();
	 System.out.println("password");
	 String password = s.nextLine();
	 
	 System.out.println("*************************************************************");
//		 this.intro();

		NewUser n= new NewUser(name,address,mobile,email);
		n.generateNo(String.valueOf(n.hashCode()),password);
		
		list.add(n);
		System.out.print(list);
		
		this.intro();
}

class NewUser{
	String name,address,mobile,email,accountNo,password;
	int balance;
	LinkedList <Transaction> transactionDetails = new LinkedList();
	NewUser(String n,String a,String m,String e) {
		 System.out.println(n +" "+a + " " + m + " " + e);

		 this.name=n;
		 this.address=a;
		 this.mobile=m;
		 this.email=e;
		

		 
	}
	public void generateNo(String code, String password) {
		String bankCode="20201210";		
		accountNo = bankCode+code ;		
		this.password = password;
		this.balance = xyz_bank.minBalance;
		System.out.print("your details are registered and the account number is :"+ accountNo +"and the balance is : "+ this.balance);
	
	}
}

public class UserHome{
	
	
	void userMenu() {
		System.out.println("*************************************************************");
		System.out.println("please choose a valid option from 1 to 5");
		System.out.println("[1] account details ");
		System.out.println("[2] deposit money");
		System.out.println("[3] withdrawal");
		System.out.println("[4] transfer");
		System.out.println("[5] transaction details");
		System.out.println("[6] logout");
		System.out.println();
		int menuOption= sInt.nextInt();
		switch(menuOption) {
			case 1 :{
				this.acDetails(currentLogin);
				break;
			}
			case 2 :{
				this.deposit();
				break;
			}
			case 3 :{
				this.withdrawal();
				break;
			}
			case 4 :{
				this.transfer();
				break;
			}
			case 5 :{
				this.transactionDetails();
				break;
			}
			case 6:{
				this.logout();
				break;
			}
		default : 		System.out.println("please choose a valid option from 1 to 5");
		}
		System.out.println("*************************************************************");
		
		
	}
	
	//view account details
	
	void acDetails(NewUser data) {
	System.out.println("*************************************************************");
	System.out.println("account Holder : " + data.accountNo);
	System.out.println("name           : " + data.name );
	System.out.println("address        : " + data.address);
	System.out.println("mobile number  : " + data.mobile);
	System.out.println("email          : " + data.email);
	System.out.println();
	System.out.println("*************************************************************");
	}
	
	//deposit of money
	
	void deposit() {
		System.out.println("*************************************************************");
		System.out.println("enter amount to deposit");
		int depositingAmount = sInt.nextInt();
//		int availBalance =depositingAmount + currentLogin.balance;
		currentLogin.transactionDetails.add(new Transaction(depositingAmount,"depositing"));
//		currentLogin.balance += depositingAmount;
//System.out.println(" your current available balance : "+ currentLogin.balance);
//		System.out.println("*************************************************************");
		this.mainMenuOption();
		}
	//withdrawal of money
void mainMenuOption() {
	System.out.println("press 1 for main menu or press 2 for logOut");
	int option;
	boolean b;
	do {
	option=sInt.nextInt();
		if (option==1)
			{this.userMenu();
			break;
			}
		else if(option == 2)
			{this.logout();
			break;
			}
		b=option != 1 ||option !=2;
	}
	while( b);
}
	void withdrawal() {
		System.out.println("*************************************************************");
		System.out.println("enter the amount to withdraw");
		int withdrawingAmount = scanInt();
		boolean eligible= (currentLogin.balance - withdrawingAmount)>= xyz_bank.minBalance;
		if(eligible) {
		
		System.out.println("please enter password");
		String p =s.nextLine();
		if(currentLogin.password.equals(p)) {
			currentLogin.transactionDetails.add(new Transaction(withdrawingAmount,"withdrawing"));
//		currentLogin.balance -= withdrawingAmount;
//		System.out.println(" your current available balance : "+ currentLogin.balance);
//		System.out.println("*************************************************************");
//		Transaction temp= new Transaction(withdrawingAmount,"withdrawing");
//			currentLogin.transactionDetails.add(temp);
//			Transaction t=currentLogin.transactionDetails.get(0);
//			System.out.println(t.changesInAmount);
//			System.out.println(currentLogin.transactionDetails.get(0));
//			System.out.println(temp);
//			Object t = currentLogin.transactionDetails.get(1);
//			System.out.println(t);
//			System.out.println(t.to);
			this.mainMenuOption();
		}
		else {
			System.out.println("invalid password");
			System.out.println("*************************************************************");

			this.logout();
		}
		}
		else {
			System.out.println("you are eligible to withdraw Rs."
					+ (currentLogin.balance-minBalance) 
					+ " only.\n your transaction declined");
			System.out.println("*************************************************************");
this.withdrawal();
		}
		}
	
//	transfer amount to another account
	
	void transfer() {
		NewUser recipient=null;
		System.out.println("*************************************************************");
		s.nextLine();
		System.out.println("enter the account number to which amount should be transfer");
		String recipientNo= s.nextLine();
		for(NewUser data : list) {
			if (data.accountNo.equals(recipientNo)) {
				recipient=data;
				
				break;
			}
			else {
				recipient=null;
//				System.out.println("recipient account no entered is invalid");
				
			}
		}
		
		System.out.println("enter the amount to transfer");
		int transferAmount = sInt.nextInt();
		boolean eligible= (currentLogin.balance - transferAmount)>= xyz_bank.minBalance;
		if(eligible) {

		System.out.println("please enter password");
		if(currentLogin.password.equals( s.next())) {
			Transaction t =new Transaction(transferAmount,recipient);
			currentLogin.transactionDetails.add(t);
			recipient.transactionDetails.add(t);
	this.userMenu();
		}
		else
			{
			System.out.println("invalid password");
			System.out.println("*************************************************************");
this.mainMenuOption();
			
			}
		}
		else {
			System.out.println("you are eligible to transfer Rs."
					+ (currentLogin.balance-minBalance) 
					+ " only.\n your transaction declined");
			System.out.println("*************************************************************");
this.transfer();
			
		}
		}

	// get statement about trasaction
		
	void transactionDetails() {
		System.out.println("*************************************************************");
		 int transactionListSize = currentLogin.transactionDetails.size();
//		 System.out.println(transactionListSize);
		for (int i = transactionListSize-1 ; i>=0 ; i--) {
//			System.out.println(i);
			Transaction t = currentLogin.transactionDetails.get(i);
			if (t.transactionType.equals("withdrawing")) {
			System.out.println("Rs."+t.changesInAmount + " has been withdrawn from your account");
			}
			else if (t.transactionType.equals("depositing")) {
				System.out.println("Rs."+t.changesInAmount + " has been deposited to your account");
				
			}
			else if (t.transactionType.equals("transfer")) {
				System.out.println("Rs."+t.changesInAmount + " has been transfered from account no : "+t.from +" to account no :" + t.to);
				
			}
		}
		System.out.println("*************************************************************");
this.mainMenuOption();	
	}
		
	//logout
	void logout() {
		System.out.println("*************************************************************");

		currentLogin=null;
		System.out.println("you had been successfully logged out \n "
				+ "thank you for using our baking services");
		new xyz_bank().intro();
		System.out.println("*************************************************************");

	}
	
	
	class Transaction{
		int changesInAmount;
		String transactionType;
		NewUser to,from;
		
		Transaction(){
			
		}
		Transaction(int changesInAmount,String transactionType){
			if (transactionType == "withdrawing")
				currentLogin.balance -= changesInAmount;
			else if (transactionType == "depositing")
				currentLogin.balance +=changesInAmount;
			this.transactionType = transactionType;  
			this.changesInAmount = changesInAmount;
			
			System.out.println(" your current available balance : "+ "Rs."+currentLogin.balance);
			System.out.println("*************************************************************");
				
		}
		Transaction(int changesInAmount,NewUser to){
			this.transactionType = "transfer";
			this.to = to;
			this.changesInAmount = changesInAmount;
			this.from = currentLogin;
			this.to.balance += changesInAmount;
			this.from.balance -= changesInAmount;

			System.out.println(" your current available balance : "+"Rs."+ currentLogin.balance);
			System.out.println("your transaction of amount "+ changesInAmount+" to "+ to.accountNo +" has been processed successfully");
			System.out.println("*************************************************************");
				
		}
	}
	
	
	
}



}
