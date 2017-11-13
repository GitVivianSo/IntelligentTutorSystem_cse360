import javax.swing.*;
/**
* Description: AffectiveCompanion.java
*      -Displays thinking face
* (Assignment number: Recitation 4 Part 4)
* Completion time: 1.5h
* @author Vivian So
* @version 1.0
*/
public class AffectiveCompanion extends CompanionDecorator{

	//override
	public void doSomething(JComponent panel) {
		super.doSomething(panel);
		ImageIcon face = null;
		
        try {
        	 face = createImageIcon("thinking.png");

	    } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Image not found");
	    }
		
		JLabel label = new JLabel();
		JLabel picture = new JLabel();
		label.setIcon(face);
		label.setText(" I am here to cheer you.");
		panel.add(label);
		panel.add(picture);
	}

}
