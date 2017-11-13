import javax.swing.*;
/**
* Description: HelperCompanion.java
*      -Displays worry face
* (Assignment number: Recitation 4 Part 4)
* Completion time: 1.5h
* @author Vivian So
* @version 1.0
*/

public class HelperCompanion extends CompanionDecorator {
	
	public void doSomething(JComponent panel) 
	{
		super.doSomething(panel);
		ImageIcon face = null;
		try {
        	 face = createImageIcon("worry.png");
	    } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Image not found");
	    }
	   
		JLabel label = new JLabel();
		JLabel picture = new JLabel();
		picture.setIcon(face);
		label.setText("I am here to help you.");
		panel.add(label);
		panel.add(picture);
	}
}
