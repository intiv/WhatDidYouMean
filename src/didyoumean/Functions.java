/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.io.File;
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
    public void ls (){
        Random random = new Random();
        System.out.println(this.palindromos.get(random.nextInt(5)));
        
    }
    
    //genera carita ascii dentro de un folder
    public void traceroute(){
        
        //Creacion de la carpeta con el nombre que le da el usuario
        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showSaveDialog(fileChooser);
        
        if (status == JFileChooser.APPROVE_OPTION){
            File directory = fileChooser.getSelectedFile();
            boolean created = directory.mkdir();
            if(created){
                //System.out.println("Archivo creado correctamente");
            }else{
                System.err.println("Error con la creacion del archivo");
            }
                    
        }
        
        
        
    }
    
}
