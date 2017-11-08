


/**
 * Description: Universe.java. In this program, I had to implement a JFrame and add 4 JPanels into
 * that frame. In addition, I had to add a JSlider at the south side of the frame.
 *
 * (Assignment number: Recitation 4 Part 3) 
 * Completion time: 20 hours
 *
 * @author Paul Tang
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Universe extends JFrame { //implements ActionListener{

    GridLayout layout = new GridLayout(0, 2);
    JSlider sliding;
    CompanionPanel avatarPanel = new CompanionPanel();
    Tutor tutorPanel = new Tutor();
    Assessor assessorPanel = new Assessor();
    JPanel pPanel = new JPanel();

//    //Labels
//    JLabel viv_label = new JLabel("Vivian");
//    JLabel cha_label = new JLabel("Chandler");
//    JLabel ram_label = new JLabel("Ramy");
//    JLabel pau_label = new JLabel("Paul");

    public Universe() {
        
        super("Universe");
        setResizable(true);
        final JPanel proj = new JPanel();
        proj.setLayout(layout);
        JPanel controls = new JPanel();

//START SLIDER
        sliding = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
        sliding.setMajorTickSpacing(1);
        sliding.setPaintTicks(true);
        controls.add(sliding);
        event ab = new event();
        sliding.addChangeListener(ab);

//I set up the components to the preferred size
        proj.setPreferredSize(new Dimension(600, 600));

//add panels
        proj.add(avatarPanel);
        proj.add(tutorPanel);
        proj.add(assessorPanel);
        proj.add(pPanel);
        assessorPanel.setVisible(false);
        
// labels should be added inside panels not from main
        // yPanel.add(cha_label);
        //  xPanel.add(viv_label);
        // zPanel.add(ram_label);
        // pPanel.add(pau_label);
        
        this.add(proj, BorderLayout.NORTH);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(controls, BorderLayout.CENTER); //slider
        

    }

    public class event implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
//when we slide the slider, we want the label to change to where the slider is at
            JSlider slide = (JSlider) e.getSource();
            int status = slide.getValue(); 
            
//for companion decorator
	    	if (status == 0) {
				BasicCompanion basic = new BasicCompanion();
				avatarPanel.setCompanion(basic); 
				avatarPanel.showYourself();
			} else if (status == 1) {
				BasicCompanion basic = new BasicCompanion();
				HelperCompanion helper = new HelperCompanion();
				helper.add(basic);
				avatarPanel.setCompanion(helper); 
				avatarPanel.showYourself();
			} else if (status == 2) {
				BasicCompanion basic = new BasicCompanion();
				AffectiveCompanion h = new AffectiveCompanion();
				h.add(basic);
				avatarPanel.setCompanion(h); 
				avatarPanel.showYourself();
			} else if (status == 3) {
				HelperCompanion helper2 = new HelperCompanion();
				AffectiveCompanion affective = new AffectiveCompanion();
				BasicCompanion basic2 = new BasicCompanion();
				helper2.add(affective);
				affective.add(basic2);
				avatarPanel.setCompanion(helper2);
				avatarPanel.showYourself();
			}
	    	
           //avatarPanel.changeState(status);
           tutorPanel.changeState(status);
           
           if (status==4){
          	  	assessorPanel.setVisible(true); //display quiz
           }
           
        }
    }

    public static void main(String[] args) {
        Universe projTwo = new Universe();
        projTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        projTwo.pack();//resizes the window to fit contents
        projTwo.setVisible(true); //the window will appear
    }
}
