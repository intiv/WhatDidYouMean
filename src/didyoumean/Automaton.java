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
public class Automaton {
	private State initial;
	
	public Automaton(){
		initial = new State();
	}
	
	public State getInitial(){
		return initial;
	}
	
	
}
