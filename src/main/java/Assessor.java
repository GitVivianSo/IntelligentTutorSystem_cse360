
import java.awt.event.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;
import javax.swing.*;

/**
 * Description: Assessor.java Assessor will have 5 outputs depending on the
 * state that the slider is at. For state 0 it will only show the author's name
 * (my name), State 1= will show the ComboBox State 3 will show the checkBox and
 * state 4 will show textfield.
 *
 * (Assignment number: Recitation 4 Part 3) 
 * Completion time: 20 hrs
 *
 * @author Ramy Dagdoni, Vivian So
 * @version 1.0
 */
//public class Assessor extends JPanel implements ActionListener{
public class Assessor extends JPanel {

    static String ans1String = "ans1";
    static String ans2String = "ans2";
    static String ans3String = "ans3";
    static String ans4String = "ans4";

    public String[] questions = {"Stress and anxiety are the same thing.",
        "Losing weight can be a sign of too much stress.",
        "An anxiety disorder can be characterized by which of the following?",
        "Men are twice as likely as women to have generalized anxiety disorder.",
        "Does smoking and drinking help reduce stress ?"
    };
    public String[][] answers = {
        {"True", "False"},
        {"Yes", "No", "I dont know."},
        {"A sudden, attack of terror.", "An unfounded fear of daily situations.",
            "Fear of doing or saying something that would be embarrasing around people.",
            "All of the above."},
        {"True.", "False"},
        {"Yes, only if you drink limited.", "Yes, because it helps you relax.", "No!!!"}
    };

    public int[] correctAnswers = {1, 0, 3, 1, 2};
    public int score = 0;
    public final int NUM_OF_QUESTIONS = 5;
    public int currentQuestion = 0;

    public JRadioButton ans1;
    public JRadioButton ans2;
    public JRadioButton ans3;
    public JRadioButton ans4;
    public ButtonGroup grp;
    public JButton SubmitButton;
    public JLabel jLabel1;
//    public JLabel jLabel3;
//    public JLabel jLabel5;
    public JLabel labelQuestion;
    public JLabel labelQuestionNum;
    int status = 4;

    JPanel pan;

    public Assessor() {
        jLabel1 = new JLabel();
//        jLabel3 = new JLabel();
//        jLabel5 = new JLabel();

        labelQuestionNum = new JLabel();
        labelQuestion = new JLabel();
        SubmitButton = new JButton();

        //initialize
        ans1 = new JRadioButton();
        ans1.setActionCommand(ans1String);
        ans2 = new JRadioButton();
        ans2.setActionCommand(ans3String);
        ans3 = new JRadioButton();
        ans3.setActionCommand(ans3String);
        ans4 = new JRadioButton();
        ans4.setActionCommand(ans4String);

        pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        //add panel to Accessor Panel
        this.add(pan);

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
        initQuiz();

    }

    private void initQuiz() {

        jLabel1.setText(" Stress Management Short Quiz");
        labelQuestion.setText("question here");

//        ans1.addActionListener(this);
//        ans2.addActionListener(this);
//        ans3.addActionListener(this);
//        ans4.addActionListener(this);
        
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

    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

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
        if (selectedAns == correctAnswers[currentQuestion]) {
            score++;
            JOptionPane.showMessageDialog(null, "Correct Answer!");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong Answer!");
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
        populateQuestion();

		}

    }
}
