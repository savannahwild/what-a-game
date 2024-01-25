package com.thg.accelerator23.connectn.ai.whatagame;
import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thg.accelerator23.connectn.ai.whatagame.GenerateRandom;
public class WhatAGameAi extends Player {
  public WhatAGameAi(Counter counter) {
    //TODO: fill in your name here
    super(counter, WhatAGameAi.class.getName());
  }
  @Override
  public int makeMove(Board board) {
    GenerateRandom genRand = new GenerateRandom();
    return genRand.generate();
  }
}
