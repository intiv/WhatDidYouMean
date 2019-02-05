/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didyoumean;

/**
 *
 * @author josef
 */
public class Letra {
    char name;
    char leftletter;
    char rightletter;

    public Letra(char name, char leftletter, char rightletter) {
        this.name = name;
        this.leftletter = leftletter;
        this.rightletter = rightletter;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public char getLeftletter() {
        return leftletter;
    }

    public void setLeftletter(char leftletter) {
        this.leftletter = leftletter;
    }

    public char getRightletter() {
        return rightletter;
    }

    public void setRightletter(char rightletter) {
        this.rightletter = rightletter;
    }

    @Override
    public String toString() {
        return "Letra{" + "name=" + name + ", leftletter=" + leftletter + ", rightletter=" + rightletter + '}';
    }
    
    
}
