import javax.swing.JComponent;
/*
 * AssessorDecorator.java
 * 	-Inherited by HintAssessor and ValidationAssessor
 * Project 4, Recitation 4
 * Completion time: 1.5 hrs
 * @name  Chandler Cotton
 * @version 4
 */
public class AssessorDecorator implements Assessor{
	protected Assessor a;
	
	public void add(Assessor a){
		this.a = a;
	}

	public void doSomething() {
		this.a.doSomething();
	}
	

}
