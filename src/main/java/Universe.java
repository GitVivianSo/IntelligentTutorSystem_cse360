


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

public class Universe extends JFrame {

    GridLayout layout = new GridLayout(0, 2);
    JSlider sliding;
    Companion xPanel = new Companion();
    Tutor yPanel = new Tutor();
    Assessor zPanel = new Assessor();
    JPanel pPanel = new JPanel();

    //Labels
    JLabel viv_label = new JLabel("Vivian");
    JLabel cha_label = new JLabel("Chandler");
    JLabel ram_label = new JLabel("Ramy");
    JLabel pau_label = new JLabel("Paul");

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

  
        proj.add(xPanel);
        xPanel.add(viv_label);
        proj.add(yPanel);
        yPanel.add(cha_label);
        
        proj.add(zPanel);
        zPanel.add(ram_label);
        zPanel.setVisible(false);
        
        proj.add(pPanel);
        pPanel.add(pau_label);
        
        this.add(proj, BorderLayout.NORTH);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(controls, BorderLayout.CENTER);
        

    }

    public class event implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
//when we slide the slider, we want the label to change to where the slider is at
            JSlider slide = (JSlider) e.getSource();
            int status = slide.getValue(); //when it hits the tick, you will find the value

            if (status == 0) {
                xPanel.add(viv_label);
                yPanel.add(cha_label);
                zPanel.add(ram_label);
                pPanel.setVisible(true);
            } else{
                xPanel.remove(viv_label);
                yPanel.remove(cha_label);
                zPanel.remove(ram_label);
                pPanel.setVisible(false);
            }
           
            xPanel.changeState(status);
            yPanel.changeState(status);
            
            if (status==4){
                zPanel.setVisible(true);
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
