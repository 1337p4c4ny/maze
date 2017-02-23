package maze;

public enum CellType {
    EMPTY((byte)0),
    WALL((byte)1),
    START((byte)2),
    FINISH((byte)3);

    byte a;

    CellType(byte a) {
        this.a = a;
    }
    @Override
    public String toString() {
        switch (a) {
            case 0: return ".";
            case 1: return "#";
            case 2: return "S";
            case 3: return "F";
            default: return "%";
        }
    }
}
