/**
 * Description: Universe.java. 
 * 		-In this program, I had to implement a JFrame and add 4 JPanels into
 * that frame. In addition, I had to add a JSlider at the south side of the frame.
 *
 * (Assignment number: Recitation 4 Part 4) 
 * Completion time: 5h
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
    AssessorPanel assessorPanel = new AssessorPanel();
    JPanel pPanel = new JPanel();
    
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
        proj.setPreferredSize(new Dimension(1100, 600));

//add panels
        proj.add(avatarPanel);
        proj.add(tutorPanel);
        proj.add(assessorPanel.AssessorPan);
        proj.add(pPanel);
        
 //for Observer pattern
        assessorPanel.addObserver(avatarPanel);
        this.add(proj, BorderLayout.NORTH);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(controls, BorderLayout.CENTER); //slider
    }

    public class event implements ChangeListener 
    {
        public void stateChanged(ChangeEvent e) {
//when we slide the slider, we want the label to change to where the slider is at
	       JSlider slide = (JSlider) e.getSource();
	       int status = slide.getValue();
           avatarPanel.changeState(status); 
           tutorPanel.changeState(status);
        }
    }

    public static void main(String[] args) {
        Universe projTwo = new Universe();
        projTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        projTwo.pack();//resizes the window to fit contents
        projTwo.setVisible(true); //the window will appear
    }
}
