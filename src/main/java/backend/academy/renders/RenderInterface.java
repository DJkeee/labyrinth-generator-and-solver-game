package backend.academy.renders;

import backend.academy.maze.Maze;
import backend.academy.primitives.coordinate.Coordinate;
import java.util.List;

/*
 * Интерфейс RenderInterface определяет методы для отрисовки лабиринта.
 * реализации данного интерфейса должны реализовать два метода:
 * - render с одним аргументов отрисовывает лабиринт без выделения найденного пути.
 * - render с 4 аргументами отрисовывает лабиринт с найденным решением, если оно найдено.
 */
public interface RenderInterface {

    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path, Coordinate start, Coordinate end);
}
