package com.thg.accelerator23.connectn.ai.whatagame;
import java.util.Random;

public class GenerateRandom {
    public int generate(){
        Random rand = new Random();
        return rand.nextInt(9);
   }
}
