/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import static didyoumean.DidYouMean.getCorrections;
import static didyoumean.DidYouMean.addCorrection;
import static didyoumean.DidYouMean.getCorrection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Inti Velásquez
 */
public class Automaton implements Serializable {

    public State initial;
    public State current;
    private LetterMap map = new LetterMap();

    public Automaton() {
        initial = new State();
        this.current = initial;
    }

    public State getInitial() {
        return initial;
    }

    private State advance(char token) {

        current = initial;
        fail();
        return current;
    }

    /**
     *
     * @param command
     * @return retVal if the command wasn't found, returns 0 if the command was
     * found, returns 1 if the command was learned, returns 2
     */
    public int testString(String command) {

        map = new LetterMap();
        // System.out.println(initial.transitions.size());
        int retVal = 0;
        String solution = "";
        String finalCommand = command;
        current = initial;
        ArrayList<Transition> tempTransitions = new ArrayList<Transition>();
        for (int i = 0; i < command.length(); i++) {
            char curr = command.charAt(i);
            Letter letter = map.getLetter(curr);
            Transition nextTr;
//                    System.out.println(letter.getName());
            if ((nextTr = current.getTransition(letter.getName())) != null) {
                current = nextTr.targetState;
                retVal = retVal == 0 ? 1 : retVal;
                solution += nextTr.originalToken;
//                        System.out.println("Advanced succesfully");
            } else {
                for (int j = 0; j < current.transitions.size(); j++) {
                    Transition tr = current.transitions.get(j);
                    Letter trLetter = map.getLetter(tr.token);
//                            System.out.println("left "+trLetter.leftletter);
//                            System.out.println("left "+trLetter.rightletter);
                    if (!tr.isAlternate && (trLetter.leftletter == letter.name || trLetter.rightletter == letter.name)) {
                        solution += tr.originalToken;
                        Transition newTransition = new Transition(letter.name, trLetter.name, tr.targetState, current, true);
                        current.addTransition(newTransition);
                        tempTransitions.add(newTransition);
                        current = tr.targetState;
                        retVal = 2;
                        break;
                    }
                }
            }
            if (current.isFinal()) {
                if (retVal == 2) {
                    System.out.println("Did you mean '" + solution + "'? y/n");
                    Scanner reader = new Scanner(System.in);
                    String resp = reader.next();
                    if (!resp.equals("y")) {
                        for (Transition tempTransition : tempTransitions) {
                            tempTransition.parentState.transitions.remove(tempTransition);
                        }
                    } else {
                        finalCommand = solution;
                        addCorrection(command, solution);
                    }
                } else {
                    String temp = getCorrection(command);
                    finalCommand = temp.equals("") ? solution : temp;
                }
                Functions functions = new Functions();
                if (solution.equals("ls")) {
                    ArrayList<String> corrections = getCorrections();
                    if (corrections.size() > 0) {
                        System.out.println("Correcciones:\n-------------");
                        for (int j = 0; j < corrections.size(); j++) {
                            System.out.println(j + ".-" + corrections.get(j));
                        }
                    } else {
                        System.out.println("No hay correcciones en la base de datos!");
                    }
                } else if (solution.equals("vi")) {
                    functions.vi();
                } else if (solution.equals("traceroute")) {
                    String face = "";
                    for (int l = 0; l < 5; l++) {
                        for (int m = 0; m < 11; m++) {
                            if (l == 0 && m == 5) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 1 && m == 3) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 3 && m == 2) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 1 && m == 7) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 3 && m == 8) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 3 && m == 4) {
                                //System.out.print("##");
                                face += "##";
                            } else if (l == 3 && m == 6) {
                                //System.out.print("##");
                                face += "##";
                            } else if (l == 4 && m == 4) {
                                //System.out.print("##");
                                face += "##";
                            } else if (l == 4 && m == 6) {
                                //System.out.print("##");
                                face += "##";
                            } else if (l == 4 && m == 0) {
                                //System.out.print(" ");

                                face += " ";
                            }
                            if (l == 3 || l == 4) {
                                if (m == 5) {
                                    //System.out.print("");

                                } else {
                                    //System.out.print("   ");

                                    face += "   ";
                                }
                            } else {
                                if (l != 6) {
                                    //System.out.print("   ");
                                    face += "   ";
                                } else {

                                }

                            }

                        }
                        face += "\n";
                    }

                    for (int l = 0; l < 4; l++) {
                        for (int m = 0; m < 11; m++) {
                            if (l == 3 && m == 5) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 2 && m == 3) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 1 && m == 2) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 2 && m == 7) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 1 && m == 8) {
                                //System.out.print("*");
                                face += "*";
                            } else if (l == 1 && m == 4) {
                                //System.out.print("______/");

                                face += "______/";
                            }

                            if (l == 1 && m == 4 || l == 1 && m == 5) {

                            } else {
                                //System.out.print("   ");
                                face += "   ";
                            }

                        }
                        if (l == 1) {
                            //System.out.println("");
                            face += "\n";
                        }
                        //System.out.println("");
                        face += "\n";

                    }
                    //System.out.println(face);
                    JFrame parent = new JFrame("Dialog");
                    parent.setAlwaysOnTop(true);

                    JFileChooser fileChooser = new JFileChooser();
                    int status = fileChooser.showSaveDialog(parent);

                    if (status == JFileChooser.APPROVE_OPTION) {
                        File directory = fileChooser.getSelectedFile();
                        boolean created = directory.mkdir();
                        if (created) {
                            //System.out.println("Archivo creado correctamente");
                        } else {
                            System.err.println("Error con la creacion del archivo");
                        }
                        String path = directory.getAbsolutePath();

                        System.out.println(path);

                        File smileyfile = new File(path + "\\smiley.txt");
                        try {
                            FileWriter fw = new FileWriter(smileyfile);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(face);
                            bw.flush();

                            bw.close();
                            fw.close();
                        } catch (Exception e) {

                        }

                    }
                }
                return retVal;
            }
        }

        //If normal test didn't reach the decision state, check for inverse string
        current = initial;
        solution = "";
        tempTransitions = new ArrayList<Transition>();
        for (int i = command.length() - 1; i >= 0; i--) {

            char curr = command.charAt(i);
            Letter letter = map.getLetter(curr);
            Transition nextTr;
            if ((nextTr = current.getTransition(letter.getName())) != null && !nextTr.isAlternate) {
                solution += letter.name;

                Transition newTransition = new Transition(command.charAt(command.length() - 1 - i), letter.name, nextTr.targetState, current, true);
                current.addTransition(newTransition);
                tempTransitions.add(newTransition);
                current = nextTr.targetState;
//                        System.out.println("Advanced succesfully");
            }
            if (current.isFinal()) {
                System.out.println("Did you mean '" + solution + "'? y/n");
                Scanner reader = new Scanner(System.in);
                String resp = reader.next();
                if (!resp.equals("y")) {
                    for (Transition tempTransition : tempTransitions) {
                        tempTransition.parentState.transitions.remove(tempTransition);
                    }
                } else {
                    //GUARDAR EN LA BD
                    Functions functions = new Functions();
                    if (solution.equals("ls")) {
                        ArrayList<String> corrections = getCorrections();
                        if (corrections.size() > 0) {
                            System.out.println("Correcciones:\n-------------");
                            for (int j = 0; j < corrections.size(); j++) {
                                System.out.println(j + ".-" + corrections.get(j));
                            }
                        } else {
                            System.out.println("No hay correcciones en la base de datos!");
                        }
                    } else if (solution.equals("vi")) {
                        functions.vi();
                    } else if (solution.equals("traceroute")) {
                        functions.traceroute();
                    }
                }
                return 2;
            }
        }
        current = initial;
        return 0;
    }

    public void fail() {
        System.err.println("Transition failed, returning to initial state");
    }

    @Override
    public String toString() {
        String retVal = "";
        int estado = 0;
        retVal += ("Q" + estado + ":\n-----\n");
        for (Transition t : current.transitions) {
            retVal += "Transicion: " + t.token;
        }
        return retVal;
    }

}
