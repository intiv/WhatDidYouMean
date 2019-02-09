/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

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
            Scanner reader = new Scanner(System.in);
            Automaton test = new Automaton();
            State q2 = new State(false);
            State q3 = new State(true);
            q2.addTransition(new Transition('s', q3, false));
            test.current.addTransition(new Transition('l', q2, false));
            
            while (true) {
                System.out.print("> ");
                reader = new Scanner(System.in);
                testCommand(test, reader.next());
            }
	}
        
    public static void testCommand(Automaton automaton, String command) {
        int testResult = automaton.testString(command);
        if (testResult == 1) {
            System.out.println("Command found!");
//        } else if (testResult == 2) {
//            System.out.println("Did you mean?");
        } else if (testResult == 0) {
            System.out.println("No se encontró el comando");
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
    
}
