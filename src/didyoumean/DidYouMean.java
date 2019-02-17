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

    private static final String url = "jdbc:mysql://remotemysql.com:3306/PBXaLwJ21H?verifyServerCertificate=false&autoReconnect=true&useSSL=false";
    private static final String userForDB = "PBXaLwJ21H";
    private static final String passwordForDB = "hPE2MYmim9";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        // TODO code application logic here
        Automaton console = null;
        Functions functions = new Functions();
        if (new File("automata.bin").isFile()) {
            try {
                FileInputStream fis = new FileInputStream("automata.bin");
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true) {
                    try {
                        console = (Automaton) ois.readObject();
                    } catch (EOFException eofex) {
                        ois.close();
                        fis.close();
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            //Build initial automaton with initial commands (transitions for ls, vi, traceroute)
            console = new Automaton();
            //ls
            State q1 = new State(false);
            State q2 = new State(true);
            q1.addTransition(new Transition('s', q2, false));
            console.initial.addTransition(new Transition('l', q1, false));

            //vi
            State q3 = new State(false);
            State q4 = new State(true);
            q3.addTransition(new Transition('i', q4, false));
            console.initial.addTransition(new Transition('v', q3, false));

            //traceroute
            State q5 = new State(false);
            State q6 = new State(false);
            State q7 = new State(false);
            State q8 = new State(false);
            State q9 = new State(false);
            State q10 = new State(false);
            State q11 = new State(false);
            State q12 = new State(false);
            State q13 = new State(false);
            State q14 = new State(true);

            q13.addTransition(new Transition('e', q14, false));
            q12.addTransition(new Transition('t', q13, false));
            q11.addTransition(new Transition('u', q12, false));
            q10.addTransition(new Transition('o', q11, false));
            q9.addTransition(new Transition('r', q10, false));
            q8.addTransition(new Transition('e', q9, false));
            q7.addTransition(new Transition('c', q8, false));
            q6.addTransition(new Transition('a', q7, false));
            q5.addTransition(new Transition('r', q6, false));
            console.initial.addTransition(new Transition('t', q5, false));
            saveAutomaton(console);
        }

        //Console, commands input/verification
        while (true) {
            System.out.print("> ");
            reader = new Scanner(System.in);
            String command = reader.next();
            testCommand(console, command);
            saveAutomaton(console);
        }

    }

    public static void addCorrection(String command, String correction) {
        Statement stmt = null;
        try {
            Connection con = DriverManager.getConnection(url, userForDB, passwordForDB);
            stmt = con.createStatement();
            if (getCorrection(command).equals("")) {
                String sql = "INSERT INTO corrections (command, correction) VALUES ('" + command + "', '" + correction + "')";
                stmt.executeUpdate(sql);
                System.out.println("Adding correction was successful.");
            } else {
                System.out.println("Correction already in db");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getCorrections() {
        Statement stmt = null;
        ArrayList<String> corrections = new ArrayList<String>();
        try {
            Connection con = DriverManager.getConnection(url, userForDB, passwordForDB);
            stmt = con.createStatement();
            //Reset db
            //String sql1 = "DELETE FROM corrections";
            //stmt.executeUpdate(sql1);
            String sql = "SELECT command, correction FROM corrections";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Fetching corrections was successful.");
            while (rs.next()) {
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

    public static boolean testCommand(Automaton automaton, String command) {
        int testResult = automaton.testString(command);
        if (testResult == 1 || testResult == 2) {
            return true;
//        } else if (testResult == 2) {
//            System.out.println("Did you mean?");
        } else if (testResult == 0) {
            System.out.println("Command not found!");

        }
        return false;

    }

    public static String getCorrection(String cmdToSearch) {
        String command = "";
        Statement stmt = null;
        try {
            Connection con = DriverManager.getConnection(url, userForDB, passwordForDB);
            stmt = con.createStatement();
            String sql = "SELECT command FROM corrections WHERE correction='" + cmdToSearch + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                command = rs.getString("command");
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return command;
    }

    public static void saveAutomaton(Automaton auto) {
        try {
            FileOutputStream fos = new FileOutputStream("automata.bin", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(auto);
            oos.close();
        } catch (IOException ioex) {
            System.err.println("Error al guardar automata");
        }
    }

}
