import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
* Description: HintAssessor.java
*      -Displays message if answer is wrong
* (Assignment number: Recitation 4 Part 4)
* Completion time: 1.5h
* @author Chandler Cotton
* @version 1.0
*/
public class HintAssessor extends AssessorDecorator {
	public void doSomething() 
	{
		super.doSomething();
	    JOptionPane.showMessageDialog(null, "Wrong Answer: Here's a Hint!");
	}
}
