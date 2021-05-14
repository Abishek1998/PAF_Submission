package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//connection
public class FundTest {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// Insert fund
	public String insertFund(String amount, String description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into fund(`id`, `amount`, `description`)" + " values (?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setDouble(2, Double.parseDouble(amount));
			preparedStmt.setString(3, description);
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the fund.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// read
	public String readFund() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table width=100%> <thead> <tr><th>ID</th>" + "<th>Amount</th>" + "<th>Description</th>"
					+ "<th>Update</th>" + "<th>Remove</th></tr> </thead> ";

			String query = "SELECT * FROM fund";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String amount = Double.toString(rs.getDouble("amount"));
				String description = rs.getString("description");

				// Add into the html table
				// output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden'
				// value='" + id + "'></td>";
				output += "<td>" + id + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + description + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-default' data-itemid='" + id + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (

		Exception e) {
			output = "Error while reading the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update
	public String updateFund(String id, String amount, String description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "update fund set amount=?,description=? WHERE id=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setDouble(1, Double.parseDouble(amount));
			preparedStmt.setString(2, description);
			preparedStmt.setInt(3, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
		}

		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the fund\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// delete
	public String deleteFund(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from fund where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the fund.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}