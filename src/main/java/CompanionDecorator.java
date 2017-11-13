import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
/**
* Description: CompanionDecorator.java
*     -inherited by HelperCompanion and AffectiveCompanion 
*     
* (Assignment number: Recitation 4 Part 4)
* Completion time: 15h
* @author Vivian So
* @version 1.0
*/
public class CompanionDecorator implements Companion
{
	protected Companion c;
	
	//aggregation CompanionDecorator can exist independent of ICompanion
	public void add(Companion c){
		this.c = c;
	}

	public void doSomething(JComponent panel) {
		this.c.doSomething(panel);
	}
	
    //method finds image and returns it as Image Icon
    protected static ImageIcon createImageIcon(String path) 
    {
        String imgURL = System.getProperty("user.dir");
        //imgURL = imgURL.replace("java", "resources") + "/" + path;
        imgURL = imgURL.substring(0, imgURL.length() ) + "/src/main/resources/" +path;
       // System.out.println("FilePathDEBUG: " +imgURL);

		//resizing
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        try {
        	return imageIcon;

	    } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Image not found");
	         return new ImageIcon();
	    }

    }

}
