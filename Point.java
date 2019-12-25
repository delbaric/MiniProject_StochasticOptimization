public class Point implements Comparable {

        private double x;
        private double y;
        private double z;
        private int dist;


    public Point (double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.dist = 1;
    }

    public static Point createRandomPoint () { // 2d
        double x = (Math.random() * 2) - 1; // -1 <= x <= 1
        double y = Math.random(); // 0 <= y <= 1
        return new Point(x, y, 0);
    }

    /*
    public static Point createRandomPoint () { // 3d
        double x = (Math.random() * 2) - 1; // -1 <= x <= 1
        double y = (Math.random() * 2) - 1; // -1 <= y <= 1
        double z = Math.random(); // 0 <= z <= 1
        return new Point(x, y, z);
    }
    */

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public Point getPoint() {
        return this;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getDist(){
        return this.dist;
    }

    public void setDist(int dist){
        this.dist = dist;
    }

    public String toString (){
        return "("+ Double.toString(this.x) + ", " + Double.toString(this.y) + ", "  + Double.toString(this.z) + ")";
    }

    public int compareTo(Object other) { // 2d
        if (this.getY() > ((Point)other).getY()) return 1;
        else return -1;
    }

    /*
    public int compareTo(Object other) { // 3d
        if (this.getZ() > ((Point)other).getZ()) return 1;
        else return -1;
    }
     */
}
