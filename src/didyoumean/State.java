/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

import java.util.ArrayList;

/**
 *
 * @author Inti Velásquez
 */
public class State {
	boolean decision;
	ArrayList<Transition> transitions = new ArrayList<Transition>();

	public State(boolean decision, ArrayList<Transition> transitions) {
		this.decision = decision;
		this.transitions = transitions;
	}

	public State(boolean decision) {
		this.decision = decision;
	}

	public State(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}
	
	public State(){
		decision = false;
		transitions = new ArrayList();
	}
	
	public void setFinal(boolean Final){
		decision = Final;
	}
	
	public boolean isFinal(){
		return decision;
	}
	
	public ArrayList<Transition> getTransitions(){
		return transitions;
	}
	
	public void addTransition(Transition transition) {
        transitions.add(transition);
    }
	
	public Transition getTransition(char token){
		for(Transition transition : transitions){
			if(transition.token == token){
				return transition;
			}
		}
		return null;
	}
	
	
}
