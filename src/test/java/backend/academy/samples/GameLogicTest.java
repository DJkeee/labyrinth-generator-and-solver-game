package backend.academy.samples;

import backend.academy.game.GameLogic;
import backend.academy.generators.Generator;
import backend.academy.generators.balancedgenerator.RandDecisionGenerator;
import backend.academy.generators.tunelbasedgenerator.TunelBaseGenerator;
import backend.academy.maze.Maze;
import backend.academy.solvers.costsolvers.AStarSolver;
import backend.academy.solvers.nocostsolvers.WaveSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void testGridSizeChoose() {
        assertEquals(15, GameLogic.gridSizeChoose(1));
        assertEquals(20, GameLogic.gridSizeChoose(2));
        assertEquals(25, GameLogic.gridSizeChoose(3));
        assertEquals(20, GameLogic.gridSizeChoose(4)); // Тест на значение по умолчанию
    }

    @Test
    void testMazeGeneratorChoose() {
        assertTrue(GameLogic.mazeGeneratorChoose(1) instanceof RandDecisionGenerator);
        assertTrue(GameLogic.mazeGeneratorChoose(2) instanceof TunelBaseGenerator);
        assertTrue(GameLogic.mazeGeneratorChoose(3) instanceof RandDecisionGenerator); // Тест на значение по умолчанию
    }

    @Test
    void testMazeSolverChoose() {
        assertTrue(GameLogic.mazeSolverChoose(1) instanceof WaveSolver);
        assertTrue(GameLogic.mazeSolverChoose(2) instanceof AStarSolver);
        assertTrue(GameLogic.mazeSolverChoose(3) instanceof WaveSolver); // Тест на значение по умолчанию
    }

    @Test
    void testMazeBuilding() {
        Generator generator = new RandDecisionGenerator();
        Maze maze = GameLogic.mazeBuilding(5, 5, generator);
        assertNotNull(maze); // Проверяем, что лабиринт не равен null
        assertEquals(5, maze.getWidth());
        assertEquals(5, maze.getHeight());
    }
}

