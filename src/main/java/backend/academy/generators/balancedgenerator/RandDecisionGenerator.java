package backend.academy.generators.balancedgenerator;

import backend.academy.generators.Generator;
import backend.academy.generators.bonusfieldiniter.BonusSetter;
import backend.academy.grid.MazeGrid;
import backend.academy.maze.Maze;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Класс BalancedGenerator реализует интерфейс Generator и отвечает за
 * генерацию сбалансированного лабиринта.
 * Алгоритм работы:
 * 1. Создается сетка (Grid) заданного размера, которая будет представлять лабиринт,
 * как матрицу [rows][cols].
 * 2. Инициализируется массив множеств (Set) для хранения координат ячеек каждой строки.
 * 3. Происходит итерация по всем ячейкам сетки:
 * - Если текущая ячейка не первая в строке, случайным образом определяется,
 * будет ли она проходом. Если да, то устанавливается проход между текущей
 * ячейкой и предыдущей.
 * - Каждая ячейка добавляется в соответствующее множество координат строки.
 * 4. После обработки всех ячеек в строке, если это не последняя строка,
 * выбирается случайная ячейка из текущего множества координат и создается
 * проход между этой ячейкой и ячейкой в следующей строке.
 * 5. После завершения генерации основной структуры лабиринта,
 * вызывается генератор бонусных полей для установки бонусов в лабиринте.
 * 6. Возвращается сгенерированный лабиринт (Maze).
 */
public final class RandDecisionGenerator implements Generator {
    private final Random random = new SecureRandom();
    private final BonusSetter bonusFieldGenerator = new BonusSetter();

    @Override
    public Maze generate(int row, int col) {
        MazeGrid mazeGrid = new MazeGrid(row, col);
        Set<Coordinate>[] rows = new Set[row];
        for (int i = 0; i < row; i++) {
            rows[i] = new HashSet<>();
        }

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (y > 0) {
                    if (random.nextBoolean()) {
                        mazeGrid.setCell(new Coordinate(x, y - 1), CellType.PASSAGE);
                        mazeGrid.setCell(new Coordinate(x, y), CellType.PASSAGE);
                    }
                }
                rows[x].add(new Coordinate(x, y));
            }

            if (x < row - 1) {
                Coordinate northCell = getRandomCell(rows[x]);
                mazeGrid.setCell(new Coordinate(x, northCell.col()), CellType.PASSAGE);
                mazeGrid.setCell(new Coordinate(x + 1, northCell.row()), CellType.PASSAGE);
            }
        }
        bonusFieldGenerator.setAllBonusFields(mazeGrid);
        return new Maze(row, col, mazeGrid);
    }

    public Coordinate getRandomCell(Set<Coordinate> cells) {
        int index = random.nextInt(cells.size());
        int i = 0;
        for (Coordinate cell : cells) {
            if (i == index) {
                return cell;
            }
            i++;
        }
        return null;
    }

}
