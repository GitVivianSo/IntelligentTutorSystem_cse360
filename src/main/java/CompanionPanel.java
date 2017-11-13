
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
* Description: CompanionPanel.java
*     -Companion observes Assessor and updates when question are answers
* (Assignment number: Recitation 4 Part 4)
* Completion time: 15h
* @author Vivian So
* @version 1.0
*/
public class CompanionPanel extends JPanel implements Observer
{
	//For decorator 
	Companion brain;
	
	//For Singleton 
	ControlCenter controls;
    double score= 0;
    
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
    	this.add(name);
    	//name.setText("<html><h1>Vivian So</h1></html>");
        picture = new JLabel();
        this.add(picture);
       // pan.add(name);
        //pan.add(picture);
       
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
    //----------------------------------------------------------------------
    
    //face changes based on score in Assessor quiz
    public void changeFace(double score){
        if(status==1){
        	 // picture.setIcon(createImageIcon( "happy.png"));
        }
        else if(status==2){
            // picture.setIcon(createImageIcon( "thinking.png"));
        }
        else if(status==3){
        	// picture.setIcon(createImageIcon( "worry.png"));
        }
        else if(status==4){
           	 //picture.setIcon(createImageIcon( "sorry.png"));
        }
        else if(status==0){
        }
      //for companion decorator
    	if (score == 0) { //default
			BasicCompanion basic = new BasicCompanion();
			this.setCompanion(basic); 
			this.showYourself();
		} else if (score < .90 && score > .80) {
			BasicCompanion basic = new BasicCompanion();
			HelperCompanion helper = new HelperCompanion();
			helper.add(basic);
			this.setCompanion(helper); 
			this.showYourself();
		} else if (score < .80 && score > .70) {
			BasicCompanion basic = new BasicCompanion();
			AffectiveCompanion h = new AffectiveCompanion();
			h.add(basic);
			this.setCompanion(h); 
			this.showYourself();
		} else  {
			HelperCompanion helper2 = new HelperCompanion();
			AffectiveCompanion affective = new AffectiveCompanion();
			BasicCompanion basic2 = new BasicCompanion();
			helper2.add(affective);
			affective.add(basic2);
			this.setCompanion(helper2);
			this.showYourself();
		}
    }



    public void changeState(int state){
    	//System.out.println("i'm in change state method "+ state);
        status = state;
        animCount = expandMax + 1;  //get it to reset to beginning
        
//add or remove name label 
	      if(status != 0){
	         this.remove(name);
	        // System.out.println("I'm inside status!= 0");
	      }
	      else{
	      	 this.add(name);
	      }
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
		}
	}
 
    //Observer - notify by the assessor
	public void update(Observable o, Object arg) {
		 controls = ControlCenter.getInstance(); //just in case ControlCenter instance is null
		 score = ControlCenter.getGradePercentage();
		 changeFace(score);
	}
	
	public void doSomething() {
		//something here
	}

}
