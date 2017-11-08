import javax.swing.*;

public class HelperCompanion extends CompanionDecorator {
	
	public void doSomething(JComponent panel) {
		super.doSomething(panel);
		ImageIcon face = null;
	   try {
        	 face = new ImageIcon("src/happy.png");

	    } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Image not found");
	    }
	   
		JLabel label = new JLabel();
		label.setIcon(face);
		label.setText("I am here to help you.");
		panel.add(label);
	}
}
