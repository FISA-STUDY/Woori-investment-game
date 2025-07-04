package controller;

import model.Model;
import model.domain.News;

public class NewsGenerator {
	private static Model model = Model.getModel();
	
	News[] news = model.getNewses();

	public News generateNews() {
		return news[(int)(Math.random()*9)];
	}
}
