package backend.academy.inputs;

import backend.academy.inputs.validate.CoordinateValidator;
import backend.academy.inputs.validate.IntervalValidator;
import backend.academy.primitives.coordinate.Coordinate;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

final public class Input {
    private static final Scanner INPUT = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    private static final PrintStream PRINT = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    private Input() {
    }

    public static int inputFromInterval(int min, int max) {
        IntervalValidator validator = new IntervalValidator(min, max);
        int number = 0;
        do {
            PRINT.print("Введите число от " + min + " до " + max + ": ");
            try {
                number = INPUT.nextInt();
                INPUT.nextLine();
            } catch (InputMismatchException e) {
                PRINT.println("Некорректный ввод. Введите число из диапазона");
                INPUT.nextLine();
                continue;
            }
            if (!validator.isValid(number)) {
                PRINT.println("Число вне диапазона. Попробуйте еще раз.");
            }
        } while (!validator.isValid(number));
        return number;
    }

    public static Coordinate inputCoordinate(int maxRow, int maxCol) {
        CoordinateValidator validator = new CoordinateValidator(maxRow, maxCol);
        Coordinate coordinate = null;

        do {
            PRINT.print("Введите координаты (строка, столбец) разделенные пробелом: ");
            String input = INPUT.nextLine();

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                PRINT.println("Некорректный ввод. Введите два числа,разделенные пробелом.");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                coordinate = new Coordinate(row, col);
            } catch (NumberFormatException e) {
                PRINT.println("Некорректный ввод.Введите два числа, разделенные пробелом.");
                continue;
            }

            if (!validator.isValid(coordinate)) {
                PRINT.println("Координаты вне допустимого диапазона. Попробуйте еще раз.");
            }
        } while (coordinate == null || !validator.isValid(coordinate)); // Продолжаем, пока координаты не будут валидны

        return coordinate;
    }
}
