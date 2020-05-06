package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Nurse {

	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremanagement", "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String readNurses(){
		String output = "";
		
	try{
		Connection con = connect();
		
	 if (con == null){
		 return "Error while connecting to the database for reading.";
	 }

	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Nurse ID</th>"+"<th>First Name</th><th>Last Name</th>"
	 + "<th>NIC</th>" + "<th>Address</th>" + "<th>Email</th>" + "<th>Update</th><th>Remove</th></tr>";
	 
	 String query = "select * from nurse";
	 
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next()){
		 String nurseID = Integer.toString(rs.getInt("nurse_id"));
		 String fName = rs.getString("first_name");
		 String lName = rs.getString("last_name");
		 String nic = rs.getString("nic");
		 String address = rs.getString("address");
		 String email = rs.getString("email");
		 
		 // Add into the html table
		 output += "<tr><td><input id=\"hidnurseIDUpdate\" name=\"hidnurseIDUpdate\" type=\"hidden\" value=\"" + nurseID + "\">"
				 + nurseID + "</td>";
				 output += "<td>" + fName + "</td>";
				 output += "<td>" + lName + "</td>";
				 output += "<td>" + nic + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + email + "</td>";
				 // buttons
				 output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" "
				 		+ "class=\" btnUpdate btn btn-secondary\"></td> "
				 		+ "<td><form method=\"post\" action=\"nurses.jsp\"> "
				 		+ "<input name=\"btnRemove\" type=\"submit\" "
				 		+ "value=\"Remove\" class=\"btn btn-danger\"> "
				 		+ "<input name=\"hidnurseIDDelete\" type=\"hidden\" "
				 		+ "value=\"" + nurseID + "\">" + "</form></td></tr>"; 
	 		}	
		 con.close();
		 // Complete the html table
		 output += "</table>"; 
	 	}
		catch (Exception e)
		 {
			 output = "Error while reading nurses.";
			 System.err.println(e.getMessage());
		 }
		return output;
	}
	
	public String addNurses(String fName, String lName, String nic, String address, String email)
	{
		 String output = "";
		 
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database";
		 }
		 // create a prepared statement
		 String query = " insert into nurse(nurse_id,first_name,last_name,nic,adress,email) values(?,?,?,?,?,?)";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, fName);
		 preparedStmt.setString(3, lName);
		 preparedStmt.setString(4, nic);
		 preparedStmt.setString(5, address);
		 preparedStmt.setDouble(6, email);
		
		//execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		catch (Exception e)
		 {
		 output = "Error while inserting";
		 System.err.println(e.getMessage());
		 }
		return output;
	}
	
	
	
	public String updateNurses(String nurseID, String fName, String lName, String nic, String address, String email) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE nurse SET first_name=?,last_name=?,nic=?,address=?,email=? WHERE nurse_id=?";
			
			PreparedStatement preparedStmnt = con.prepareStatement(query);
			
			preparedStmnt.setString(1, 	fName);
			preparedStmnt.setString(2, lName);
			preparedStmnt.setString(3, 	nic);
			preparedStmnt.setString(4, address);
			preparedStmnt.setString(5, email);
			preparedStmnt.setInt(6, Integer.parseInt(nurseID));
			
			preparedStmnt.execute();
			con.close();
			
			output="Updated Successfully";
		}catch(Exception e) {
			output="error while updating the nurses.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	
}
