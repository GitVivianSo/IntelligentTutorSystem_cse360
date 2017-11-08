
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.awt.event.*;

/*
* Description: Tutor.java
* -load html files for each of the options the user picks in the
* Universe.java file
* (Assignment number: Recitation 4 Part 1:GUI)
*Completion Time: 18 hours
* @author Chandler Cotton
* @version 1.0
 */
public class Tutor extends JPanel implements ActionListener {

    FileReader fr;
    JScrollPane scrollPane;
    JPanel msgPanel;
    JLabel nameLabel;
    int panelNum;
    String currentPanel;

    JPanel infoPanel;
    JPanel statusPanel;
    JPanel resetPanel;
    JButton helpButton;
    JButton statusButton;
    JButton classButton;
    JButton backButton;
    JButton studentButton;
    JButton resetButton;

    JLabel timeLabel;
    JLabel toGoLabel;
    JLabel ansLabel;
    Timer testTimer;
    int min;
    int sec;
    int questToGo = 15;
    int questAns = 0;

    public Tutor() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(120, 120);
        fr = null;
        panelNum = 0;      //panel will start at  state 0 (msgPanel) when program opens

        //msg panel stuff
        msgPanel = new JPanel();
        msgPanel.setLayout(new GridLayout(6, 1));

        nameLabel = new JLabel("<Chandler>");
        helpButton = new JButton("Help");
        statusButton = new JButton("Quiz Status Test");
        classButton = new JButton("Class Info");
        studentButton = new JButton("Students");
        resetButton = new JButton("Reset Password");

        helpButton.addActionListener(this);
        statusButton.addActionListener(this);
        classButton.addActionListener(this);
        studentButton.addActionListener(this);
        resetButton.addActionListener(this);

        msgPanel.add(nameLabel);
        msgPanel.add(helpButton);
        msgPanel.add(statusButton);
        msgPanel.add(classButton);
        msgPanel.add(studentButton);
        msgPanel.add(resetButton);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());

        timeLabel = new JLabel("Test Timer: 60:00");
        toGoLabel = new JLabel("Questions to go: " + questToGo);
        ansLabel = new JLabel("Questions answered: " + questAns);

        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(4, 1));
        statusPanel.add(timeLabel);
        statusPanel.add(toGoLabel);
        statusPanel.add(ansLabel);

        resetPanel = new JPanel();

        backButton = new JButton("Back");
        infoPanel.add(backButton, BorderLayout.NORTH);
        backButton.addActionListener(this);

        this.add(msgPanel);     //adds message pane to panel
        this.setVisible(true);

    }

    public void changeState(int number) {
        if (number == 0) {
            //nameLabel.setText("Chandler"); // sets name to chandler if state is 0
            if (panelNum == 0) {
                //do nothing if state is already on 0
            } else {  //going from scrollPane to msgPanel
                this.add(msgPanel);
                //System.out.println("closed");
                this.remove(scrollPane);  //switching panels
                this.revalidate();
                this.repaint();
                try {
                    fr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                panelNum = number;  //updating the status of the state
            }
        } else {
            if (panelNum == number) {
                //do nothing if it is already on the scrollPane
            } else if (panelNum == 0) {

                this.remove(msgPanel);
                String file = "P" + number + ".html";  //retrieving file name based on the number passed in the parameter

                this.displayFile(file);
                panelNum = number;

                this.add(scrollPane);
                scrollPane.revalidate();
                this.revalidate();   //updates panel
                this.repaint();

            } else {
                this.remove(scrollPane);   //does same this except changes from one scrollPane to another instance
                String file = "P" + number + ".html";

                this.displayFile(file);
                panelNum = number;

                this.add(scrollPane);
                scrollPane.revalidate();
                this.revalidate();   //updates panel
                this.repaint();
            }
        }
    }

    public void errorMessageLabel() { //changes nameLabel to error message
        nameLabel.setText("HTML file is not found/working");
        if (panelNum == 0) {
            nameLabel.setText("HTML file is not found/working");
            this.revalidate();
            this.repaint();
        } else {
            this.remove(scrollPane);
            this.add(msgPanel);
            this.revalidate();
            this.repaint();
            panelNum = 0;
        }
    }

    public void displayFile(String file) {
        try {
            fr = null;
            scrollPane = null;

            String dir = System.getProperty("user.dir");
            //dir = dir.substring(0, dir.length() ) + "";
            dir = dir.replace("java","resources");
            fr = new FileReader(new File(dir + "/" + file));


            JEditorPane htmlViewer = new JEditorPane();  //gets editorpane for html file
            htmlViewer.setContentType("text/html");  //sets htmlViewer to read html
            htmlViewer.read(fr, "test");
            htmlViewer.setEditable(false);
            scrollPane = new JScrollPane(htmlViewer);  //editorPane inserted to scroll pane
            /*this.add(scrollPane);
			scrollPane.revalidate();
			this.revalidate();   //updates panel
			this.repaint();*/
            fr.close();
        } catch (Exception e) {
            this.errorMessageLabel();
            e.printStackTrace();
        }

    }

    public Dimension getPreferredSize() { //gets dimension size to help format scrollpane
        return new Dimension(200, 200);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == helpButton) {
            //pull up help page
            this.displayFile("Help.html");
            this.remove(msgPanel);
            infoPanel.add(scrollPane, BorderLayout.CENTER);
            this.add(infoPanel);
            infoPanel.setVisible(true);
            this.revalidate();
            this.repaint();
            //this.add(scrollPane, BorderLayout.CENTER);

            currentPanel = "help";
        }

        if (e.getSource() == statusButton) {
            //pull up student account info
            this.remove(msgPanel);
            this.add(infoPanel);
            infoPanel.add(statusPanel);

            testTimer = new Timer(1000, new TimerListener());
            min = 60;
            sec = 00;
            testTimer.start();

            currentPanel = "status";
        }

        if (e.getSource() == classButton) {
            //pull up classId information
            this.displayFile("info.html");
            this.remove(msgPanel);
            infoPanel.add(scrollPane, BorderLayout.CENTER);
            this.add(infoPanel);
            infoPanel.setVisible(true);
            this.revalidate();
            this.repaint();
            currentPanel = "class";
        }

        if (e.getSource() == studentButton) {
            this.displayFile("ActiveStudent.html");
            this.remove(msgPanel);
            infoPanel.add(scrollPane, BorderLayout.CENTER);
            this.add(infoPanel);
            infoPanel.setVisible(true);
            this.revalidate();
            this.repaint();

            currentPanel = "student";

        }

        if (e.getSource() == resetButton) {
            this.remove(msgPanel);
            infoPanel.add(resetPanel);
            this.add(infoPanel);

            String newPassword;

            newPassword = JOptionPane.showInputDialog(null, "Please enter a password that is more than "
                    + "4 characters long, or enter q to quit.");

            do {
                if (newPassword.equalsIgnoreCase("q")) {
                    JLabel quitLabel = new JLabel("You have opted out of creating a new password, press back to exit out of this pane");
                    resetPanel.add(quitLabel);
                    quitLabel.setHorizontalAlignment(JLabel.CENTER);
                } else if (newPassword.length() <= 4) {
                    newPassword = JOptionPane.showInputDialog(null, "Your password is not more than "
                            + "4 characters.  Please try again or press enter q to quit.");

                } else {
                    JLabel successLabel = new JLabel("Please look at your email to confirm your new password");
                    resetPanel.add(successLabel);
                    successLabel.setHorizontalAlignment(JLabel.CENTER);
                }

                this.revalidate();
                this.repaint();

                currentPanel = "reset";

            } while (newPassword.length() <= 4 && !newPassword.equalsIgnoreCase("q"));
        }

        if (e.getSource() == backButton) {
            this.remove(infoPanel);
            this.add(msgPanel);
            this.revalidate();
            this.repaint();

            if (currentPanel == "status") {
                infoPanel.remove(statusPanel);
                testTimer.stop();
            } else if (currentPanel == "reset") {
                infoPanel.remove(resetPanel);
            } else {
                infoPanel.remove(scrollPane);
            }
        }

    }

    private class TimerListener implements ActionListener {

        String minStr;
        String secStr;

        public void actionPerformed(ActionEvent e) {

            if (sec == 0) {
                if (min == 0) {
                    sec = 0;
                } else {
                    sec = 59;
                    min--;
                }
            } else {
                sec--;
            }

            if (sec < 10) {
                secStr = "0" + sec;
            } else {
                secStr = sec + "";
            }

            minStr = min + "";

            timeLabel.setText("Test Timer: " + minStr + ":" + secStr);

            revalidate();
            repaint();

        }
    }

}
