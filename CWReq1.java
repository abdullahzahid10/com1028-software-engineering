package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BaseQuery;
//Get into the class
public class CWReq1 extends BaseQuery{

	public CWReq1(String configFilePath) throws FileNotFoundException {
		super(configFilePath);
	}
	//getActual() Method being implemented.
	public  ArrayList<Actor> getActual() throws SQLException {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		ArrayList<Actor> totalNumOfActors = new ArrayList<Actor>();

		Actor a;

		Integer actorID;
		String firstName;
		String lastName;
		//Sql query, oly allowed distinct queries at this stage
		ResultSet rs = this.getResultSet("Select * from actor");
		//While loop.
		while(rs.next()) {
			actorID = rs.getInt("actor_id");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			a = new Actor(actorID, firstName, lastName);
			actors.add(a);
		}
		//For loop
		for(Actor x: actors) {

			if (x.getFirstName().isEmpty() == false) {
				totalNumOfActors.add(x);
			}
		}
		//return the appropriates
		return totalNumOfActors;

	}

	//Human friendly format.
	public void printOutput() throws SQLException{
		ArrayList<Actor> totalNumOfActors = getActual();
		for (int i = 0; i < 1; i++) {
			int sizeGetter = totalNumOfActors.size();

			String str2 = Integer.toString(sizeGetter);

			System.out.println(str2);
			System.out.println();
		}	

	}

}
