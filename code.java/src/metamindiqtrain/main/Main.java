package metamindiqtrain.main;

import metamindiqtrain.core.*;




/**
 * @author  rain
 */
public class Main {

	/**
	 * @uml.property  name="global"
	 * @uml.associationEnd  
	 */
	public static GlobalData global = new GlobalData();
	
	public static void main(String[] args) {

		System.out.println("program start");
		
	
		
		new TrainingManager();
		
		
		
		
		
		
		
		
		try{
		Thread.sleep(2000);
		}
		catch (Exception e){};

		//infomapframe.setVisible(false);
		System.out.println("program end");
		
	
	
	}

}
