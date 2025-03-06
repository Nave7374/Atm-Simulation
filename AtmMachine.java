package Com.Atm.Simulation;

import java.util.Scanner;

public class AtmMachine {

	/*	
	 *	This is the Execution file with main method of ATM simulation
	 * 	To Login to the Account or access the ATM machine pin is needed (The pin is : "1234")
	 * 	This ATM machine simulation includes 
	 * 	1. Display balance, 
	 * 	2. Withdraw money,
	 * 	3. Deposit money,
	 * 	4. Pin change,
	 * 	5. Transaction history.
	 * 	By selecting the respective options You can access the ATM machine properly. 
	 * 	This class act's like front-end.
	*/
	
	private static Scanner s = new Scanner(System.in);
	private	static AtmService atm = new AtmService(1234, 10000);
	private static int option=0;
	private static boolean isLogedin = false;
	
	public static void main(String[] args) {
		
		while(!isLogedin) {
			System.out.print("Enter the Pin : ");
			if(atm.isPin(s.nextInt())) isLogedin=true;
		}
		System.out.println("Welcome to the Atm ");
		System.out.println("press 1 to Check balance ");
		System.out.println("press 2 to Deposit Money ");
		System.out.println("press 3 to Withdraw Money");
		System.out.println("press 4 to Pin change");
		System.out.println("press 5 for Transaction History");
		System.out.println("press 6 to Exit");
		do{
			System.out.println("Select the Option");
			option = s.nextInt();
			if(option==10)option-=1;
			else if(option <= 0 || option>6) System.out.println("Enter the Correct Option.");
			else {
				switch(option) {
				case 1 : atm.displayBalance();
						break;
				case 2 : atm.deposit();
						break;
				case 3 : atm.withdraw();
						break;
				case 4 : atm.changePin();
						break;
				case 5 : atm.transactionHistory();
						break;
				case 6 :atm.exit(); 
						option=10;
						break;
				}
			}
		}while(option!=10);
	}
	
}
class AtmService {

	/*
	 * 	In this class all the services are added.
	 */
	
	
	private double bal;
	private StringBuilder transactionhistory;
	private int pin;
	private int count;
	private static Scanner s = new Scanner(System.in);
	
	public AtmService(int pin,double bal) {
		
//		Constructor is used to initialize the properties, which is called in AtmMachine class.
		
		this.bal=bal;
		this.pin=pin;
		transactionhistory = new StringBuilder();
		count=0;
	}
	
	public void withdraw(){
		
//		This method will perform the Withdraw Logic
		
		System.out.print("Enter the Amount to Withdraw : ");
		int bal = s.nextInt();
		if(this.bal<bal) {
			System.out.println("Insufficient Balance");
			return;
		}
		this.bal-=bal;
		transactionhistory.append(++count + ". " + bal + " was withdrawn \n");
		System.out.println(bal + " is Withdrawn");
	}
	
	public void deposit() {
		
//		This method will perform the Deposit Logic
		
		System.out.print("Enter the Amount to Deposit : ");
		int bal = s.nextInt();
		this.bal+=bal;
		transactionhistory.append(++count + ". " +bal + " was Deposited \n");
		System.out.println(bal+" is Deposited");
	}
	
	public boolean isPin(int pin) {
		
//		This method will check the entered pin is correct or not.
		
		return this.pin==pin;
	}
	
	public void transactionHistory() {
		
//		This method will print the transaction history which was added for every task that is performed.
		
		System.out.println(transactionhistory);
	}
	
	public void displayBalance() {
		
//		This method will display the balance
		
		System.out.println(bal); 
	}
	
	public void changePin() {
		
//		This method will perform pin change Logic
		
		System.out.print("Enter the pin to change : ");
		int pin = s.nextInt();
		if(!isPin(pin)) {
			this.pin=pin;
			System.out.println("Pin is changed");
			transactionhistory.append(++count +". Pin has been changed \n");
		}
		else System.out.println("Pin already Exist.");
	}
	
	public void exit() {
		
//		This method is used for Exiting the application. It is not mandatory to put the Thread on sleep. 
		
		try {
			System.out.print("Exiting");
			Thread.sleep(600);
			System.out.print("...");
			Thread.sleep(600);
			System.out.print("...");
			Thread.sleep(600);
			System.out.println("...");
			Thread.sleep(1000);
			System.out.println("Thanks For Using the Atm Machine");
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
}