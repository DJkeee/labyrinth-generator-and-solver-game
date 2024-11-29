package backend.academy.maze;

import backend.academy.grid.MazeGrid;
import backend.academy.primitives.cell.Cell;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import java.util.List;

/**
 * Класс Maze представляет собой удобную структуру данных
 * для хранения информации о лабиринте.
 * Основные характеристики:
 * - Высота (height) и ширина (width) лабиринта.
 * - Сетка (Grid), содержащая информацию о ячейках лабиринта и реализующая логику лабиринта.
 */

public final class Maze {
    private final int height;
    private final int width;
    private final MazeGrid mazeGrid;

    public Maze(int height, int width, MazeGrid mazeGrid) {
        this.height = height;
        this.width = width;
        this.mazeGrid = mazeGrid;
    }

    public int getCellCost(Coordinate coordinate) {
        return mazeGrid.getCellCost(coordinate);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCell(Coordinate coord) {
        return mazeGrid.getCell(coord);
    }

    public CellType getType(Coordinate coord) {
        return mazeGrid.getCellType(coord);
    }

    public void setCellType(Coordinate coord, CellType type) {
        mazeGrid.setCell(coord, type);
    }

    public List<Coordinate> getNeighbors(Coordinate coord) {
        return mazeGrid.getNeighbors(coord);
    }
}

