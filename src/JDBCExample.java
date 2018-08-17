
//STEP 1. Import required packages
import java.sql.*;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/* Steps 
Import the package
Establish the Connection
Create a Statement object
Execute a query
Process the result
Close the connection
*/

public class JDBCExample {

	static Connection conn = null;
	Statement stmt = null;

	String insertSql = null;
	String sql = null;

	public static void main(String[] args) {

		JDBCExample first = new JDBCExample();

		first.getMentorsDetails();

		// first.insetDAO();
	}

	public Connection getConnection() {

		try {
			// STEP 2
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	@SuppressWarnings("resource")
	public void getMentorsDetails() {

		sql = "SELECT PersonID, LastName, FirstName, Address,City,MobileNo FROM t_mentors_details";
		ResultSet rs = null;
		try {

			conn = getConnection();
			// STEP 3
			stmt = conn.createStatement();
			// STEP 4
			rs = stmt.executeQuery(sql);
			
			 

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("PersonID");
				String lName = rs.getString("LastName");
				String fName = rs.getString("FirstName");
				String address = rs.getString("Address");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Last Name: " + lName);
				System.out.print(", First Name: " + fName);
				System.out.println(", Address: " + address);
			}
			
		System.out.println(" Get resultset with query parameter or where condtions");	
		
		sql = "SELECT PersonID, LastName, FirstName, Address,City,MobileNo FROM t_mentors_details where PersonID = 100";
		
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("PersonID");
			String lName = rs.getString("LastName");
			String fName = rs.getString("FirstName");
			String address = rs.getString("Address");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Last Name: " + lName);
			System.out.print(", First Name: " + fName);
			System.out.println(", Address: " + address);
		}
		
			System.out.println(" Get resultset with query parameter or where condtions with dynamic ");
			PreparedStatement prepareStatement = null;
			sql = "SELECT PersonID, LastName, FirstName, Address,City,MobileNo FROM t_mentors_details where PersonID =?";
				 
				// STEP 3
				prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, 100);
				rs = prepareStatement.executeQuery();
				while (rs.next()) {
					// Retrieve by column name
					int id = rs.getInt("PersonID");
					String lName = rs.getString("LastName");
					String fName = rs.getString("FirstName");
					String address = rs.getString("Address");

					// Display values
					System.out.print("ID: " + id);
					System.out.print(", Last Name: " + lName);
					System.out.print(", First Name: " + fName);
					System.out.println(", Address: " + address);
				}
			
			
		} catch (MySQLSyntaxErrorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// STEP 6: Clean-up environment

			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public int insetDAO() {

		insertSql = "insert into t_mentors_details(PersonID, LastName, FirstName, Address,City,MobileNo) values(?,?,?,?,?,?) ";
		int resultSet = 0;
		PreparedStatement prepareStatement = null;

		try {

			conn = getConnection();
			// STEP 3
			prepareStatement = conn.prepareStatement(insertSql);

			prepareStatement.setInt(1, 101);
			prepareStatement.setString(2, "Babu");
			prepareStatement.setString(3, "M");
			prepareStatement.setString(4, "OMR");
			prepareStatement.setString(5, "Chennai");
			prepareStatement.setString(6, "+91 9500XXXXX");

			// STEP 4
			resultSet = prepareStatement.executeUpdate();
			System.out.println(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// STEP 6: Clean-up environment

			try {
				prepareStatement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return resultSet;

	}

}