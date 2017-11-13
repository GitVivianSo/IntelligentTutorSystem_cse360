import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.*;
/**
* Description: BasicCompanion.java
*     -Display default face
* (Assignment number: Recitation 4 Part 4)
* Completion time: 1h
* @author Vivian So
* @version 1.0
*/
public class BasicCompanion implements Companion{

	public void doSomething(JComponent panel) {
		ImageIcon face = new ImageIcon("src/happy.png");
		JLabel label = new JLabel();
		label.setIcon(face);
		label.setText("Hello Student, ");	
		panel.add(label);
	}
}
