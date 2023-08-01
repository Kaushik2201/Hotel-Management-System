
package hotel.management.system;

import java.sql.*;

public class Conn {
    
    // Step 2 - Creating an obj of connection interface 
    Connection c ;
    
    // Step 3 - Creating a statement
    Statement s ;
    Conn(){
        
        // Step 1 - Establishing / Setting up the drivers 
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem", "root", "Nunune@12");
            s = c.createStatement(); 
        }
        catch(Exception e){
            e.printStackTrace();
    }
}
}

 