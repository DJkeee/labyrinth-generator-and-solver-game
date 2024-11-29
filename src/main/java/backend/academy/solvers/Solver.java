package backend.academy.solvers;

import backend.academy.maze.Maze;
import backend.academy.primitives.coordinate.Coordinate;
import java.util.List;

/*
 * Интерфейс Solver определяет метод для решения лабиринта.
 *
 * Реализации данного интерфейса должны предоставлять алгоритм,
 * который находит путь из стартовой точки в конечную точку
 * лабиринта.
 */
public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
