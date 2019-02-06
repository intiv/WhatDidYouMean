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
public class DidYouMean {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
            State q2 = new State(true);
            State q1 = new State(false);
            State begin = new State(false);
            
            //
            q1.addTransition(new Transition('S', q2));
            begin.addTransition(new Transition('L', q1));
            
            String cadena = "LS";
            State nextState = begin;
            for (int i = 0; i < cadena.length(); i++) {
                System.out.println("cadena.charAt(i) => " + cadena.charAt(i));
                Transition nextTransition = nextState.getTransition(cadena.charAt(i));
//                System.out.println(nextState.getTransitions());
                if (nextTransition != null) {
                    nextState = nextTransition.targetState;
                } else {
                    System.out.println("Cadena Inválida");
                    break;
                }
            }
	}
	
}
