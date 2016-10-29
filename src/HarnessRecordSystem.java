import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HarnessRecordSystem {
	HarnessRecords theHarnessRecords;
	JTextArea recordViewer;
	
	public static void main(String[] args) {
		HarnessRecordSystem theHarnessRecordSystem = new HarnessRecordSystem();
		theHarnessRecordSystem.go();
	}
	
	public void go() {			
		JFrame theFrame = new JFrame("Harness Record System");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		JButton addHarnessListButton = new JButton("Create Record from List");
		addHarnessListButton.addActionListener(new AddHarnessListListener());
		buttonBox.add(addHarnessListButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5, 5)));
		
		JButton addNewHarnessButton = new JButton("Add New Harness");
		addNewHarnessButton.addActionListener(new AddNewHarnessListener());
		buttonBox.add(addNewHarnessButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5, 5)));
		
		JButton addOldHarnessButton = new JButton("Add Old Harness");
		addOldHarnessButton.addActionListener(new AddOldHarnessListener());
		buttonBox.add(addOldHarnessButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5, 15)));
					
		JButton removeHarnessButton = new JButton("Remove Harness");
		removeHarnessButton.addActionListener(new RemoveRecordListener());
		buttonBox.add(removeHarnessButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5, 5)));
	
		JButton recordSafetyCheckButton = new JButton("Record Safety Check");
		recordSafetyCheckButton.addActionListener(new RecordSafetyCheckListener());
		buttonBox.add(recordSafetyCheckButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5, 15)));
		
		JButton loanHarnessButton = new JButton("Loan Harness");
		loanHarnessButton.addActionListener(new LoanHarnessListener());
		buttonBox.add(loanHarnessButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5, 5)));
		
		JButton returnHarnessButton = new JButton("Return Harness");
		returnHarnessButton.addActionListener(new ReturnHarnessListener());
		buttonBox.add(returnHarnessButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5, 5)));
				
		recordViewer = new JTextArea(10, 20);
		recordViewer.setLineWrap(true);
		
		JScrollPane scroller = new JScrollPane(recordViewer);
		
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		background.add(BorderLayout.CENTER, scroller);
		background.add(BorderLayout.EAST, buttonBox);
		
		theFrame.getContentPane().add(background);
		theFrame.setBounds(50,50,600,400);
		theFrame.setVisible(true);
	}
	
	public class AddNewHarnessListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			HarnessInputBox inputBox = new HarnessInputBox("Make: ", "Model Number: ", "Instructor Name: ", "Add New Harness");
				
			Harness theHarness = new Harness(inputBox.makeField.getText(), 
					Integer.parseInt(inputBox.modelNumberField.getText()), inputBox.instructorNameField.getText());
			
			if (theHarnessRecords == null)
			{
				theHarnessRecords = new HarnessRecords();
			}
			
			theHarnessRecords.addHarness(theHarness);
			
			displayHarnessRecords();
		}	
	}
	
	public class AddOldHarnessListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			HarnessInputBox inputBox = new HarnessInputBox("Make:", "Model Number: ", "No. of Times Used: ", "Instructor Name: ",
																									"On Loan: ", "Add Old Harness");
			
			Harness theHarness = new Harness(inputBox.makeField.getText(), Integer.parseInt(inputBox.modelNumberField.getText()),
			Integer.parseInt(inputBox.timesUsedField.getText()), inputBox.instructorNameField.getText(), inputBox.onLoanBox.isSelected());
			
			if (theHarnessRecords == null)
			{
				theHarnessRecords = new HarnessRecords();
			}
			
			theHarnessRecords.addHarness(theHarness);
			
			displayHarnessRecords();
		}
	}
	
	public class AddHarnessListListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int dialogResult = 0;
			
			if (theHarnessRecords != null) {
				dialogResult = JOptionPane.showConfirmDialog(null, "Warning, this will reset your current inventory, continue?");
			}
			
			if (theHarnessRecords == null || dialogResult == JOptionPane.YES_OPTION)
			{
				JFrame aFrame = new JFrame();
				JFileChooser openChooser = new JFileChooser();            
				int myReturnVal = openChooser.showOpenDialog(aFrame);
		
				if (myReturnVal == JFileChooser.APPROVE_OPTION) {
					try 
					{
						In fileIn = new In(openChooser.getSelectedFile());
						theHarnessRecords = new HarnessRecords(fileIn);
					}
					catch (Exception exception) 
					{
						exception.printStackTrace();
					}
					displayHarnessRecords();
				}
			}	
		}
	}
	
	public class RemoveRecordListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			HarnessInputBox inputBox = new HarnessInputBox("Make: ", "Model Number: ", "Remove Harness");
			
			theHarnessRecords.removeHarness(inputBox.makeField.getText(), Integer.parseInt(inputBox.modelNumberField.getText()));
						
			displayHarnessRecords();	
		}
	}
	
	public class RecordSafetyCheckListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			HarnessInputBox inputBox = new HarnessInputBox("Make: ", "Model Number: ", "Instructor Name: ", "Check Harness");
			
			theHarnessRecords.checkHarness(inputBox.instructorNameField.getText(), inputBox.makeField.getText(),
																Integer.parseInt(inputBox.modelNumberField.getText()));
			displayHarnessRecords();
		}
	}
	
	public class LoanHarnessListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			HarnessInputBox inputBox = new HarnessInputBox("Member Name: ", "Loan Harness");
			
			theHarnessRecords.loanHarness(inputBox.memberNameField.getText());
			
			displayHarnessRecords();
		}
	}
	
	public class ReturnHarnessListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			HarnessInputBox inputBox = new HarnessInputBox("Make: ", "Model Number: ", "Return Harness");
			
			theHarnessRecords.returnHarness(inputBox.makeField.getText(), Integer.parseInt(inputBox.modelNumberField.getText()));
			
			displayHarnessRecords();									
		}
	}	
	
	public void displayHarnessRecords() {
		String displayHarnesses = "";
		
		for (int index=0; index<theHarnessRecords.harnessList.size(); index++)
		{
			Harness aHarness = theHarnessRecords.harnessList.get(index);
			displayHarnesses += "- " + aHarness.toString() + "\n";
			
		}
		recordViewer.setText(displayHarnesses);			
	}
}
