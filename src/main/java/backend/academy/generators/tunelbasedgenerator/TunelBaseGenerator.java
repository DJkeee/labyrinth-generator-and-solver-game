package backend.academy.generators.tunelbasedgenerator;

import backend.academy.generators.Generator;
import backend.academy.generators.bonusfieldiniter.BonusSetter;
import backend.academy.grid.MazeGrid;
import backend.academy.maze.Maze;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import java.security.SecureRandom;
import java.util.Random;

/*
 * Класс TunelBasedGenerator реализует интерфейс Generator и отвечает за
 * генерацию лабиринта на основе большого тунеля.
 * Алгоритм работы:
 * 1. Создается сетка (Grid) заданного размера, которая будет представлять лабиринт.
 * 2. Инициализируется генератор бонусных полей (BonusFieldGenerator).
 * 3. Происходит итерация по всем строкам сетки:
 * - Для каждой строки выбирается случайная начальная ячейка (startX).
 * - Устанавливается переменная endX, которая будет использоваться для создания прохода.
 * 4. Внутри каждой строки:
 * - Устанавливается проход (CellType.PASSAGE) для текущей ячейки.
 * - С вероятностью 50% выбирается направление для создания вертикального прохода вверх
 * в предыдущую строку, если это возможно.
 * - Увеличивается значение endX, чтобы продолжить создание проходов вправо.
 * 5. *Смещение генерации лабиринта:*
 * - параметр `offset`, который определяет сдвиг генерации лабиринта от краев.
 * - Циклы генерации туннелей теперь выполняются в пределах `offset` от краев, что приводит
 * к смещению лабиринта ближе к центру.
 * 6. После завершения создания структуры лабиринта, вызывается генератор бонусных полей
 * для установки бонусов в лабиринте.
 * 7. Возвращается сгенерированный лабиринт (Maze).
 */

public final class TunelBaseGenerator implements Generator {
    private static final Random RANDOM = new SecureRandom();
    private static final BonusSetter BONUSFIELDGENERATOR = new BonusSetter();
    final static double CHANCEOFPASS = 0.5;
    final static int DIRMAXVALUE = 3;
    final static int OFFSET = 2;

    @Override
    public Maze generate(int row, int col) {
        MazeGrid mazeGrid = new MazeGrid(row, col);

        for (int x = OFFSET; x < mazeGrid.getRows() - OFFSET; x++) {
            int endX = RANDOM.nextInt(mazeGrid.getCols() - 2 * OFFSET) + OFFSET;
            while (endX < mazeGrid.getCols() - OFFSET) {
                Coordinate coord = new Coordinate(x, endX);
                mazeGrid.setCell(coord, CellType.PASSAGE);
                if (RANDOM.nextDouble() < CHANCEOFPASS) {
                    int dir = RANDOM.nextInt(DIRMAXVALUE);
                    if ((dir == 0) && x > OFFSET) {
                        Coordinate upCoord = new Coordinate(x - 1, endX);
                        mazeGrid.setCell(upCoord, CellType.PASSAGE);
                    }
                }
                endX++;
            }
        }

        // Генерация бонусных полей
        BONUSFIELDGENERATOR.setAllBonusFields(mazeGrid);
        return new Maze(row, col, mazeGrid);
    }
}
