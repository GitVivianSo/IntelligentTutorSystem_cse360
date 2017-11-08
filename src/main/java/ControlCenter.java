public class ControlCenter {
	
	private static ControlCenter singleton;
	
	protected ControlCenter(){}
	
	public static ControlCenter getInstance()
	{
		if(singleton == null)
		{
			singleton = new ControlCenter();
		}
		
		return singleton;
	}
}