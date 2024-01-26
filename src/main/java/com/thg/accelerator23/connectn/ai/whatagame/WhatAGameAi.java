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
  public void downDefend(){
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        //                down
        if (j < 5 && pos == 10 && b[i][j].equals(opp) && b[i][j + 1].equals(opp) && b[i][j + 2].equals(opp) && b[i][j + 3].isEmpty()) {
          pos = i;
          break;
        }}}
  }
  public void threeDownAttack(){
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        //                down
        if (j < 5 && pos == 10 && b[i][j].equals(marker) && b[i][j + 1].equals(marker) && b[i][j + 2].equals(marker) && b[i][j + 3].isEmpty()) {
          pos = i;
          break;
        }}}
  }
  public void threeRow(String currentMarker){
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {

//                diag
//                    /
        if (i < 7 && j < 5 && pos == 10 && b[i][j].equals(currentMarker) && b[i + 1][j + 1].equals(currentMarker) && b[i + 2][j + 2].equals(currentMarker)){
          if (j>0&&i>0&&b[i-1][j-1].isEmpty()){
            pos = i-1;
            break;
          }
          if (!b[i + 3][j + 2].isEmpty()&&b[i + 3][j + 3].isEmpty()){
            pos = i + 3;
            break;}
        }
//                    \
        if (i > 2 && j < 5 && pos == 10 && b[i][j].equals(currentMarker) && b[i - 1][j + 1].equals(currentMarker) && b[i - 2][j + 2].equals(currentMarker)){
          if (j>0&&i<9&&b[i+1][j-1].isEmpty()){
            pos = i+1;
            break;
          }
          if (b[i - 3][j + 3].isEmpty() && !b[i - 3][j + 2].isEmpty()){
            pos = i-3;
            break;}
        }

//                across
        if (i < 7 && pos == 10 && b[i][j].equals(currentMarker) && b[i + 1][j].equals(currentMarker) && b[i + 2][j].equals(currentMarker)) {
          int under = j;
          if (j > 0) {
            under -= 1;
          }
          if (b[i + 3][j].isEmpty()&&((j>0&&!b[i + 3][under].isEmpty())|| (j == 0&&b[i+3][0].isEmpty()))) {
            pos = i + 3;
            break;
          }
          if (i > 0 && b[i - 1][j].isEmpty()&&((j>0&&!b[i - 1][under].isEmpty()) || (j == 0&&b[i-1][0].isEmpty()))) {
            pos = i - 1;
            break;
          }
        }
        if (i>2 &&pos == 10 && b[i][j].equals(currentMarker) && b[i - 1][j].equals(currentMarker)) {
          int under = j;
          if (j > 0) {
            under -= 1;
          }
          if (b[i - 3][j].equals(currentMarker)&&((j>0&&!b[i - 2][under].isEmpty()) || (j == 0&&b[i-2][0].isEmpty()))){
            pos = i-2;
            break;
          }
        }
        if (i < 7 && pos == 10 && b[i][j].equals(currentMarker) && b[i + 1][j].equals(currentMarker)) {
          int under = j;
          if (j > 0) {
            under -= 1;
          }
          if (b[i + 3][j].equals(currentMarker)&&((j>0&&!b[i +2][under].isEmpty()) || (j == 0&&b[i+2][0].isEmpty()))){
            pos = i+2;
            break;
          }
        }


        if (i < 7 && j < 5 && pos == 10 && b[i][j].equals(currentMarker) && b[i + 1][j + 1].equals(currentMarker)) {
          if (b[i + 3][j + 3].equals(currentMarker) && b[i + 2][j + 2].isEmpty() && !b[i + 2][j + 1].isEmpty()) {
            pos = i + 2;
            break;
          }
        }

        if (i > 2 && j > 2 && pos == 10 && b[i][j].equals(currentMarker) && b[i - 1][j - 1].equals(currentMarker)) {
          if (b[i-3][j-3].equals(currentMarker)&&b[i-2][j-2].isEmpty()&&!b[i-2][j-1].isEmpty()){
            pos = i-2;
            break;}
        }
//               \
        if (i > 2 && j < 5 && pos == 10 && b[i][j].equals(currentMarker) && b[i - 1][j + 1].equals(currentMarker)) {
          if (b[i - 3][j + 3].equals(currentMarker) && b[i - 2][j + 2].isEmpty() && !b[i - 2][j + 1].isEmpty()) {
            pos = i - 2;
            break;
          }
        }
        if (i<7&&j>2&&b[i][j].equals(currentMarker)&&b[i+1][j-1].equals(currentMarker)){
          if(b[i+3][j-3].equals(currentMarker)&&b[i+2][j-2].isEmpty()&&!b[i+2][j-3].isEmpty()){
            pos = i+2;
            break;
          }
        }

      }
    }
  }

  public void attack() {
    threeRow(marker);
  }

  public void defend() {
    threeRow(opp);
  }
  public void predictDiag(String currentMarker) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
//                    /
        if (i < 7 && j < 5 && pos == 10 && b[i][j].equals(currentMarker) && b[i + 1][j + 1].equals(currentMarker)) {
          if (b[i + 3][j + 3].isEmpty() && !b[i + 3][j + 2].isEmpty()) {
            pos = i + 3;
            break;
          }
        }

        if (i > 2 && j > 2 && pos == 10 && b[i][j].equals(currentMarker) && b[i - 1][j - 1].equals(currentMarker)) {

          if (b[i - 3][j -3].isEmpty()&&((j>3&&!b[i - 3][j -4].isEmpty())||j==3)){
            pos = i - 3;
            break;}
        }
//               \
        if (i > 2 && j < 5 && pos == 10 && b[i][j].equals(currentMarker) && b[i - 1][j + 1].equals(currentMarker)){

          if (b[i-3][j+3].isEmpty()&&!b[i-3][j+2].isEmpty()){
            pos = i-3;
            break;
          }
          if (i<7&&j>2&&b[i][j].equals(currentMarker)&&b[i+1][j-1].equals(currentMarker)){

            if (b[i+3][j-3].isEmpty()&&((j>3&&!b[i+3][j-4].isEmpty())||j==3)){
              pos = i+3;
              break;
            }
          }
        }
      }
    }
  }
  public void twoRow(String currentMarker){
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
//                diag
        if (i < 8 && j < 6 && pos == 10 && b[i][j].equals(currentMarker) && b[i + 1][j + 1].equals(currentMarker) && b[i + 2][j + 2].isEmpty() && !b[i + 2][j + 1].isEmpty()) {
          pos = i + 2;
          break;
        }
        if (i > 1 && j < 6 && pos == 10 && b[i][j].equals(currentMarker) && b[i - 1][j + 1].equals(currentMarker) && b[i - 2][j + 2].isEmpty() && !b[i - 2][j + 1].isEmpty()) {
          pos = i - 2;
          break;
        }
////                down
//                    if (j < 6 && pos == 10 && b[i][j].equals(currentMarker) && b[i][j + 1].equals(currentMarker) && b[i][j + 2].isEmpty()) {
//                        pos = i;
//                        break;
//                    }
//                across
        if (i < 8 && pos == 10 && b[i][j].equals(currentMarker) && b[i + 1][j].equals(currentMarker)) {
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
  public void nextBestDefend() {
    twoRow(opp);
  }

  public void nextBestAttack() {
    twoRow(marker);
  }
  public void diagAttack(){
    predictDiag(marker);
    predictDiag(opp);
  }
  public void twoDown(String currentMarker){
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 8; j++) {
        if (j < 6 && pos == 10 && b[i][j].equals(currentMarker) && b[i][j + 1].equals(currentMarker) && b[i][j + 2].isEmpty()) {
          pos = i;
          break;
        }}
    }}
  public void twoDownAttack(){

    twoDown(marker);
    twoDown(opp);
  }
  public void randomMove() {
    pos = generateRandomPosition();
    while (board.hasCounterAtPosition(new Position(pos, board.getConfig().getHeight() - 2))) {
      pos = generateRandomPosition();
    }
  }
  public void lessRandomMove(){
    for (int i = 1; i < 9; i++) {
      for (int j = 0; j < 7; j++) {
        if (b[i][j].equals(marker)&&pos==10){
          if (b[i][j+1].isEmpty()){
            pos = i;
            break;
          }
          if (b[i-1][j].isEmpty()){
            pos = i-1;
            break;
          }
          if (b[i+1][j].isEmpty()){
            pos = i-1;
            break;
          }
        }
      }
    }
  }
  @Override
  public int makeMove(Board board) {
    pos = 10;
    this.board = board;
    setMarkers();
    convertBoard();
    threeDownAttack();
    downDefend();
    attack();
    if (pos == 10) {
      defend();
    }
    if (pos == 10) {
      nextBestDefend();
    }
    if (pos == 10){
      diagAttack();
    }
    if (pos == 10) {
      nextBestAttack();
    }

    if (pos==10){
      twoDownAttack();
    }

    if (pos == 10) {
      lessRandomMove();
    }
    if (pos == 10){
      randomMove();
    }
    if (pos!= 10){
      while (board.hasCounterAtPosition(new Position(pos, board.getConfig().getHeight()-1))) {
        pos = generateRandomPosition();
      }
    }
    return pos;
  }
}



