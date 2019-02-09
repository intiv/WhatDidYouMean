/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Inti Velásquez
 */
public class LetterMap implements Serializable{
	public static ArrayList<Letter> lettersmap=new ArrayList<Letter>();
    public static char arreglo[] ={'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
    
	public LetterMap(){
		
        lettersmap.add(new Letter('q','w','a'));
        for (int i = 1; i <=8; i++) {
            Letter letter =new Letter(arreglo[i],arreglo[i-1],arreglo[i+1]);
           // System.out.println("wwwwwwwwww");
            lettersmap.add(letter);
            letter = null;
        }
        lettersmap.add(new Letter('p','o','l'));
        lettersmap.add(new Letter('a','q','s'));
        for (int i = 11; i <=17; i++) {
            Letter letter =new Letter(arreglo[i],arreglo[i-1],arreglo[i+1]);
            lettersmap.add(letter);
            letter = null;
        }
        lettersmap.add(new Letter('l','o','k'));
        lettersmap.add(new Letter('z','s','x'));
        for (int i = 20; i <=24; i++) {
            Letter letter =new Letter(arreglo[i],arreglo[i-1],arreglo[i+1]);
            lettersmap.add(letter);
            letter = null;
        }
        lettersmap.add(new Letter('m','n','j'));
//        for (Letter letra : lettersmap) {
//            System.out.println(letra);
//        } 
	}
	
	public Letter getLetter(char token){
		for(Letter letter : lettersmap){
			if(letter.name == token){
				return letter;
			}
		}
		return null;
	}
}
