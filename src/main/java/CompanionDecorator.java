import javax.swing.JComponent;

public class CompanionDecorator implements Companion
{
	protected Companion c;
	
	//aggregation CompanionDecorator can exist independent of ICompanion
	public void add(Companion c){
		this.c = c;
	}

	public void doSomething(JComponent panel) {
		this.c.doSomething(panel);
	}

}
