package model;

public class Model {
	private Database db = new Database();
	
	private static Model model = new Model();
	
	// Singleton design pattern 구조
	public static Model getModel() {
		return model;
	}
	private Model() {}
	
}
