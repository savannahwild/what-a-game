package com.thg.accelerator23.connectn.ai.whatagame;
import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Position;
import com.thg.accelerator23.connectn.ai.whatagame.GenerateRandom;

import java.util.ArrayList;
public class WhatAGameAi extends Player {
    private String[][] b;
    private String marker;
    private String opp;
    private int pos = 10;
    private Board board;

    public WhatAGameAi(Counter counter) {
      //TODO: fill in your name here
      super(counter, WhatAGameAi.class.getName());
    }

    public void convertBoard() {
      this.b = new String[10][8];
      ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
      for (int y = board.getConfig().getHeight() - 1; y >= 0; y--) {
        for (int x = 0; x < board.getConfig().getWidth(); x++) {
          Counter counterAtPosition = board.getCounterAtPosition(new Position(x, y));
          String counterString = counterAtPosition != null ? counterAtPosition.getStringRepresentation() : "";
          b[x][y] = counterString;
        }
      }
    }

    public void setMarkers() {
      marker = getCounter().getStringRepresentation();
      if (marker.equals("O")) {
        opp = "X";
      } else {
        opp = "O";
      }
    }

    public void attack() {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 8; j++) {
//                diag
          if (i < 7 && j < 5 && pos == 10 && b[i][j].equals(marker) && b[i + 1][j + 1].equals(marker) && b[i + 2][j + 2].equals(marker) && b[i + 3][j + 3].isEmpty() && !b[i + 3][j + 2].isEmpty()) {
            pos = i + 3;
            break;
          }
          if (i > 2 && j < 5 && pos == 10 && b[i][j].equals(marker) && b[i - 1][j + 1].equals(marker) && b[i - 2][j + 2].equals(marker) && b[i - 3][j + 3].isEmpty() && !b[i - 3][j + 2].isEmpty()) {
            pos = i - 3;
            break;
          }
//                down
          if (j < 4 && pos == 10 && b[i][j].equals(marker) && b[i][j + 1].equals(marker) && b[i][j + 2].equals(marker) && b[i][j + 3].isEmpty()) {
            pos = i;
            break;
          }
//                across
          if (i < 7 && pos == 10 && b[i][j].equals(marker) && b[i + 1][j].equals(marker) && b[i + 2][j].equals(marker)) {
            int under = j;
            if (j > 0) {
              under -= 1;
            }
            if (i < 6 && b[i + 3][j].isEmpty()) {
              if (!b[i + 3][under].isEmpty() || j == 0) {
                pos = i + 3;
                break;
              }
            }
            if (i > 0 && b[i - 1][j].isEmpty()) {
              if (!b[i - 1][under].isEmpty() || j == 0) {
                pos = i - 1;
                break;
              }
            }
//                    if (i==6&&b[i-1][j].isEmpty()&&!b[i-1][under].isEmpty()){
//                        pos = i-1;
//                        break;
//                    }
            break;
          }
        }
      }
    }

    public void defend() {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 8; j++) {
//                diag
          if (i < 7 && j < 5 && pos == 10 && b[i][j].equals(opp) && b[i + 1][j + 1].equals(opp) && b[i + 2][j + 2].equals(opp) && b[i + 3][j + 3].isEmpty() && !b[i + 3][j + 2].isEmpty()) {
            pos = i + 3;
            break;
          }
          if (i > 2 && j < 5 && pos == 10 && b[i][j].equals(opp) && b[i - 1][j + 1].equals(opp) && b[i - 2][j + 2].equals(opp) && b[i - 3][j + 3].isEmpty() && !b[i - 3][j + 2].isEmpty()) {
            pos = i - 3;
            break;
          }
//                down
          if (j < 4 && pos == 10 && b[i][j].equals(opp) && b[i][j + 1].equals(opp) && b[i][j + 2].equals(opp) && b[i][j + 3].isEmpty()) {
            pos = i;
            break;
          }
//                across
          if (i < 7 && pos == 10 && b[i][j].equals(opp) && b[i + 1][j].equals(opp) && b[i + 2][j].equals(opp)) {
            int under = j;
            if (j > 0) {
              under -= 1;
            }
            if (i < 6 && b[i + 3][j].isEmpty()) {
              if (!b[i + 3][under].isEmpty() || j == 0) {
                pos = i + 3;
                break;
              }
            }
            if (i > 0 && b[i - 1][j].isEmpty()) {
              if (!b[i - 1][under].isEmpty() || j == 0) {
                pos = i - 1;
                break;
              }
            }
//                    if (i==6&&b[i-1][j].isEmpty()&&!b[i-1][under].isEmpty()){
//                        pos = i-1;
//                        break;
//                    }
            break;
          }
        }
      }
    }

    //    public void defend() {
//        for (int i=0; i < 10; i++){
//            for (int j=0;  j < 8;j++){
//                if (j<4 && pos==10){
//                    if (b[i][j].equals(opp)&&b[i][j+1].equals(opp)&&b[i][j+2].equals(opp)&&!b[i][j+3].equals(marker)) {
//                        pos = i;
//                        break;
//                    }
//                }
//                if (i<8 && pos==10){
//            if (b[i][j].equals(opp)&&b[i+1][j].equals(opp)&&b[i+2][j].equals(opp)){
//                if (i<7&&b[i+3][j].isEmpty()){
//                    pos = i+3;
//                    break;
//                }
//                else{
//                    if (i>0&&b[i-1][j].isEmpty()){
//                        pos = i-1;
//                        break;
//                    }}
//                if (i==7&&b[i-1][j].isEmpty()){
//                    pos = i-1;
//                    break;
//                }
//                break;
//            }
//        }
//    }}}
    public void nextBestDefend() {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 8; j++) {
//                diag
          if (i < 8 && j < 6 && pos == 10 && b[i][j].equals(opp) && b[i + 1][j + 1].equals(opp) && b[i + 2][j + 2].isEmpty() && !b[i + 2][j + 1].isEmpty()) {
            pos = i + 2;
            break;
          }
          if (i > 1 && j < 6 && pos == 10 && b[i][j].equals(opp) && b[i - 1][j + 1].equals(opp) && b[i - 2][j + 2].isEmpty() && !b[i - 2][j + 1].isEmpty()) {
            pos = i - 2;
            break;
          }
//                down
          if (j < 5 && pos == 10 && b[i][j].equals(opp) && b[i][j + 1].equals(opp) && b[i][j + 2].isEmpty()) {
            pos = i;
            break;
          }
//                across
          if (i < 8 && pos == 10 && b[i][j].equals(opp) && b[i + 1][j].equals(opp)) {
            int under = j;
            if (j > 0) {
              under -= 1;
            }
            if (i < 7 && b[i + 2][j].isEmpty()) {
              if (!b[i + 2][under].isEmpty() || j == 0) {
                pos = i + 2;
                break;
              }
            }
            if (i > 0 && b[i - 1][j].isEmpty()) {
              if (!b[i - 1][under].isEmpty() || j == 0) {
                pos = i - 1;
                break;
              }
            }
//                        if (i==7&&b[i-1][j].isEmpty()&&!b[i-1][under].isEmpty()){
//                            pos = i-1;
//                            break;
//                        }
            break;
          }
        }
      }
    }

    public void nextBestAttack() {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 8; j++) {
//                diag
          if (i < 8 && j < 6 && pos == 10 && b[i][j].equals(marker) && b[i + 1][j + 1].equals(marker) && b[i + 2][j + 2].isEmpty() && !b[i + 2][j + 1].isEmpty()) {
            pos = i + 2;
            break;
          }
          if (i > 1 && j < 6 && pos == 10 && b[i][j].equals(marker) && b[i - 1][j + 1].equals(marker) && b[i - 2][j + 2].isEmpty() && !b[i - 2][j + 1].isEmpty()) {
            pos = i - 2;
            break;
          }
//                down
          if (j < 5 && pos == 10 && b[i][j].equals(marker) && b[i][j + 1].equals(marker) && b[i][j + 2].isEmpty()) {
            pos = i;
            break;
          }
//                across
          if (i < 8 && pos == 10 && b[i][j].equals(marker) && b[i + 1][j].equals(marker)) {
            int under = j;
            if (j > 0) {
              under -= 1;
            }
            if (i < 7 && b[i + 2][j].isEmpty()) {
              if (!b[i + 2][under].isEmpty() || j == 0) {
                pos = i + 2;
                break;
              }
            }
            if (i > 0 && b[i - 1][j].isEmpty()) {
              if (!b[i - 1][under].isEmpty() || j == 0) {
                pos = i - 1;
                break;
              }
            }
//                    if (i==7&&b[i-1][j].isEmpty()&&!b[i-1][under].isEmpty()){
//                        pos = i-1;
//                        break;
//                    }
            break;
          }
        }
      }
    }

    public void randomMove() {
      GenerateRandom genRand = new GenerateRandom();
      pos = genRand.generate();
      while (board.hasCounterAtPosition(new Position(pos, board.getConfig().getHeight() - 2))) {
        pos = genRand.generate();
      }
    }

    @Override
    public int makeMove(Board board) {
      pos = 10;
      this.board = board;
      setMarkers();
      convertBoard();
      attack();
      if (pos == 10) {
        defend();
      }
      if (pos == 10) {
        nextBestDefend();
      }
      if (pos == 10) {
        nextBestAttack();
      }
      if (pos == 10) {
        randomMove();
      }
      return pos;
    }
  }


