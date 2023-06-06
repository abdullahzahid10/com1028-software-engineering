package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.BaseQuery;

public class LabReq1 extends BaseQuery{

	public LabReq1(String configFilePath) throws FileNotFoundException {
		super(configFilePath);
	}

	/* -------------------------------------------------------------
	 * TODO: getActual() to be completed as part of the coursework.
	 * --------------------------------------------------------------
	 */
	/* ---------------------------------------------------------------------
	 * The getActual() method returns yours requirement code's output.
	 * In this instance, the return type is a String, you are free to choose
	 * other return types depending on the requirement. You are allowed to 
	 * write additional helper methods.
	 * ---------------------------------------------------------------------
	 */

	//	public String getActual() {
	//		unimplementedMessage();
	//		return null;

	public List<String> getActual() throws SQLException {


		List<String> actorLastNames = new ArrayList<String>();


		Map<String, Integer> actorLastnameCount = new HashMap<String, Integer>();

		List<Actor> actorList = getActorList();

		for (Actor actor : actorList) {
			String lastName = actor.getLastName();
			if (actorLastnameCount.containsKey(lastName)) {
				Integer count = actorLastnameCount.get(lastName);
				actorLastnameCount.put(lastName, count + 1);
			} else {
				actorLastnameCount.put(lastName, 1);
			}
		}

		for (String lastName : actorLastnameCount.keySet()) {
			Integer count = actorLastnameCount.get(lastName);
			if (count > 2) {
				actorLastNames.add(lastName);
			}
		}

		Collections.sort(actorLastNames);

		return actorLastNames;
	}


	private List<Actor> getActorList() throws SQLException {

		List<Actor> actorList = new ArrayList<Actor>();

		Actor actor;
		Integer actorID;
		String firstName;
		String lastName;

		ResultSet rs = this.getResultSet("Select * from actor");

		// Iterate over the ResultSet to create an ArrayList of Actor objects
		while (rs.next()) {
			actorID = rs.getInt("actor_id");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");

			actor = new Actor(actorID, firstName, lastName);
			actorList.add(actor);
		}

		return actorList;
	}

	/* -------------------------------------------------------------
	 * TODO: printOutput() to be completed as part of the coursework.
	 * --------------------------------------------------------------
	 */
	/* ----------------------------------------------------------------------
	 * The printOutput() method prints result of your requirement code 
	 * onto the console for the end-user to view. This method should
	 * rely on the requirement code results obtained through the getActual() 
	 * method, decorate it in a human friendly format and display the results 
	 * on the console. It is possible that this method may need to get additional 
	 * data to make the output human friendly. For example, if the requirement 
	 * code returns only the customer IDs, this method may additionally 
	 * want to fetch the customer names to make the output human-friendly.
	 * You are allowed to write additional helper methods.
	 * ----------------------------------------------------------------------
	 */

	//	public void printOutput() throws SQLException{
	//		unimplementedMessage();
	//	}

	public void printOutput() throws SQLException {
		List<String> actorLastNames = getActual();
		System.out.println("List of actor last names that are shared by at least 3 actors");
		for (String lastName : actorLastNames) {
			System.out.println(lastName);
		}
	}

}
