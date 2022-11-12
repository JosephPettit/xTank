package SharedResources;

public class Missile extends TankData {

    private double x, y;
    private int r;

    public Missile(TankData parent) {
        super(parent.getTankColor(), parent.getPlayerNumber());
        this.x = parent.getX();
        this.y = parent.getY();
        this.r = parent.getmR();
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

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
