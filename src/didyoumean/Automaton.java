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
	public State current;
	private LetterMap map = new LetterMap();
	
	public Automaton(){
		initial = new State();
		this.current = initial;
	}
	
	public State getInitial(){
		return initial;
	}
	
	public State advance(char token){
		Letter letter = map.getLetter(token);
		for(Transition tr : current.transitions){
			if(tr.token == letter.name || tr.token == letter.getLeftletter() || tr.token == letter.getRightletter()){
				current = tr.targetState;
				System.out.println("Advanced succesfully");
				return current;
			}
		}
		current = initial;
		fail();
		return current;
	}
	
	public void fail(){
		System.err.println("Transition failed, returning to initial state");
	}
}
