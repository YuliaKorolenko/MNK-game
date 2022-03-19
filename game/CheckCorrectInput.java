package game;

import java.io.PrintStream;
import java.util.Scanner;

public class CheckCorrectInput {
    private final PrintStream out;
    private final Scanner sc;

    public CheckCorrectInput() {
        this.sc = new Scanner(System.in);
        this.out = System.out;
    }

    public void println(final Object message) {
        out.println(message);
    }

    public void print(final Object message) {
        out.print(message);
    }

    public boolean checkYesNoInput() {
        while (true) {
            String yesNo = sc.nextLine();
            if (yesNo.equals("Yes")) {
                return true;
            }
            if (yesNo.equals("No")) {
                return false;
            }
            out.println("Please, enter Yes or No");
        }
    }

    public int[] checkRightNounOfInt(final int number) {

        flag:
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] strings = line.split(" ");
            if (strings.length != number) {
                out.println("Please enter " + number + " numbers");
                continue;
            }
            int[] result = new int[number];
            try {
                for (int i = 0; i < number; i++) {
                    result[i] = Integer.parseInt(strings[i]);
                }
            } catch (NumberFormatException e) {
                out.println("Please enter " + number + " integer numbers");
                continue;
            }

            if (result.length == 3) {
                if (Math.max(result[0], result[1]) < result[2]) {
                    out.println("Sorry, wrong k. Value of k is too big for this field. Try again.");
                    continue;
                }
            }
            for (int i = 0; i < result.length; i++) {
                if (result[i] <= 0) {
                    out.println("Please, enter " + result.length + " positive integer");
                    continue flag;
                }
            }

            return result;

        }
        throw new AssertionError("Error input");

    }
}
