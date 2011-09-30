package com.twentysix20.misc;
/******************************************************************************
 *
 * Copyright (c) 2005 Peter Risser
 * All rights reserved.
 *
 * Created on Jul 2, 2005
 */


/**
 * @author Peter Risser (TPNR007)
 *
 */
public class MonteHall {
    
    public static int chooseDoor() {
        return (int)(Math.random() * 3); 
    }
    public static int chooseShow(int winner, int choice) {
        do {
            int show = chooseDoor();
            if ((show != winner) && (show != choice))
                return show;
        } while (true);
            
    }
    public static int chooseOtherDoor(int choice, int show) {
        do {
            int other = chooseDoor();
            if ((other != choice) && (other != show))
                return other;
        } while (true);
            
    }

    public static void main(String[] Args) {
        int wins = 0;
        int plays = 1000;
        for (int i = 0; i < plays; i++) {
            int winner = chooseDoor(); 
            int choice = chooseDoor();
            int show = chooseShow(winner, choice);
            int otherDoor = chooseOtherDoor(choice, show);
            if (otherDoor == winner) ++wins;
        }
        System.out.println("Wins: "+wins);
        System.out.println("Pctg: "+(wins*100/plays)+"%");
    }

}
