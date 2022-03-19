package game;

import game.player.HumanPlayer;
import game.player.Player;
import game.player.RandomPlayer;
import game.player.SequentialPlayer;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {

    public static void main(String[] args) {
        CheckCorrectInput correctIO = new CheckCorrectInput();
        do {
            final Tournament tournament = new Tournament(chooseLog(correctIO), chooseSizeOfDesk(correctIO)
                    , chooseTypeOfPlayers(correctIO), chooseCircle(correctIO), correctIO);
            tournament.play();
            tournament.getTable();
            correctIO.println("Please, enter Yes if you want to start new tournament ");
            correctIO.println("Enter No if you don't");

        } while (correctIO.checkYesNoInput());
    }

    public static Player[] chooseTypeOfPlayers(final CheckCorrectInput correctIO) {
        int numbers;
        do {
            System.out.println("Please, choose number of players");
            numbers = correctIO.checkRightNounOfInt(1)[0];
            if (numbers != 1) {
                break;
            }
            correctIO.println("Sorry. You can't play alone");
        } while (true);
        Player[] players = new Player[numbers];
        for (int i = 0; i < numbers; i++) {
            players[i] = choosePlayer(i, correctIO);
        }
        return players;
    }

    public static Player choosePlayer(final int play, final CheckCorrectInput correctIO) {
        correctIO.println("Please,choose Player" + (play + 1));
        correctIO.println("1 - HumanPlayer");
        correctIO.println("2 - RandomPlayer");
        correctIO.println("3 - SequmentalPlayer");
        do {
            int cplayer = correctIO.checkRightNounOfInt(1)[0];
            if (cplayer == 1) {
                return new HumanPlayer(correctIO);
            } else if (cplayer == 2) {
                return new RandomPlayer();
            } else if (cplayer == 3) {
                return new SequentialPlayer();
            } else {
                correctIO.println("Please, type 1, 2, 3");
            }
        } while (true);
    }

    public static int[] chooseSizeOfDesk(final CheckCorrectInput correctIO) {
        correctIO.println("Please, choose width of the desk, length of the desk, number of symbols in a row for victory");
        return correctIO.checkRightNounOfInt(3);
    }

    public static boolean chooseLog(final CheckCorrectInput correctIO) {
        correctIO.println("Enter Yes if you want to see current game position");
        correctIO.println("Enter No if you don't");
        return correctIO.checkYesNoInput();
    }

    public static int chooseCircle(final CheckCorrectInput correctIO) {
        correctIO.println("Please, choose number of circles");
        return correctIO.checkRightNounOfInt(1)[0];
    }
}
