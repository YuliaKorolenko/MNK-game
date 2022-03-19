package game.board;

import game.Cell;
import game.Move;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Position {
    boolean isValid(Move move);

    int getRow();

    int getColumn();

    Cell getCell(int r, int c);
}
