/**
* Description: ControlCenter.java
*      -updates scores and records time spent from assessor questions
* (Assignment number: Recitation 4 Part 4)
* Completion time: 10h
* @author Chandler Cotton, Vivian So
* @version 1.0
*/
public class ControlCenter {

	private static ControlCenter singleton;

	private static int[] ansCorrect;
	private static int[] ansIncorrect;
	private static int[][] timeSpent;
	private static double gradePercentage;
	private static int counter = 0;

	protected ControlCenter()
	{
		ansCorrect = new int[1];  //we only have one lesson right now
		ansIncorrect = new int[1];
		timeSpent = new int[5][1];
	}

	public static ControlCenter getInstance()
	{
		if(singleton == null)
		{
			singleton = new ControlCenter();
		}

		return singleton;
	}

	public static String calculateStatus()
	{
		double correct = ansCorrect[0];
		double incorrect = ansIncorrect[0];

		gradePercentage = correct/(correct + incorrect);
//		System.out.println("Percentage correct:" + gradePercentage);
//		System.out.println("correct:" + correct);
//		System.out.println("incorrect:" + incorrect);
//		
		if (gradePercentage >= .70){
			return "execellent";
		}
		else {
			return "struggling";
		}

	}

	public static void updateProgress(int num, int time)  //called from Assessor when user presses submit for each question
	{
		if(num == 1)
			ansCorrect[0]++;
		else
			ansIncorrect[0]++;
		
		timeSpent[counter][0] = time;
		
		counter++;
		
		System.out.println("Time spent on question: " + time +" seconds");
	}

	public static double getGradePercentage() {
		return gradePercentage;
	}
}