package backend.academy.samples;

import backend.academy.generators.tunelbasedgenerator.TunelBaseGenerator;
import backend.academy.maze.Maze;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TunelBaseGeneratorTest {

    @Test
    void testGenerateMaze() {
        TunelBaseGenerator generator = new TunelBaseGenerator();
        int rows = 5;
        int cols = 5;

        // Генерируем лабиринт
        Maze maze = generator.generate(rows, cols);

        // Проверяем, что лабиринт не равен null
        assertNotNull(maze);

        // Проверяем размеры лабиринта
        assertEquals(rows, maze.getHeight());
        assertEquals(cols, maze.getWidth());

        // Проверяем, что все ячейки инициализированы
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                CellType cellType = maze.getCell(new Coordinate(x, y)).cellType();
                assertNotNull(cellType);
            }
        }
    }

    @Test
    void testMazeGenerationConsistency() {
        TunelBaseGenerator generator = new TunelBaseGenerator();
        int rows = 5;
        int cols = 5;

        // Генерируем лабиринт несколько раз и проверяем его размеры
        Maze maze1 = generator.generate(rows, cols);
        Maze maze2 = generator.generate(rows, cols);

        assertNotNull(maze1);
        assertNotNull(maze2);

        assertEquals(rows, maze1.getHeight());
        assertEquals(cols, maze1.getWidth());

        assertEquals(rows, maze2.getHeight());
        assertEquals(cols, maze2.getWidth());

        // Проверяем, что лабиринты могут быть различными (это может быть не всегда верно из-за случайности)
        assertNotEquals(maze1.hashCode(), maze2.hashCode());
    }
}
