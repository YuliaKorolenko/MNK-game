package game.player;

import game.Cell;
import game.CheckCorrectInput;
import game.Move;
import game.board.Position;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final CheckCorrectInput correctIO;

    public HumanPlayer(final CheckCorrectInput correctIO) {
        this.correctIO = correctIO;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            correctIO.println("Position");
            correctIO.println(position);
            correctIO.println(cell + "'s move");
            correctIO.println("Enter row and column");
            final int[] a = correctIO.checkRightNounOfInt(2);
            final Move move = new Move(a[0] - 1, a[1] - 1, cell);
            if (position.isValid(move)) {
                return move;
            }
            correctIO.println("Move " + move + " is invalid");
        }
    }
}
