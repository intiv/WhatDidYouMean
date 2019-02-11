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
        char originalToken;
	State targetState;
	State parentState;
	boolean isAlternate;
	
	public Transition(char token, State targetState, boolean isAlternate) {
		this.token = token;
		this.originalToken = token;
		this.targetState = targetState;
		this.isAlternate = isAlternate;
        }

        public Transition(char token, State targetState, State parentState, boolean isAlternate) {
            this.token = token;
            this.originalToken = token;
            this.targetState = targetState;
            this.parentState = parentState;
            this.isAlternate = isAlternate;
        }
        
        
        public Transition(char token, char originalToken, State targetState, State parentState, boolean isAlternate) {
            this.token = token;
            this.originalToken = originalToken;
            this.targetState = targetState;
            this.parentState = parentState;
            this.isAlternate = isAlternate;
        }
        
        
        
	

}
