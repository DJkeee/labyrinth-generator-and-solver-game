package backend.academy.renders.realrender;

import backend.academy.maze.Maze;
import backend.academy.primitives.cell.Cell;
import backend.academy.primitives.coordinate.Coordinate;
import backend.academy.renders.RenderInterface;
import java.util.List;

/**
 * Класс RealRender реализует интерфейс Render и отвечает за отрисовку лабиринта.
 * Он предоставляет методы для визуализации лабиринта с учетом различных элементов,
 * таких как стены, монеты, ловушки и деревья.
 * Также поддерживается отображение пути, а также начальной и конечной точек.
 */

public class RealRender implements RenderInterface {

    @Override
    public String render(Maze maze) {
        return renderMaze(maze, null, null, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path, Coordinate start, Coordinate end) {
        return renderMaze(maze, path, start, end);
    }

    private String renderMaze(
        Maze maze,
        List<Coordinate> path,
        Coordinate start,
        Coordinate end
    ) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                Coordinate currentCoord = new Coordinate(row, col);
                sb.append(renderCell(maze.getCell(currentCoord), currentCoord, path, start, end));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private String renderCell(
        Cell cell,
        Coordinate currentCoord,
        List<Coordinate> path,
        Coordinate start,
        Coordinate end
    ) {
        if (currentCoord.equals(start) || currentCoord.equals(end)) {
            return "\uD83D\uDEB4";
        } else if (path != null && path.contains(currentCoord)) {
            return "\uD83D\uDFEB";
        } else {
            return renderCellType(cell);
        }
    }

    private String renderCellType(Cell cell) {
        return switch (cell.cellType()) {
            case WALL -> "⬛️";
            case COIN -> "\uD83D\uDFE8";
            case TRAP -> "⚠️️";
            case TREE -> "\uD83D\uDFE2";
            default -> "⬜️";
        };
    }
}
