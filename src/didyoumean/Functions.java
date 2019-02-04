/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.util.ArrayList;
import java.util.Random;

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
    
    
    
}
