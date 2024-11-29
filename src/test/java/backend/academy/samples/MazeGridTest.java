package backend.academy.samples;

import backend.academy.grid.MazeGrid;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MazeGridTest {

    @Test
    void testInitialization() {
        int rows = 5;
        int cols = 5;
        MazeGrid mazeGrid = new MazeGrid(rows, cols);

        assertEquals(rows, mazeGrid.getRows());
        assertEquals(cols, mazeGrid.getCols());

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Coordinate coord = new Coordinate(row, col);
                assertEquals(CellType.WALL, mazeGrid.getCellType(coord));
            }
        }
    }

    @Test
    void testSetCell() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);
        Coordinate coord = new Coordinate(2, 2);

        mazeGrid.setCell(coord, CellType.PASSAGE);

        assertEquals(CellType.PASSAGE, mazeGrid.getCellType(coord));
    }

    @Test
    void testGetCellCost() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);
        Coordinate coord = new Coordinate(1, 1);

        mazeGrid.setCell(coord, CellType.PASSAGE);


        assertTrue(mazeGrid.getCellCost(coord) == CellType.PASSAGE.getCost());
    }

    @Test
    void testInvalidCoordinate() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);
        Coordinate invalidCoord = new Coordinate(-1, -1);

        // Проверяем исключение при неправильных координатах
        assertThrows(IllegalArgumentException.class, () -> {
            mazeGrid.getCell(invalidCoord);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            mazeGrid.getCellCost(invalidCoord);
        });
    }

    @Test
    void testGetNeighbors() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);
        Coordinate coord = new Coordinate(2, 2);

        // Устанавливаем некоторые ячейки как проходы
        mazeGrid.setCell(new Coordinate(2, 1), CellType.PASSAGE);
        mazeGrid.setCell(new Coordinate(1, 2), CellType.PASSAGE);

        // Получаем соседей
        List<Coordinate> neighbors = mazeGrid.getNeighbors(coord);

        // Проверяем, что соседи возвращаются правильно
        assertTrue(neighbors.contains(new Coordinate(2, 1)));
        assertTrue(neighbors.contains(new Coordinate(1, 2)));

        // Проверяем, что стены не добавляются в соседей
        assertFalse(neighbors.contains(new Coordinate(3, 3))); // Пример несуществующей стены
    }

    @Test
    void testCorrectCoordinateCheck() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);

        // Проверяем корректные координаты
        assertTrue(mazeGrid.isCorrectCoordinate(new Coordinate(0, 0)));
        assertTrue(mazeGrid.isCorrectCoordinate(new Coordinate(4, 4)));

        // Проверяем некорректные координаты
        assertFalse(mazeGrid.isCorrectCoordinate(new Coordinate(-1, -1)));
        assertFalse(mazeGrid.isCorrectCoordinate(new Coordinate(5, 5)));
    }
}
