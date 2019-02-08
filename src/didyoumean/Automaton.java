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
	
	private State advance(char token){
		
		current = initial;
		fail();
		return current;
	}
	
	public boolean testString(String command){
		for(int i = 0; i < command.length(); i++){
			char curr = command.charAt(i);
			Letter letter = map.getLetter(curr);
			for(Transition tr : current.transitions){
				if((tr.token == letter.getName() && !tr.isAlternate) || (tr.token == letter.getLeftletter() && tr.isAlternate) || (tr.token == letter.getRightletter() && tr.isAlternate) ){
					current = tr.targetState;
					System.out.println("Advanced succesfully");
				}
			}
			if(current.isFinal()){
				return true;
			}
		}
		
		//If normal test didn't reach the decision state, check for inverse string
		current = initial;
		for(int i = command.length() - 1; i >= 0; i--){
			char curr = command.charAt(i);
			Letter letter = map.getLetter(curr);
			for(Transition tr : current.transitions){
				if(tr.token == letter.getName()){
					current = tr.targetState;
					System.out.println("Advanced succesfully (Inverse string)");
				}
			}
			if(current.isFinal()){
				return true;
			}
		}
		current = initial;
		return false;
	}
	
	public void fail(){
		System.err.println("Transition failed, returning to initial state");
	}
}
