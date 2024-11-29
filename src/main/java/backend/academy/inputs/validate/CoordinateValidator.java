package backend.academy.inputs.validate;

import backend.academy.primitives.coordinate.Coordinate;

public class CoordinateValidator implements Validator<Coordinate> {
    private final int maxRow;
    private final int maxCol;

    public CoordinateValidator(int maxRow, int maxCol) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
    }

    @Override
    public boolean isValid(Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    }
}
