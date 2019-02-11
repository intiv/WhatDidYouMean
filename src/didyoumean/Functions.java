/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;

/**
 *
 * @author ricar
 */
public class Functions {

    private ArrayList<String> palindromos = new ArrayList();

    public Functions() {
        //Se inicia el llenado de palindromos
        this.palindromos.add("Sometamos o matemos");
        this.palindromos.add("Robaba oro a babor");
        this.palindromos.add("Somos o no somos");
        this.palindromos.add("Yo dono rosas, oro no doy");
        this.palindromos.add("Anula la luz azul a la Luna");
    }

    //funcion para llamar un palindromo
    public void vi() {
        Random random = new Random();
        System.out.println(this.palindromos.get(random.nextInt(5)));

    }

    //genera carita ascii dentro de un folder
    public void traceroute() {

        //Creacion de la cara ASCII
        String face = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 0 && j == 5) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 1 && j == 3) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 3 && j == 2) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 1 && j == 7) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 3 && j == 8) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 3 && j == 4) {
                    //System.out.print("##");
                    face += "##";
                } else if (i == 3 && j == 6) {
                    //System.out.print("##");
                    face += "##";
                } else if (i == 4 && j == 4) {
                    //System.out.print("##");
                    face += "##";
                } else if (i == 4 && j == 6) {
                    //System.out.print("##");
                    face += "##";
                } else if (i == 4 && j == 0) {
                    //System.out.print(" ");

                    face += " ";
                }
                if (i == 3 || i == 4) {
                    if (j == 5) {
                        //System.out.print("");

                    } else {
                        //System.out.print("   ");

                        face += "   ";
                    }
                } else {
                    if (i != 6) {
                        //System.out.print("   ");
                        face += "   ";
                    } else {

                    }

                }

            }
            face += "\n";
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 3 && j == 5) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 2 && j == 3) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 1 && j == 2) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 2 && j == 7) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 1 && j == 8) {
                    //System.out.print("*");
                    face += "*";
                } else if (i == 1 && j == 4) {
                    //System.out.print("______/");

                    face += "______/";
                }

                if (i == 1 && j == 4 || i == 1 && j == 5) {

                } else {
                    //System.out.print("   ");
                    face += "   ";
                }

            }
            if (i == 1) {
                //System.out.println("");
                face += "\n";
            }
            //System.out.println("");
            face += "\n";

        }
        //System.out.println(face);

        
        //Creacion de la carpeta con el nombre que le da el usuario
        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showSaveDialog(fileChooser);

        if (status == JFileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();
            boolean created = directory.mkdir();
            if (created) {
                //System.out.println("Archivo creado correctamente");
            } else {
                System.err.println("Error con la creacion del archivo");
            }
            String path = directory.getAbsolutePath();

            //System.out.println(path);

            File smileyfile = new File("smiley.txt");
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

}
