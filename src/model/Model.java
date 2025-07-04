package model;

import model.domain.News;

public class Model {
	private Database db = new Database();
	
	private static Model model = new Model();
	
	public static Model getModel() {
		return model;
	}
	private Model() {}
	
	public News[] getNewses() {
		return db.getNews();
	}
}
