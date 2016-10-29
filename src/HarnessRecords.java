import java.util.ArrayList;
import javax.swing.JOptionPane;

public class HarnessRecords {
	ArrayList<Harness> harnessList;
	
	public HarnessRecords() {
		harnessList = new ArrayList<Harness>();
	}
	
	public HarnessRecords(In in) {
		
		if (in.hasNextLine())
		{			
			int count = 0;
			int harnessesAmount = Integer.parseInt(in.readLine());
			
			harnessList = new ArrayList<Harness>();
			
			while (count < harnessesAmount)
			{
				Harness aHarness = new Harness(in.readLine(), Integer.parseInt(in.readLine()), in.readLine());
				
				harnessList.add(aHarness);
				count++;
			}
		}
	}
	
	public boolean isEmpty() {
		
		if (harnessList.size()>0)
		{
			return false;
		}
		else
		{
			return true;
		}	
	}
	
	
	public Harness addHarness(Harness theHarness) {
		for (int index=0; index<harnessList.size(); index++) {
			Harness aHarness = harnessList.get(index);
			
			if (theHarness.make.equals(aHarness.make) && theHarness.modelNumber == aHarness.modelNumber)
			{
				JOptionPane.showMessageDialog(null, "A Harness of that Make and Model Number already exists.");
				return null;
			}
		}
		harnessList.add(theHarness);	
		return theHarness;
	}
	
	public Harness findHarness(String make, int modelNumber) {
		
		if (harnessList != null)
		{
			for (int index=0; index<harnessList.size(); index++) {
				Harness aHarness = harnessList.get(index);
				
				if (make.equals(aHarness.make) && modelNumber == aHarness.modelNumber)
				{
					return aHarness;
				}					
			}
		}
		return null;
	}
	
	public Harness removeHarness(String make, int modelNumber) {
		
		if (harnessList != null)
		{
			for (int index=0; index<harnessList.size(); index++) {
				Harness aHarness = harnessList.get(index);
				
				if (make.equals(aHarness.make) && modelNumber == aHarness.modelNumber)
				{
					harnessList.remove(aHarness);
					return aHarness;
				}						
			}
			JOptionPane.showMessageDialog(null, "No such harness has been found on record.");
		}
		return null;		
	}
	
	public Harness checkHarness(String instructorName, String make, int modelNumber) {
		
		if (harnessList != null)
		{
			for (int index=0; index<harnessList.size(); index++)
			{
				Harness aHarness = harnessList.get(index);
				if (make.equals(aHarness.make) && modelNumber == aHarness.modelNumber)
				{
					aHarness.checkHarness(instructorName);
					return aHarness;				
				}								
			}
			JOptionPane.showMessageDialog(null, "No such harness has been found on record.");
		}
		return null;
	}
	
	public Harness loanHarness(String memberName) {
		
		if (harnessList != null)
		{
			for (int index=0; index<harnessList.size(); index++) {
				Harness aHarness = harnessList.get(index);
				
				if (aHarness.canHarnessBeLoaned()) {
					aHarness.loanHarness(memberName);
					return aHarness;
				}	
			}
		}
		JOptionPane.showMessageDialog(null, "There are no available harnesses to loan.");
		return null;
	}
	
	public Harness returnHarness(String make, int modelNumber) {
		
		if (harnessList != null)
		{
			for (int index=0; index<harnessList.size(); index++) {
				Harness aHarness = harnessList.get(index);
				
				if (make.equals(aHarness.make) && modelNumber == aHarness.modelNumber) {
					aHarness.returnHarness();
					return aHarness;				
				}
			}
			JOptionPane.showMessageDialog(null, "No such harness has been found on record.");
		}
		return null;				
	}
}
