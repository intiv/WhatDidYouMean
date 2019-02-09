/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.io.Serializable;

/**
 *
 * @author Inti Velásquez
 */
public class Transition implements Serializable{
	char token;
	State targetState;
	boolean isAlternate;
	
	public Transition(char token, State targetState, boolean isAlternate) {
		this.token = token;
		this.targetState = targetState;
		this.isAlternate = isAlternate;
    }
	
	
}
