package backend.academy.samples;

import backend.academy.maze.Maze;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import backend.academy.solvers.nocostsolvers.WaveSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import backend.academy.grid.MazeGrid;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaveSolverTest {

    private WaveSolver solver;
    private Maze maze;

    @BeforeEach
    public void setUp() {
        solver = new WaveSolver();
        MazeGrid grid = new MazeGrid(3, 3);
        maze = new Maze(3, 3, grid);
        // Настраиваем лабиринт
        maze.setCellType(new Coordinate(0, 0), CellType.PASSAGE);
        maze.setCellType(new Coordinate(0, 1), CellType.WALL);
        maze.setCellType(new Coordinate(0, 2), CellType.PASSAGE);
        maze.setCellType(new Coordinate(1, 0), CellType.PASSAGE);
        maze.setCellType(new Coordinate(1, 1), CellType.WALL);
        maze.setCellType(new Coordinate(1, 2), CellType.PASSAGE);
        maze.setCellType(new Coordinate(2, 0), CellType.PASSAGE);
        maze.setCellType(new Coordinate(2, 1), CellType.PASSAGE);
        maze.setCellType(new Coordinate(2, 2), CellType.PASSAGE);
    }

    @Test
    public void testSolvePathExists() {
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 2);

        List<Coordinate> path = solver.solve(maze, start, end);

        assertEquals(5, path.size()); // Ожидаем, что путь будет из 5 координат
        assertTrue(path.contains(start));
        assertTrue(path.contains(end));
    }

    @Test
    public void testSolveNoPath() {
        // Устанавливаем стену между стартом и финишем
        maze.setCellType(new Coordinate(2, 1), CellType.WALL);
        maze.setCellType(new Coordinate(2, 1), CellType.WALL);
        maze.setCellType(new Coordinate(2, 1), CellType.WALL);

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        List<Coordinate> path = solver.solve(maze, start, end);

        assertTrue(path.isEmpty()); // Ожидаем, что путь не найден
    }

    @Test
    public void testSolveStartEqualsEnd() {
        Coordinate startAndEnd = new Coordinate(1, 1);
        maze.setCellType(startAndEnd, CellType.PASSAGE);

        List<Coordinate> path = solver.solve(maze, startAndEnd, startAndEnd);

        assertEquals(1, path.size()); // Путь должен содержать только одну координату
        assertTrue(path.contains(startAndEnd));
    }
}

