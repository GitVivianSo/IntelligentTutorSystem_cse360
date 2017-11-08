import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.*;

public class BasicCompanion implements Companion{


	public void doSomething(JComponent panel) {
		ImageIcon face = new ImageIcon("src/happy.png");
		JLabel label = new JLabel();
		label.setIcon(face);
		label.setText("Hello Student, ");	
		panel.add(label);
	}
}
