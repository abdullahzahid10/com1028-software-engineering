package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BaseQuery;

public class TutReq extends BaseQuery{

	public TutReq(String configFilePath) throws FileNotFoundException {
		super(configFilePath);
	}
	
	public  ArrayList<Film> getActual() throws SQLException {
		ArrayList<Film> films = new ArrayList<Film>();
		ArrayList<Film> expensiveBoringFilms = new ArrayList<Film>();
		
		Film f;
		
		Integer film_id;
		String title;
		String description;
		Double rental_rate;
		
		ResultSet rs = this.getResultSet("Select * from film");
		
		while(rs.next()) {
			film_id = rs.getInt("film_id");
			title = rs.getString("title");
			description = rs.getString("description");
			rental_rate = rs.getDouble("rental_rate");
			f = new Film(film_id, title, description, rental_rate);
			films.add(f);
		}
		
		for(Film x: films) {
			if(x.getRentalRate() == 4.99 
					&& x.getDescription().contains("Boring")) {
				expensiveBoringFilms.add(x);
			}
		}
		
		return expensiveBoringFilms;
	
	}
	
	
	public static void main(String [] args) {
		
	}
	public void printOutput() throws SQLException{
		ArrayList<Film> expensiveBoringFilms = getActual();
		for(Film f:expensiveBoringFilms) {
			System.out.println(f.getFilmID() + " " + f.getTitle() + " " + f.getRentalRate() + " " + f.getDescription());
		}	}

}
