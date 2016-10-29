import javax.swing.JOptionPane;
import java.util.Scanner;

public class Harness {
	String make;
	int modelNumber;
	
	int noOfTimesUsed;
	String lastCheckInstructor;
	
	boolean onLoan;
	String onLoanMember;
	
	public Harness(String harnessMake, int harnessModel, int timesUsed, String instructorName, boolean loaned)
	{
		make = harnessMake;
		modelNumber = harnessModel;
		
		noOfTimesUsed = timesUsed;
		
		lastCheckInstructor = instructorName;
		
		onLoan = loaned;
		
		if (onLoan)
		{
			String userInput = JOptionPane.showInputDialog("Please enter the name of the club member who borrowed the harness:");
			Scanner inputScanner = new Scanner(userInput);
			
			onLoanMember = inputScanner.nextLine();
			inputScanner.close();
		}
		else
		{
			onLoanMember = null;
		}
		
	}
	
	public Harness(String harnessMake, int harnessModel, String instructorName)
	{
		make = harnessMake;
		modelNumber = harnessModel;
		
		noOfTimesUsed = 0;
		
		lastCheckInstructor = instructorName;
		
		onLoan = false;
		onLoanMember = null;
	}
	
	public boolean checkHarness(String instructorName) {
		if (!onLoan)
		{
			noOfTimesUsed = 0;
			lastCheckInstructor = instructorName;
			
			return true;
		}
		
		return false;
	}
	
	public void isHarnessOnLoan() {
		if (onLoanMember != null)
		{
			onLoan = true;
		}
		else
		{
			onLoan = false;
		}			
	}
	
	public boolean canHarnessBeLoaned() {
		if (noOfTimesUsed < 25 && !onLoan)
		{
			return true;
		}
		else if (noOfTimesUsed >= 25 && !onLoan)
		{
			JOptionPane.showMessageDialog(null, "This harness needs to be safety checked.");
			
			return false;			
		}
		else
		{
			return false;
		}
	}
	
	public boolean loanHarness(String memberName) {
		if (canHarnessBeLoaned())
		{
			noOfTimesUsed++;
			onLoanMember = memberName;
			onLoan = true;
			return onLoan;
		}
		else
		{
			return false;
		}		
	}
	
	public boolean returnHarness() {
		if (onLoan) {
			onLoan = false;
			onLoanMember = null;
			return true;
		}
		else
		{
			return false;
		}						
	}
	
	public String toString() {
		String loanState;
		if (onLoan)
		{
			loanState = "Currently on loan to " + onLoanMember + "."; 
		}
		else
		{
			loanState = "Not currently on loan.";
		}
		
		String harnessDescription = make + "\n" + "Model Number: " + modelNumber + "\n" + "No. of Times Used: " + noOfTimesUsed
														+ "\n" + "Last checked by " + lastCheckInstructor + "\n" + loanState + "\n";
		return harnessDescription;
	}		
}
