/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Inti Velásquez
 */
public class DidYouMean {
    private static final String url = "jdbc:mysql://localhost:3306/wdym";
    private static final String userForDB = "admin";
    private static final String passwordForDB = "admin";
    public static ArrayList<Letra> lettersmap=new ArrayList<Letra>();
    public static char arreglo[] ={'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
    
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
                System.out.println("hola mundo");
                crear_arreglo();
                String command = "sl";
                String correction = "ls";
                addCorrection(command, correction);
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
    public  static void crear_arreglo(){
        for (int i = 0; i < arreglo.length; i++) {
            System.out.println(i+"aaaaaa"+arreglo[i]);
        }
        lettersmap.add(new Letra('q','w','a'));
        for (int i = 1; i <=8; i++) {
            Letra letter =new Letra(arreglo[i],arreglo[i-1],arreglo[i+1]);
           // System.out.println("wwwwwwwwww");
            lettersmap.add(letter);
            letter=null;
        }
        lettersmap.add(new Letra('p','o','l'));
        lettersmap.add(new Letra('a','q','s'));
        for (int i = 11; i <=17; i++) {
            Letra letter =new Letra(arreglo[i],arreglo[i-1],arreglo[i+1]);
            lettersmap.add(letter);
            letter=null;
        }
        lettersmap.add(new Letra('l','o','k'));
        lettersmap.add(new Letra('z','s','x'));
        for (int i = 20; i <=24; i++) {
            Letra letter =new Letra(arreglo[i],arreglo[i-1],arreglo[i+1]);
            lettersmap.add(letter);
            letter=null;
        }
        lettersmap.add(new Letra('m','n','j'));
        for (Letra letra : lettersmap) {
            System.out.println(letra);
        }
        
    }
}
