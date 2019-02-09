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
			State q2 = new State(false);
			State q3 = new State(true);
			q2.addTransition(new Transition('a', q3, false));
			test.current.addTransition(new Transition('b', q2, false));
			test.current.addTransition(new Transition('a', q3, false));
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
