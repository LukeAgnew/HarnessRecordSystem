import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class HarnessInputBox {
	JFrame frame = new JFrame();
	JPanel background = new JPanel(new BorderLayout(5,5));
	JPanel controls = new JPanel(new GridLayout(0,1,2,2));
    JPanel labels = new JPanel(new GridLayout(0,1,2,2));
    
    JTextField makeField;
    JTextField modelNumberField;
    JTextField timesUsedField;
    JTextField instructorNameField;
    JCheckBox onLoanBox;
    JTextField memberNameField;
	
    public HarnessInputBox(String make, String modelNumber, String instructorName, String inputType) {
    	labels.add(new JLabel(make , SwingConstants.RIGHT));
        labels.add(new JLabel(modelNumber, SwingConstants.RIGHT));
        labels.add(new JLabel(instructorName, SwingConstants.RIGHT));
        background.add(labels, BorderLayout.WEST);
    	
        makeField = new JTextField();
        controls.add(makeField);
        modelNumberField = new JTextField();
        controls.add(modelNumberField);
        instructorNameField = new JTextField();
        controls.add(instructorNameField);
        background.add(controls, BorderLayout.CENTER);
        
        display(inputType);
    }
    
    public HarnessInputBox(String make, String modelNumber, String timesUsed, String instructorName, String onLoan, String inputType) {
    	labels.add(new JLabel(make , SwingConstants.RIGHT));
        labels.add(new JLabel(modelNumber, SwingConstants.RIGHT));
        labels.add(new JLabel(timesUsed, SwingConstants.RIGHT));
        labels.add(new JLabel(instructorName, SwingConstants.RIGHT));
        labels.add(new JLabel(onLoan, SwingConstants.RIGHT));
        background.add(labels, BorderLayout.WEST);
        
        makeField = new JTextField();
        controls.add(makeField);
        modelNumberField = new JTextField();
        controls.add(modelNumberField);
        timesUsedField = new JTextField();
        controls.add(timesUsedField);
        instructorNameField = new JTextField();
        controls.add(instructorNameField);
        onLoanBox = new JCheckBox();
        controls.add(onLoanBox);
        background.add(controls, BorderLayout.CENTER);
        
        display(inputType);
    }
	
    public HarnessInputBox(String make, String modelNumber, String inputType) {
    	labels.add(new JLabel(make , SwingConstants.RIGHT));
        labels.add(new JLabel(modelNumber, SwingConstants.RIGHT));
        background.add(labels, BorderLayout.WEST);
                
        makeField = new JTextField();
        controls.add(makeField);
        modelNumberField = new JTextField();
        controls.add(modelNumberField);
        background.add(controls, BorderLayout.CENTER);
        
        display(inputType);
    }
    
    public HarnessInputBox(String memberName, String inputType) {
    	labels.add(new JLabel("Member Name: ", SwingConstants.RIGHT));
        background.add(labels, BorderLayout.WEST);

        memberNameField = new JTextField();
        controls.add(memberNameField);
        
        background.add(controls, BorderLayout.CENTER);     
    	
        display(inputType);
    }
    
    public void display(String inputType) {
    	JOptionPane.showMessageDialog(frame, background, inputType, JOptionPane.QUESTION_MESSAGE);   	
    }
		
}
