import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
* Description: ValidationAssessor.java
*      -Displays message if answer is correct
* (Assignment number: Recitation 4 Part 4)
* Completion time: 1.5h
* @author Chandler Cotton
* @version 1.0
*/
public class ValidationAssessor extends AssessorDecorator{
	public void doSomething() 
	{
		super.doSomething();
	    JOptionPane.showMessageDialog(null, "Correct Answer!");
	}
}
