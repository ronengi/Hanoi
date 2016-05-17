/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoi;

/**
 *
 * @author stimpy
 */
public class Hanoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Pegs hanoi = new Pegs(3, 6);
        // System.out.println(hanoi);

        hanoi.moveTower(5, 0, 1, 2);

        // System.out.println(hanoi);

    }
    
}
