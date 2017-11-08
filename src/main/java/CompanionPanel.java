
import java.awt.Image;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
* Description: Companion.java
*     -description
* (Assignment number: Recitation 4 Part 4)
* Completion time: 4:30 -7:00pm, 10:45pm -1:39am
* @author Vivian So
* @version 1.0

*/
public class CompanionPanel extends JPanel implements Observer
{
	//for decorator pattern
	public Companion brain;

    int status= 0;
    JPanel pan = new JPanel();;
    JLabel name = new JLabel("Vivian");
    JLabel picture;


    //Variables for animation
    final int defWid;
    final int defHeight;
    int picWidth;
    int picHeight;
    int delay = 100;
	Timer animation;
	int animCount = 0;
	final int expandMax = 30;


    public CompanionPanel(){
    	//set up panel
    	this.add(pan);
    	name.setText("<html><h1>Vivian So</h1></html>");
        picture = new JLabel();
        pan.add(name);
        pan.add(picture);
       
        //animations
        animation = new Timer(delay, new AnimationListener());
        defWid=picture.getWidth();
        defHeight=picture.getHeight();
        picWidth =picture.getWidth();;
        picHeight=picture.getHeight();;
        animation.start();

    }
    
    //for decorator pattern=----------------------------------------------
    public void setCompanion(Companion x){
    	brain = x;
    }
    
    public void showYourself(){
    	removeAll();
    	brain.doSomething(this);
    	revalidate();
    }
    //========================----------------------------------------------
    
    
    public void changeFace(int status){
        if(status==1){
              pan.remove(name);
        	  picture.setIcon(createImageIcon( "happy.png"));
        }
        else if(status==2){
             pan.remove(name);
             picture.setIcon(createImageIcon( "thinking.png"));
        }
        else if(status==3){
        	 pan.remove(name);
        	 picture.setIcon(createImageIcon( "worry.png"));

        }
        else if(status==4){
        	 pan.remove(name);
           	 picture.setIcon(createImageIcon( "sorry.png"));
        }
        else if(status==0){
        	pan.add(name);
        }
    }

    //method finds image and returns it as Image Icon
    protected static ImageIcon createImageIcon(String path) {
        //java.net.URL imgURL = Companion.class.getResource(path);
        String imgURL = System.getProperty("user.dir");
        //imgURL = imgURL.replace("java", "resources") + "/" + path;
        imgURL = imgURL.substring(0, imgURL.length() ) + "/src/main/resources/" +path;
        //System.out.println("FilePathDEBUG: " +imgURL);

		//resizing
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        try {
        	return imageIcon;

	    } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Image not found");
	         return new ImageIcon();
	    }

    }

    public void changeState(int state){
        status = state;
        animCount = expandMax + 1;  //get it to reset to beginning
        changeFace(status);
        //update in here
    }

//    public Dimension getPreferredSize(){
//        return new Dimension(100, 100);
//    }

    private class AnimationListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
            if(animCount < expandMax)
			{
				picWidth = picWidth + 5;
				picHeight = picHeight + 5;
				animCount++;
			}
			else
			{
				  picWidth = picture.getWidth();
	              picHeight = picture.getHeight();
	              animCount = 0;
			}
		}
	}

	public void update(java.util.Observable o, Object arg) {
		 changeFace(status);
	}
	public void doSomething() {
//something here
	}

}
