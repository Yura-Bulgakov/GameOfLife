package ru.project.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Coordinate {
   private int row;
   private int col;

   @Override
   public String toString() {
      return "Coordinate{" +
              "row=" + row +
              ", col=" + col +
              '}';
   }
}
