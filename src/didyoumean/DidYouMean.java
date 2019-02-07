/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Inti Velásquez
 */
public class DidYouMean {
    private static final String url = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3277559";
    private static final String userForDB = "sql3277559";
    private static final String passwordForDB = "8BZ3rdNQFc";
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
//            String command = "iv";
//            String correction = "vi";
//            addCorrection(command, correction);
            ArrayList<String> corrections = getCorrections();
            System.out.printf("%s\t->\t%s \n", "command", "correction");
            for (int i = 0; i < corrections.size(); i++) {
                System.out.println(corrections.get(i));
            }
	}
        
        public static void addCorrection (String command, String correction) {
            Statement stmt = null;
            try {
                Connection con = DriverManager.getConnection(url, userForDB, passwordForDB);
                System.out.println("Adding correction was successful.");
                stmt = con.createStatement();
                String sql = "INSERT INTO corrections (command, correction) VALUES ('" + command + "', '" + correction +"')";
                stmt.executeUpdate(sql);
            } catch (Exception e) {
                    e.printStackTrace();
            }
        }
        
        public static ArrayList<String> getCorrections () {
                Statement stmt = null;
                ArrayList<String> corrections = new ArrayList<String>();
            try {
                Connection con = DriverManager.getConnection(url, userForDB, passwordForDB);
                System.out.println("Fetching corrections was successful.");
                stmt = con.createStatement();
                String sql = "SELECT command, correction FROM corrections";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                   String command = rs.getString("command");
                   String correction = rs.getString("correction");
                   String output = String.format("%s\t->\t%s \n", command, correction);
                   corrections.add(output);
                }
                rs.close();
                return corrections;
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return corrections;
        }
	
}
