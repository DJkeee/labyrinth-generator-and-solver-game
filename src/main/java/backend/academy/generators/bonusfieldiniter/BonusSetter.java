package backend.academy.generators.bonusfieldiniter;

import backend.academy.grid.MazeGrid;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import java.security.SecureRandom;
import java.util.Random;

/*
 * Класс BonusSetter отвечает за расстановку бонусных полей в лабиринте.
 *
 * <p>Он использует генератор случайных чисел для определения типа поля,
 * которое будет установлено в клетку лабиринта. Для каждого типа поля
 * задается определенная вероятность: для ловушки, монеты и дерева.
 * Затем класс проходит по всем клеткам лабиринта, кроме стен, и
 * устанавливает в них случайный тип поля, учитывая заданные вероятности.
 */
public class BonusSetter {
    private final Random random;
    final double trapProbability;
    final double coinProbability;
    double treeProbability;

    @SuppressWarnings("magicnumber")
    public BonusSetter() {
        this.random = new SecureRandom();
        trapProbability = 0.03;
        coinProbability = 0.04;
        treeProbability = 0.02;
    }

    public void setAllBonusFields(MazeGrid mazeGrid) {
        for (int x = 0; x < mazeGrid.getRows(); x++) {
            for (int y = 0; y < mazeGrid.getCols(); y++) {
                Coordinate coord = new Coordinate(x, y);
                if (mazeGrid.getCellType(coord) != CellType.WALL) {
                    CellType bonusField = generateBonusField();
                    mazeGrid.setCell(coord, bonusField);
                }
            }
        }
    }

    private CellType generateBonusField() {
        double randomValue = random.nextDouble();

        if (randomValue < trapProbability) {
            return CellType.TRAP;
        } else if (randomValue < trapProbability + coinProbability) {
            return CellType.COIN;
        } else if (randomValue < trapProbability + coinProbability + treeProbability) {
            return CellType.TREE;
        } else {
            return CellType.PASSAGE;
        }
    }
}
