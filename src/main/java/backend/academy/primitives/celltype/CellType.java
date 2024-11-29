package backend.academy.primitives.celltype;

public enum CellType {
    WALL(1000),
    PASSAGE(4),
    TRAP(10),
    COIN(1),
    TREE(6);

    private final int cost;

    CellType(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

}

