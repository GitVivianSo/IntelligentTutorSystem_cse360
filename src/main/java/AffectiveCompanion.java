import javax.swing.*;

public class AffectiveCompanion extends CompanionDecorator{

	//override
	public void doSomething(JComponent panel) {
		super.doSomething(panel);
		ImageIcon face = null;
		
        try {
        	 face = new ImageIcon("src/thinking.png");

	    } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Image not found");
	    }
		
		JLabel label = new JLabel();
		label.setIcon(face);
		label.setText(" I am here to cheer you.");
		panel.add(label);
	}

}
