/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

/**
 *
 * @author Inti Velásquez
 */
public class Transition {
	char token;
	State targetState;
	
	public Transition(){
		targetState = new State();
		
	}
}
