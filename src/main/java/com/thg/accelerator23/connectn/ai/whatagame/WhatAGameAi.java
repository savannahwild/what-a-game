package com.thg.accelerator23.connectn.ai.whatagame;
import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Position;
import java.util.ArrayList;
import java.util.Random;
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
  public int generateRandomPosition(){
    Random rand = new Random();
    return rand.nextInt(10);
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
        if (i < 7 && j < 5 && pos == 10 && b[i][j].equals(marker) && b[i + 1][j + 1].equals(marker) && b[i + 2][j + 2].equals(marker)) {
          if ( b[i + 3][j + 3].isEmpty()&&!b[i + 3][j + 2].isEmpty()){
            pos = i + 3;
            break;}
          if (j>0&&i>0&&b[i -1][j-1].isEmpty()){
            pos = i-1;
            break;
          }
        }
        if (i > 2 && j < 5 && pos == 10 && b[i][j].equals(marker) && b[i - 1][j + 1].equals(marker) && b[i - 2][j + 2].equals(marker)){
          if (b[i - 3][j + 3].isEmpty() && !b[i - 3][j + 2].isEmpty()){
            pos = i - 3;
            break;}
          if (j>0&&i<9&&b[i+1][j-1].isEmpty()){
            pos = i+1;
            break;
          }
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
          if (i < 6 && b[i + 3][j].isEmpty()&&(!b[i + 3][under].isEmpty() || (j == 0&&b[i+3][0].isEmpty()))) {
            pos = i + 3;
            break;
          }
          if (i > 0 && b[i - 1][j].isEmpty()&&(!b[i - 1][under].isEmpty() || (j == 0&&b[i=1][0].isEmpty()))) {
            pos = i - 1;
            break;
          }
        }
      }
    }
  }

  public void defend() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
//                diag
        if (i < 7 && j < 5 && pos == 10 && b[i][j].equals(opp) && b[i + 1][j + 1].equals(opp) && b[i + 2][j + 2].equals(opp)){
          if (j>0&&i>0&&b[i-1][j-1].isEmpty()){
            pos = i-1;
            break;
          }
          if (!b[i + 3][j + 2].isEmpty()&&b[i + 3][j + 3].isEmpty()){
            pos = i + 3;
            break;}
        }
        if (i > 2 && j < 5 && pos == 10 && b[i][j].equals(opp) && b[i - 1][j + 1].equals(opp) && b[i - 2][j + 2].equals(opp)){
          if (j>0&&i<9&&b[i+1][j-1].isEmpty()){
            pos = i+1;
            break;
          }
          if (b[i - 3][j + 3].isEmpty() && !b[i - 3][j + 2].isEmpty()){
            pos = i-3;
            break;}
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
          if (i < 6 && b[i + 3][j].isEmpty()&&((j>0&&!b[i + 3][under].isEmpty()) || (j == 0&&b[i+3][0].isEmpty()))) {
            pos = i + 3;
            break;
          }
          if (i > 0 && b[i - 1][j].isEmpty()&&((j>0&&!b[i - 1][under].isEmpty()) || (j == 0&&b[i-1][0].isEmpty()))) {
            pos = i - 1;
            break;
          }
        }
        if (i < 9 &&i>1 &&pos == 10 && b[i][j].equals(opp) && b[i + 1][j].equals(opp)) {
          int under = j;
          if (j > 0) {
            under -= 1;
          }
          if (b[i - 2][j].equals(opp)&&((j>0&&!b[i - 1][under].isEmpty()) || (j == 0&&b[i-1][0].isEmpty()))){
            pos = i-1;
            break;
          }
        }
        if (i < 7 &&pos == 10 && b[i][j].equals(opp) && b[i + 1][j].equals(opp)) {
          int under = j;
          if (j > 0) {
            under -= 1;
          }
          if (b[i + 3][j].equals(opp)&&((j>0&&!b[i + 2][under].isEmpty()) || (j == 0&&b[i+2][0].isEmpty()))) {
            pos = i +2;
            break;
          }
        }
      }
    }
  }

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

//                across
        if (i < 8 && pos == 10 && b[i][j].equals(opp) && b[i + 1][j].equals(opp)) {
          int under = j;
          if (j > 0) {
            under -= 1;
          }
          if (i < 7 && b[i + 2][j].isEmpty()&&(!b[i + 2][under].isEmpty() || j == 0)) {
            pos = i + 2;
            break;
          }
          if (i > 0 && b[i - 1][j].isEmpty()&&(!b[i - 1][under].isEmpty() || j == 0)) {
            pos = i - 1;
            break;
          }
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
          if (i < 7 && b[i + 2][j].isEmpty()&&(!b[i + 2][under].isEmpty() || j == 0)) {
            pos = i + 2;
            break;
          }
          if (i > 0 && b[i - 1][j].isEmpty()&&(!b[i - 1][under].isEmpty() || j == 0)) {
            pos = i - 1;
            break;
          }
        }
        // down defend
        if (j < 5 && pos == 10 && b[i][j].equals(opp) && b[i][j + 1].equals(opp) && b[i][j + 2].isEmpty()) {
          pos = i;
          break;
        }
      }
    }
  }

  public void randomMove() {
    pos = generateRandomPosition();
    while (board.hasCounterAtPosition(new Position(pos, board.getConfig().getHeight() - 2))) {
      pos = generateRandomPosition();
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
    if (pos!= 10){
      while (board.hasCounterAtPosition(new Position(pos, board.getConfig().getHeight()))) {
        pos = generateRandomPosition();
      }
    }
    return pos;
  }
}


