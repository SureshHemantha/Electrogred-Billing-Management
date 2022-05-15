package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Bill 
{ //A common method to connect to the DataBase
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.cj.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/apielectricity?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
										"root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertBill(String date, String account, String units, String total){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into billing (`Bid`,`B_date`,`B_account`,`B_units`,`B_total`)"+" values (?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, date); 
						preparedStmt.setString(3, account); 
						preparedStmt.setString(4, units); 
						preparedStmt.setDouble(5, Double.parseDouble(total)); 
						
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newBilling = readBilling(); 
						output = "{\"status\":\"success\",\"data\":\""+newBilling+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the bill.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readBilling() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" class=\"table\"><tr><th>ID</th><th>Bill Date</th>"
		 		+ "<th>Bill Account</th><th>Bill Units</th>"
		 		+ "<th>Bill Total Price</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from billing"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String Bid = Integer.toString(rs.getInt("Bid")); 
		 String B_date = rs.getString("B_date"); 
		 String B_account = rs.getString("B_account");
		 String B_units = rs.getString("B_units");
		 String B_total = Double.toString(rs.getDouble("B_total")); 
		 
		 // Add into the html table
		 output += "<tr><td><input id='hidBIDUpdate' name='hidBIDUpdate' type='hidden' value='"+Bid+"'>"+Bid+"</td>"; 
		 output += "<td>" + B_date + "</td>";
		 output += "<td>" + B_account + "</td>"; 
		 output += "<td>" + B_units + "</td>";
		 output += "<td>" + B_total + "</td>"; 
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-bid='" + Bid + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-bid='" + Bid + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the bill."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateBill(String Bid, String date, String account, String total, String units){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE billing SET B_date=?,B_account=?,B_total=?,B_units=? WHERE Bid=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, date); 
							preparedStmt.setString(2, account); 
							preparedStmt.setString(3, units); 
							preparedStmt.setDouble(4, Double.parseDouble(total)); 
							preparedStmt.setInt(5, Integer.parseInt(Bid)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newBilling = readBilling(); 
							output = "{\"status\":\"success\",\"data\":\""+newBilling+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the bill.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteBill(String Bid){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from billing where Bid=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(Bid)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newBilling = readBilling(); 
						 output = "{\"status\":\"success\",\"data\":\""+newBilling+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the bill.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
