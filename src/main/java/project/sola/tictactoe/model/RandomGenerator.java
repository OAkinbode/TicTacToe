package project.sola.tictactoe.model;


import org.springframework.stereotype.Component;

import java.util.Random;


public class RandomGenerator {

    /**
     * creates a random number ranging between lo and hi,
     * @param lo
     * @param hi
     * @return
     */
    int discrete(int lo, int hi)
    {
        if(lo >= hi){
            System.out.println("Error discrete, lo >= hi");
            System.exit(0);
        }

        Random r = new Random();
        int d = r.nextInt(hi - lo + 1) + lo;
        return d;
    }

}
