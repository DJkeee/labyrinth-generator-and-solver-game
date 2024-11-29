package backend.academy.samples;

import backend.academy.grid.MazeGrid;
import backend.academy.maze.Maze;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeTest {

    @Test
    void testInitialization() {
        int height = 5;
        int width = 5;
        MazeGrid mazeGrid = new MazeGrid(height, width);
        Maze maze = new Maze(height, width, mazeGrid);

        assertEquals(height, maze.getHeight());
        assertEquals(width, maze.getWidth());
    }

    @Test
    void testGetCellCost() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);
        Coordinate coord = new Coordinate(2, 2);
        mazeGrid.setCell(coord, CellType.PASSAGE); // Предполагаем, что PASSAGE имеет стоимость > 0

        Maze maze = new Maze(5, 5, mazeGrid);

        assertEquals(maze.getCellCost(coord), CellType.PASSAGE.getCost());
    }

    @Test
    void testGetType() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);
        Coordinate coord = new Coordinate(3, 3);
        mazeGrid.setCell(coord, CellType.WALL);

        Maze maze = new Maze(5, 5, mazeGrid);

        assertEquals(CellType.WALL, maze.getType(coord));
    }

    @Test
    void testSetCellType() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);
        Coordinate coord = new Coordinate(4, 4);
        Maze maze = new Maze(5, 5, mazeGrid);

        maze.setCellType(coord, CellType.PASSAGE);

        assertEquals(CellType.PASSAGE, maze.getType(coord));
    }

    @Test
    void testGetNeighbors() {
        MazeGrid mazeGrid = new MazeGrid(5, 5);
        Coordinate coord = new Coordinate(2, 2);

        // Устанавливаем некоторые ячейки как проходы
        mazeGrid.setCell(new Coordinate(2, 1), CellType.PASSAGE);
        mazeGrid.setCell(new Coordinate(1, 2), CellType.PASSAGE);

        Maze maze = new Maze(5, 5, mazeGrid);

        // Получаем соседей
        List<Coordinate> neighbors = maze.getNeighbors(coord);

        // Проверяем, что соседи возвращаются правильно
        assertTrue(neighbors.contains(new Coordinate(2, 1)));
        assertTrue(neighbors.contains(new Coordinate(1, 2)));

        // Проверяем, что стены не добавляются в соседей
        assertFalse(neighbors.contains(new Coordinate(3, 3))); // Пример несуществующей стены
    }
}

