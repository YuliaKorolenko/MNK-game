package game.board;

import game.Cell;
import game.Move;
import game.Result;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int columns, rows, k;
    private int quantityOfEmptyCells;

    public TicTacToeBoard(final int columns, final int rows, final int k) {
        this.cells = new Cell[rows][columns];
        this.columns = columns;
        this.rows = rows;
        this.k = k;
        this.quantityOfEmptyCells = rows * columns;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        quantityOfEmptyCells--;

        cells[move.getRow()][move.getColumn()] = move.getValue();
        int i = move.getRow();
        int j = move.getColumn();
        int inDiag1 = countOfMove(i, j, 1, 1) + countOfMove(i, j, -1, -1) - 1;
        int inDiag2 = countOfMove(i, j, 1, -1) + countOfMove(i, j, -1, 1) - 1;
        int inRow = countOfMove(i, j, 0, 1) + countOfMove(i, j, 0, -1) - 1;
        int inColumn = countOfMove(i, j, 1, 0) + countOfMove(i, j, -1, 0) - 1;

        if (inRow >= k || inColumn >= k || inDiag1 >= k || inDiag2 >= k) {
            return Result.WIN;
        }
        if (quantityOfEmptyCells == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    public int countOfMove(int i, int j, final int addi, final int addj) {
        int answer = 0;
        while (i < rows && j < columns && i >= 0 && j >= 0 && cells[i][j] == turn) {
            i += addi;
            j += addj;
            answer++;
        }
        return answer;
    }


    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < rows
                && 0 <= move.getColumn() && move.getColumn() < columns
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        int countOfRange = Integer.toString(Math.max(columns, rows)).length() + 1;
        final StringBuilder sb = new StringBuilder(" ".repeat(countOfRange));
        for (int i = 0; i < columns; i++) {
            sb.append(String.format("%" + countOfRange + "d", i + 1));
        }
        for (int r = 0; r < rows; r++) {
            sb.append("\n");
            sb.append(String.format("%" + countOfRange + "d", r + 1));
            for (int c = 0; c < columns; c++) {
                sb.append(String.format("%" + countOfRange + "c", SYMBOLS.get(cells[r][c])));
            }
        }
        return sb.toString();
    }

    @Override
    public int getColumn() {
        return columns;
    }

    @Override
    public int getRow() {
        return rows;
    }
}
