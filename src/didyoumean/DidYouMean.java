/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

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
      Scanner reader = new Scanner(System.in);
		  // TODO code application logic here
		Automaton test = null;
		if(new File("automata.bin").isFile()){
			try{
				FileInputStream fis = new FileInputStream("automata.bin");
				ObjectInputStream ois = new ObjectInputStream(fis);
				while(true){
					try{
						test = (Automaton) ois.readObject();
					}catch(EOFException eofex){
						ois.close();
						fis.close();
						break;
					}
				}
			} catch(IOException | ClassNotFoundException ex){
				ex.printStackTrace();
			}
		}else{
			test = new Automaton();
			State q2 = new State(true);
			State q1 = new State(false);
                        State q3 = new State(false);
                        State q4 = new State(true);
			q1.addTransition(new Transition('s', q2, false));  
                        q3.addTransition(new Transition('i', q4, false));
			test.current.addTransition(new Transition('l', q1, false));
                        test.current.addTransition(new Transition('v', q3, false));
                        
			saveAutomaton(test);
                        
		}
		while (true) {
			System.out.print("> ");
			reader = new Scanner(System.in);
			testCommand(test, reader.next());
                        saveAutomaton(test);
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
    
	public static void saveAutomaton(Automaton auto){
		try{
			FileOutputStream fos = new FileOutputStream("automata.bin", false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(auto);
			oos.close();
		}catch(IOException ioex){
			System.err.println("Error al guardar automata");
		}
	}

}
