package game;

import game.board.TicTacToeBoard;
import game.player.Player;

public class Tournament {

    private final boolean log;
    private final Player[] players;
    private final int numberOfCircles;
    private final CheckCorrectInput correctIO;
    private final int[] MNKData;
    private final int[] Win;
    private final int[] Lose;
    private final int[] Draw;

    public Tournament(final boolean log, final int[] MNKData, final Player[] players, final int numberOfCircles, final CheckCorrectInput correctIO) {
        this.log = log;
        this.MNKData = MNKData;
        this.players = players;
        this.numberOfCircles = numberOfCircles;
        this.correctIO = correctIO;
        this.Win = new int[players.length];
        this.Lose = new int[players.length];
        this.Draw = new int[players.length];
    }

    public void play() {
        for (int k = 0; k < numberOfCircles; k++) {
            for (int i = 0; i < players.length; i++) {
                for (int j = i + 1; j < players.length; j++) {
                    correctIO.println("Player " + (i + 1) + " Player " + (j + 1) + " are playing now");
                    final Game game = new Game(log, players[i], players[j], i, j);
                    final int answer = game.play(new TicTacToeBoard(MNKData[0], MNKData[1], MNKData[2]));
                    makeTable(i, j, answer - 1);
                    correctIO.println("Game result: " + answer);
                }
            }
        }
    }

    public void makeTable(final int i, final int j, final int answer) {
        if (answer == i) {
            Win[i]++;
            Lose[j]++;
        } else if (answer == j) {
            Win[j]++;
            Lose[i]++;
        } else {
            Draw[i]++;
            Draw[j]++;
        }
    }

    public void getTable() {
        correctIO.println("Player     Win   Lose  Draw  Score");
        for (int i = 0; i < players.length; i++) {
            correctIO.print(String.format("Player %-4d", (i + 1)));
            correctIO.print(String.format("%-6d", Win[i]));
            correctIO.print(String.format("%-6d", Lose[i]));
            correctIO.print(String.format("%-6d", Draw[i]));
            correctIO.println(String.format("%-7d", Draw[i] + Win[i] * 3));
        }
    }
}
