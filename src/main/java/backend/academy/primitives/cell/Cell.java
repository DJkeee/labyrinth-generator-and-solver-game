package backend.academy.primitives.cell;

import backend.academy.primitives.celltype.CellType;
import backend.academy.primitives.coordinate.Coordinate;

public record Cell(Coordinate coord, CellType cellType) {
}
