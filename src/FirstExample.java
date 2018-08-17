//STEP 1. Import required packages
import java.sql.*;

public class FirstExample {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/testleaf";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "MyNewPass";
   
   public static void main(String[] args) {
	   
	   
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      
      // insert
      
     // String insertSql = "insert into t_mentors_details values(?,?,?,?,?,?) ";
      
      String insertSql = "insert into t_mentors_details(PersonID, LastName, FirstName, Address,City,MobileNo) values(?,?,?,?,?,?) ";
      
      
      PreparedStatement prepareStatement;
      
      prepareStatement = conn.prepareStatement(insertSql);
      
      prepareStatement.setInt(1, 101);
      prepareStatement.setString(2, "Babu");
      prepareStatement.setString(3, "M");
      prepareStatement.setString(4, "OMR");
      prepareStatement.setString(5, "Chennai");
      prepareStatement.setString(6, "+91 9500XXXXX");
      
     int resultSet =  prepareStatement.executeUpdate();
     
     System.out.println(resultSet);
      
      
      sql = "SELECT PersonID, LastName, FirstName, Address,City,MobileNo FROM t_mentors_details";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("PersonID");
         String lName = rs.getString("LastName");
         String fName = rs.getString("FirstName");
         String address = rs.getString("Address");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Last Name: " + lName);
         System.out.print(", First Name: " + fName);
         System.out.println(", address: " + address);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("DB Connection success!");
}
}