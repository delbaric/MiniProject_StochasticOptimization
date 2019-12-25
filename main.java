import java.util.Arrays;
import java.util.LinkedList;

public class main {

    /* Global */
    private static int numOfPoints = 100000;
    private static double numOfRounds = 1000;

    private static Point[] points = new Point[numOfPoints];
    private static LinkedList<Integer> adj[] = new LinkedList[numOfPoints];


    public static void main(String[] args) throws InterruptedException {


        double sumOfAllPaths = 0;

        for (int i = 1; i <= numOfRounds; i++) {
//            for (int p = 0; p < numOfPoints; p++){
//                adj[p] = new LinkedList<>();
//            }
            createInitialCone();
            for (int j = 1; j < numOfPoints - 1; j++) { // check dist from every point to every other point except for the first and last
//                System.out.println("The point we now compare is : " + j);
                setMaxDistToNeighbor(j);
            }
            sumOfAllPaths += points[numOfPoints-1].getDist();
            for (int k = 0; k < numOfPoints; k++) {
                points[k] = null;
//                adj[k] = null;
            }
//            System.out.println("[Round number: "+i+"]\n***** Calculation Finished !!! ***** \nNumber of points: "+numOfPoints+"\nCurrent average path length is: " + sumOfAllPaths/i+"\n\n");
        }
        System.out.println("***** Calculation Finished !!! ***** \nNumber of points: "+numOfPoints+"\nNumber of rounds: "+((int)numOfRounds)+"\nAverage path length is: " + sumOfAllPaths/numOfRounds);

    }

    static void createInitialCone () throws InterruptedException {

        Point p;
        points[0] = new Point(0,0,0);
        points[0].setDist(0);
        points[1] = new Point(0,0,2);
        int counter = 2;
        while (counter < numOfPoints) {
            p = Point.createRandomPoint();

            if (isInConeRange(points[0], p)){ // in Cone-range
                if (Math.random() > 0.5) { // coin flip
                    p.setZ(p.getZ() + 1);
                }
                points[counter] = p;
                counter++;
            }
        }
//        System.out.println("BEFORE SORTING");
        Arrays.sort(points); // according to leading axes
//        System.out.println("BEFORE SORTING");

//        Thread t1 = new Thread(()-> {
//           for (int i = 0; i < numOfPoints/2; i++){
//               for (int j = i+1; j < numOfPoints; j++){
//                   if (isInConeRange(points[i], points[j])){
//                        adj[i].add(j);
//                    }
//               }
//           }
//        });
//
//        Thread t2 = new Thread(()-> {
//            for (int i = numOfPoints/2; i < numOfPoints; i++){
//                for (int j = i+1; j < numOfPoints; j++) {
//                    if (isInConeRange(points[i], points[j])) {
//                        adj[i].add(j);
//                    }
//                }
//            };
//        });
//
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println("Threads finish");

    }
    static void printAllPoints() {
        for (Point pi : points) {
            System.out.println(pi);
        }
    }
    static void printAllDist () {
        for (int i = 0 ; i < numOfPoints ; i++) {
            System.out.println("p" + i + " :" + points[i].getDist());
        }
    }

    static boolean isInConeRange (Point source, Point dest) {
        return ((dest.getZ() >= source.getZ()) && (Math.pow((dest.getX() - source.getX()), 2) + Math.pow((dest.getY() - source.getY()), 2) <= Math.pow((dest.getZ() - source.getZ()), 2)));
    }

    static void setMaxDistToNeighbor (int sourceIndex){

        Point source = points[sourceIndex];
        Point dest;
//        for (int neighbor : adj[sourceIndex]){
////            System.out.println("we now set max for point: " + sourceIndex);
//            dest = points[neighbor];
//            if (dest.getDist() < (source.getDist() + 1)) {
//                dest.setDist(source.getDist()+1);
//            }
//        }

        for (int i = sourceIndex + 1; i < numOfPoints; i++)
        {
            dest = points[i];
            if (isInConeRange(source, dest)){
                if (dest.getDist() < (source.getDist() + 1)) {
                    dest.setDist(source.getDist()+1);
                }
            }
        }
    }
}
