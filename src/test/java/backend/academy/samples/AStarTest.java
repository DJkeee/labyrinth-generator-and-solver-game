package backend.academy.samples;

import backend.academy.grid.MazeGrid;
import backend.academy.maze.Maze;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import backend.academy.solvers.costsolvers.AStarSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class AStarTest {

    @Test
    void testSolveMazeWithNoPath() {
        Maze maze = createMazeWithNoPath();
        AStarSolver solver = new AStarSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 2);

        List<Coordinate> actualPath = solver.solve(maze, start, end);

        assertTrue(actualPath.isEmpty());
    }

    private Maze createMazeWithNoPath() {
        MazeGrid grid = new MazeGrid(3, 3);

        // Устанавливаем проходимые клетки
        grid.setCell(new Coordinate(0, 0), CellType.PASSAGE); // Passage
        grid.setCell(new Coordinate(0, 2), CellType.PASSAGE); // Passage
        grid.setCell(new Coordinate(1, 2), CellType.PASSAGE); // Passage
        grid.setCell(new Coordinate(2, 0), CellType.PASSAGE); // Passage
        grid.setCell(new Coordinate(2, 2), CellType.PASSAGE); // Passage

        return new Maze(3, 3, grid);
    }

}
