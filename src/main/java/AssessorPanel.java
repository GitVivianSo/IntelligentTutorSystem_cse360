
import java.awt.event.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;
import javax.swing.*;

/**
 * AssessorPanel.java
 * Description: AssessorPanel displays the questions, and then sends the results to Control
 * 				Center and helps Companion display status of the student
 *
 * (Assignment number: Recitation 4 Part 4) 
 * Completion time: 10 hrs
 *
 * @author Ramy Dagdoni, Vivian So, Chandler Cotton
 * @version 1.0
 */
//public class Assessor extends JPanel implements ActionListener{
public class AssessorPanel extends Observable{//extends JPanel{
	//For decorator 
	Assessor brain;
		
    static String ans1String = "ans1";
    static String ans2String = "ans2";
    static String ans3String = "ans3";
    static String ans4String = "ans4";

   public String[] questions = {"Stress and anxiety are\n the same thing.",
        "Losing weight can be a sign of\n too much stress.",
        "An anxiety disorder can be characterized\n by which of the following?",
        "Men are twice as likely as women\n to have generalized anxiety disorder.",
        "Does smoking and drinking help reduce stress ?"
    }; 
    //public String[] questions = {"A","B","C","D","E"};
//    public String[][] answers = {
//            {"True", "False"},
//            {"True", "False"},
//            {"True", "False"},
//            {"True", "False"},
//            {"True", "False"}
//    };
        
    public String[][] answers = {
        {"True", "False"},
        {"Yes", "No", "I dont know."},
        {"A sudden, attack of terror.", "An unfounded fear of daily situations.",
            "Fear of doing or saying something that would be embarrasing around people.",
            "All of the above."},
        {"True.", "False"},
        {"Yes, only if you drink limited.", "Yes, because it helps you relax.", "No!!!"}
    };

     int[] correctAnswers = {1, 0, 3, 1, 2};
     int score = 0;
     final int NUM_OF_QUESTIONS = 5;
     int currentQuestion = 0;

    JRadioButton ans1,ans2, ans3, ans4;
    ButtonGroup grp;
    JButton SubmitButton;
    JLabel jLabel1, labelQuestion, labelQuestionNum;
    int status = 4;
    
    //ControlCenter variables
    JLabel ControlCenterlabel = new JLabel();
    ControlCenter controls;
    String controlStatus;
    Timer t = new Timer(1000, new TickTime());
    int timerNum = 0;
    JLabel ControlCenterTimeLabel = new JLabel();
    
    JPanel AssessorPan = new JPanel();
    JPanel pan; //add this to AccessorPan
    
    public AssessorPanel() 
    {
    	System.out.println();
        jLabel1 = new JLabel();
        labelQuestionNum = new JLabel();
        labelQuestion = new JLabel();
        SubmitButton = new JButton();

        //initialize
        ans1 = new JRadioButton();
        ans1.setActionCommand(ans1String);
        ans2 = new JRadioButton();
        ans2.setActionCommand(ans2String);
        ans3 = new JRadioButton();
        ans3.setActionCommand(ans3String);
        ans4 = new JRadioButton();
        ans4.setActionCommand(ans4String);

        pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        //add panel to Assessor Panel
        AssessorPan.add(pan);

        //group radio buttons
        ButtonGroup grp = new ButtonGroup();
        grp.add(ans1);
        grp.add(ans2);
        grp.add(ans3);
        grp.add(ans4);

        //add everything to inner panel
        pan.add(labelQuestionNum);
        pan.add(labelQuestion);
        pan.add(ans1);
        pan.add(ans2);
        pan.add(ans3);
        pan.add(ans4);
        pan.add(SubmitButton);
        pan.add(ControlCenterlabel);
        pan.add(ControlCenterTimeLabel);
        initQuiz();
    }

    private void initQuiz() 
    {
        jLabel1.setText("Stress Management Short Quiz");
        labelQuestion.setText("question here");
        
	    ans1.addActionListener(new ButtonListener());
	    ans2.addActionListener(new ButtonListener());
	    ans3.addActionListener(new ButtonListener());
	    ans4.addActionListener(new ButtonListener());
	     
        SubmitButton.setText("Submit");
        SubmitButton.addActionListener(new ButtonListener());

        labelQuestionNum.setText("Question #1");
        populateQuestion();
    }

    //this method will populate current question and answers
    public void populateQuestion() {
        //show question
    	t.start();
        labelQuestion.setText(questions[currentQuestion]);
        //if 2 answers only
        if (answers[currentQuestion].length == 2) {
            //hide radiobutton 3 and 4
            ans3.setVisible(false);
            ans4.setVisible(false);
            //set other 2 answers
            ans1.setText(answers[currentQuestion][0]);
            ans2.setText(answers[currentQuestion][1]);
        } ///if 3 answers
        else if (answers[currentQuestion].length == 3) {
            //hide radiobutton 4 and show 3
            ans3.setVisible(true);
            ans4.setVisible(false);
            //set other 3 answers
            ans1.setText(answers[currentQuestion][0]);
            ans2.setText(answers[currentQuestion][1]);
            ans3.setText(answers[currentQuestion][2]);

        } // if 4 answers
        else {
            //show radiobutton 4 and 3
            ans3.setVisible(true);
            ans4.setVisible(true);
            //set  4 answers
            ans1.setText(answers[currentQuestion][0]);
            ans2.setText(answers[currentQuestion][1]);
            ans3.setText(answers[currentQuestion][2]);
            ans4.setText(answers[currentQuestion][3]);
        }

        //show question number
        labelQuestionNum.setText("Question #" + (currentQuestion + 1));
    }
   
    public void updateFaceandScore()
    {
    	//update score
    	controls = controls.getInstance();
    	controls.updateProgress(score, timerNum);
    	controlStatus = controls.calculateStatus();
    	ControlCenterlabel.setText("Your Control Center Status is: " + controlStatus);
    	ControlCenterTimeLabel.setText("Time spent on last question: " + timerNum + " seconds");
    	
    	
    	//companion change face
    	setChanged();
    	notifyObservers();
    	timerNum = 0;//reset timer
    }

    //for decorator pattern=----------------------------------------------
    public void setAssessor(Assessor x){
    	brain = x;
    }
    
    public void showYourself(){
    	brain.doSomething();
    }
    //----------------------------------------------------------------------
    private class ButtonListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
	        int selectedAns = -1;
	        //get selected answer
	        if (ans1.isSelected()) {
	            selectedAns = 0;
	        } else if (ans2.isSelected()) {
	            selectedAns = 1;
	        } else if (ans3.isSelected()) {
	            selectedAns = 2;
	        } else if (ans4.isSelected()) {
	            selectedAns = 3;
	        } else { // no answer selected
	            JOptionPane.showMessageDialog(null, "You must select an answer");
	            return; // exit method
	        }
	        //if correct answer increase score
	        if (selectedAns == correctAnswers[currentQuestion]) { //correct answer
	        	score =1; 
	        	BasicAssessor basic = new BasicAssessor();
				ValidationAssessor helper = new ValidationAssessor();
				helper.add(basic);
				setAssessor(helper); 
				showYourself();
				
	        } else {	//wrong answer show hint
	            score = 0; 
	        	BasicAssessor basic1 = new BasicAssessor();
				HintAssessor helper1 = new HintAssessor();
				helper1.add(basic1);
				setAssessor(helper1); 
				showYourself();
	        }
	        currentQuestion++;
	        //if exam ends
	        if (currentQuestion == NUM_OF_QUESTIONS) {
	            JOptionPane.showMessageDialog(null, "Exam is over, your score is " + score);
	            System.exit(0); // exit program
	        }
	        //unselect radio buttons
	        ans1.setSelected(false);
	        ans2.setSelected(false);
	        ans3.setSelected(false);
	        ans4.setSelected(false);
	        
	        t.stop();//stop timer
	        populateQuestion();
	        updateFaceandScore();
			}

    }
    private class TickTime implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		timerNum++;
    	}
    }
}
