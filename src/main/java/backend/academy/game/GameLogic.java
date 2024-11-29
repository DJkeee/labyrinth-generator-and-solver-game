package backend.academy.game;

import backend.academy.generators.Generator;
import backend.academy.generators.balancedgenerator.RandDecisionGenerator;
import backend.academy.generators.tunelbasedgenerator.TunelBaseGenerator;
import backend.academy.inputs.Input;
import backend.academy.maze.Maze;
import backend.academy.primitives.coordinate.Coordinate;
import backend.academy.renders.RenderInterface;
import backend.academy.renders.realrender.RealRender;
import backend.academy.solvers.Solver;
import backend.academy.solvers.costsolvers.AStarSolver;
import backend.academy.solvers.nocostsolvers.WaveSolver;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("magicnumber")
public class GameLogic {
    private final Maze maze;
    private final Solver solver;
    private final RenderInterface renderInterface;
    private static final PrintStream PRINT = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    public GameLogic(Maze maze, Solver solver) {
        this.maze = maze;
        this.solver = solver;
        this.renderInterface = new RealRender();
    }

    public static int gridSizeChoose(int choice) {
        return switch (choice) {
            case 1 -> 15;
            case 3 -> 25;
            default -> 20;
        };
    }

    public static Maze mazeBuilding(int row, int col, Generator generator) {
        return generator.generate(row, col);
    }

    public static Generator mazeGeneratorChoose(int choice) {
        return switch (choice) {
            case 2 -> new TunelBaseGenerator();
            default -> new RandDecisionGenerator();
        };
    }

    public static Solver mazeSolverChoose(int choice) {
        return switch (choice) {
            case 2 -> new AStarSolver();
            default -> new WaveSolver();
        };
    }

    public void renderMazeWithoutPass() {
        GameUI.sayAboutCoordinatesInterval(maze.getHeight(), maze.getWidth());
        String mazeRender = renderInterface.render(maze);
        GameUI.sayAboutMazeSymbols();
        PRINT.println(mazeRender);
    }

    public void renderMazeWithPath(Coordinate start, Coordinate end) {
        String mazeRender = renderInterface.render(maze, solver.solve(maze, start, end), start, end);
        GameUI.sayAboutMazeSymbols();
        PRINT.println(mazeRender);
    }

    @SuppressWarnings("magicnumbers")
    public void renderMazeWithPath() {
        GameUI.askAboutStart();
        GameUI.sayAboutCoordinatesInterval(1, maze.getHeight());
        Coordinate start = Input.inputCoordinate(maze.getWidth(), maze.getHeight());

        GameUI.askAboutEnd();
        GameUI.sayAboutCoordinatesInterval(1, maze.getHeight());
        Coordinate end = Input.inputCoordinate(maze.getWidth(), maze.getHeight());

        renderMazeWithPath(start, end);
    }

    public static GameLogic gameLogicCreator() {
        GameUI.sayGameIntro();
        GameUI.askAboutMazeSize();
        int sizeOfLabyrint = gridSizeChoose(Input.inputFromInterval(1, 3));

        GameUI.askAboutGenerationAlgorithm();
        Generator generator = mazeGeneratorChoose(Input.inputFromInterval(1, 2));
        Maze maze = mazeBuilding(sizeOfLabyrint, sizeOfLabyrint, generator);

        GameUI.askAboutSolveAlgorithm();
        Solver solver = mazeSolverChoose(Input.inputFromInterval(1, 2));
        return new GameLogic(maze, solver);
    }
}



