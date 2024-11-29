package backend.academy.grid;

import backend.academy.primitives.cell.Cell;
import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;
import java.util.ArrayList;
import java.util.List;

/* Класс mazeGrid представляет собой двумерную сетку, состоящую из ячеек (Cell).
 * Каждая ячейка может иметь различные типы (CellType), которые определяют их свойства,
 * такие как стоимость перемещения через них.
 * Реализует основную внутреннюю логику работы лабиринта, что упрощает логику работы с лабиринтом
 */

public class MazeGrid {
    private final Cell[][] grid;

    public MazeGrid(int rows, int cols) {
        this.grid = new Cell[rows][cols];
        initGridwithWalls();
    }

    public int getRows() {
        return grid.length;
    }

    public int getCols() {
        return grid[0].length;
    }

    public void setCell(Coordinate coord, CellType cell) {
        if (!isCorrectCoordinate(coord)) {
            return;
        }
        grid[coord.row()][coord.col()] = new Cell(coord, cell);
    }

    public int getCellCost(Coordinate coordinate) {
        if (!isCorrectCoordinate(coordinate)) {
            throw new IllegalArgumentException("Некорректные координаты");
        }
        return grid[coordinate.row()][coordinate.col()].cellType().getCost();
    }

    public boolean isCorrectCoordinate(Coordinate coord) {
        return coord.row() >= 0 && coord.row() < getRows()
            && coord.col() >= 0 && coord.col() < getCols();
    }

    public Cell getCell(Coordinate coord) {
        if (!isCorrectCoordinate(new Coordinate(coord.row(), coord.col()))) {
            throw new IllegalArgumentException("Неправильные координаты");
        }
        return grid[coord.row()][coord.col()];
    }

    public CellType getCellType(Coordinate coord) {
        if (!isCorrectCoordinate(new Coordinate(coord.row(), coord.col()))) {
            throw new IllegalArgumentException("неверные координаты");
        }
        return grid[coord.row()][coord.col()].cellType();
    }

    private void initGridwithWalls() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                Coordinate coord = new Coordinate(row, col);
                setCell(coord, CellType.WALL);
            }
        }
    }

    public List<Coordinate> getNeighbors(Coordinate coord) {
        List<Coordinate> neighbors = new ArrayList<>();
        int x = coord.row();
        int y = coord.col();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (Math.abs(dx) + Math.abs(dy) == 1) {
                    int newX = x + dx;
                    int newY = y + dy;
                    Coordinate neighbor = new Coordinate(newX, newY);

                    if (isCorrectCoordinate(neighbor) && getCellType(neighbor) != CellType.WALL) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }

}
