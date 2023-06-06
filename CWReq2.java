//Declare the package
package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


// Import Database
import db.BaseQuery;

//Getting into class
public class CWReq2 extends BaseQuery{

	public CWReq2(String configFilePath) throws FileNotFoundException {
		super(configFilePath);
	}

	//Starting to break down in getting the method
	public  ArrayList<Actor> getActual() throws SQLException {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		ArrayList<Actor> penelopeFName = new ArrayList<Actor>();

		Actor a;

		//Start initializing the variables.
		Integer actorID;
		String firstName;
		String lastName;

		//Proceeding with the resultset
		ResultSet rs = this.getResultSet("Select * from actor");

		while(rs.next()) {
			actorID = rs.getInt("actor_id");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			a = new Actor(actorID, firstName, lastName);
			actors.add(a);
		}

		//Making sure all names with penelope are printed.
		for(Actor x: actors) {
			if(x.getFirstName().contains("PENELOPE")) {
				penelopeFName.add(x);
			}
		}
//Returning arraylist
		return penelopeFName;

	}

	//Printing in a human friendly format
	public void printOutput() throws SQLException{
		ArrayList<Actor> penelopeFName = getActual();
		for(Actor a:penelopeFName) {
			System.out.println(a.getActorID() + " " + a.getFirstName() + " " + a.getLastName());
		}	}

}