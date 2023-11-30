package ru.project.board;

import lombok.*;

@Getter
@Setter
@ToString
public class Coordinate {

   private int row;
   private int col;

   public Coordinate(int row, int col) {
      if (row < 0 || col < 0){
         throw new IllegalArgumentException("Координаты не могут быть отрицательными!");
      }
      this.row = row;
      this.col = col;
   }

   private Coordinate() {
   }
}
