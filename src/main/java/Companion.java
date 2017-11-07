

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.*;
/**
* Description: Companion.java
*      -Avatar is animated
* (Assignment number: Recitation 4 Part 3)
* Completion time: 4:30 -7:00pm
* @author Vivian So
* @version 1.0

*/
public class Companion extends JPanel implements Observer
{
    int status= 0;


    JPanel pan = new JPanel();;
    JLabel name = new JLabel("Vivian");
    JLabel picture;



    final int defWid ;
    final int defHeight ;

    int picWidth ;
    int picHeight;


    int delay = 100;

	Timer animation = new Timer(delay, new AnimationListener());

	int animCount = 0;
	final int expandMax = 30;


    public Companion(){
    	this.add(pan);
        pan.add(name);
        picture = new JLabel();
        pan.add(picture);
        name.setText("<html><h1>Vivian So</h1></html>");

        defWid=picture.getWidth();
        defHeight=picture.getHeight();
        picWidth =picture.getWidth();;
        picHeight=picture.getHeight();;
        animation.start();

    }
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


    protected static ImageIcon createImageIcon(String path) {
        //java.net.URL imgURL = Companion.class.getResource(path);

        String imgURL = System.getProperty("user.dir");
        imgURL = imgURL.replace("java", "resources") + "/" + path;
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
    }

    public Dimension getPreferredSize(){
        return new Dimension(100, 100);
    }


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
			repaint();
		}
	}

	public void update(java.util.Observable o, Object arg) {
		 changeFace(status);
	}



}
