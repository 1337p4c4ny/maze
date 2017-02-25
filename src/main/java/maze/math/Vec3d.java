package maze.math;

public class Vec3d {

    private double x;
    private double y;
    private double z;

    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double distance(Vec3d vec) {
        return Math.sqrt(
            MMath.sqr(this.x - vec.getX())
                +
            MMath.sqr(this.y - vec.getY())
                +
            MMath.sqr(this.z - vec.getZ())
        );
    }

    public double length() {
        return Math.sqrt(MMath.sqr(this.x) + MMath.sqr(this.y) + MMath.sqr(this.z));
    }

    public Vec3d normalize() {
        double length = Math.abs(length());
        return new Vec3d(this.x/length, this.y/length, this.z/length);
    }
}
