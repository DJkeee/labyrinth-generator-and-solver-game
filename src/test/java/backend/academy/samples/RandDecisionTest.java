package backend.academy.samples;

import backend.academy.generators.balancedgenerator.RandDecisionGenerator;
import backend.academy.maze.Maze;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class RandDecisionGeneratorTest {

    @Test
    void testGenerateMaze() {
        RandDecisionGenerator generator = new RandDecisionGenerator();
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
    void testRandomCellSelection() {
        RandDecisionGenerator generator = new RandDecisionGenerator();
        Set<Coordinate> cells = new HashSet<>();
        cells.add(new Coordinate(0, 0));
        cells.add(new Coordinate(1, 1));
        cells.add(new Coordinate(2, 2));

        Coordinate randomCell = generator.getRandomCell(cells);

        assertTrue(cells.contains(randomCell));
    }

    @Test
    void testMazeGenerationConsistency() {
        RandDecisionGenerator generator = new RandDecisionGenerator();
        int rows = 5;
        int cols = 5;

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
