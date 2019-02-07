/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Inti Velásquez
 */
public class DidYouMean {
    private static final String url = "jdbc:mysql://localhost:3306/wdym";
    private static final String userForDB = "admin";
    private static final String passwordForDB = "admin";
    
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		Automaton test = new Automaton();
		State q2 = new State(false);
		State q3 = new State(true);
		q2.addTransition(new Transition('a', q3));
		test.current.addTransition(new Transition('b', q2));
		test.advance('b');
		test.advance('c');
		test.advance('b');
		test.advance('a');
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
    
}
