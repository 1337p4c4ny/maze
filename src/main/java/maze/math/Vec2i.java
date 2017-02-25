package maze.math;

public class Vec2i {

    private long x;
    private long y;

    public Vec2i(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public double distance(Vec2i vec) {
        return Math.sqrt(MMath.sqr(this.x - vec.getX()) + MMath.sqr(this.y - vec.getY()));
    }
}
