import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    
    private int numberOfSegments;
    private LineSegment[] segments;
    ArrayList<Point[]> pointees = new ArrayList<Point[]>();
    ArrayList<Point> tempPointees = new ArrayList<Point>();
    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        
        //this.segments = new LineSegment[1000];
        int N = points.length;
        double[] slopes = new double[N-1];
        double slopeKeepee = 0.01;
        
        Point[] original = points.clone();
        int val = 0;
        for (int i = 0; i < N; i++) {
            //sort array according to a slopeOrder in the point
            points = original.clone();
            Arrays.sort(points, points[i].slopeOrder());
            // now get the sequences out of it
            for(int j = 1; j < N; j++){
                // get each point
                // val++;
                // StdOut.println( val );
                // calculate slopes for all other points?
                double slope = points[0].slopeTo(points[j]);
                //StdOut.println(slope);
                
                if( slopeKeepee == slope ){ //
                    if(tempPointees.size() == 0){
                        tempPointees.add(points[j-1]);
                    } 
                    tempPointees.add(points[j]);
                } else {
                    if(tempPointees.size() >= 3  ){
                        updatePointees(tempPointees);                        
                    }
                    tempPointees.clear();
                    slopeKeepee = slope;
                }
                // if this the last element in the array
                if( tempPointees.size() >= 3  && j == N - 1) {
                    updatePointees(tempPointees);
                    pointees.clear();
                }
                
                
            }
            //StdOut.println("-----------------" + numberOfSegments);
            //StdOut.println("----------------------------------------------------");
            
        }
        
        this.segments = new LineSegment[pointees.size()];
        for(Point[] eachOne : pointees){
            this.segments[numberOfSegments()] = new LineSegment(eachOne[0], eachOne[1]);
            numberOfSegments++;
        }
        StdOut.println("done");
    }
    
    private void updatePointees(ArrayList<Point> tempPointees) {
        //this.segments[numberOfSegments()] = new LineSegment(pointees.get(0), pointees.get(pointees.size()-1));
        //numberOfSegments++;
        ///////////////////
        
        Point tempFirst = tempPointees.get(0);
        Point tempFirstFirst = tempPointees.get(1);
        Point tempLast = tempPointees.get(tempPointees.size()-1);
        
        boolean added = false;
        for(int ind=0; ind<pointees.size();ind++ ){
            
            Point[] eachOne = pointees.get(ind);
            double slope1 = eachOne[0].slopeTo(eachOne[1]);
            double slope2 = eachOne[0].slopeTo(tempFirstFirst);
            if( slope1 == slope2 ){
                if(eachOne[0].compareTo(tempFirst) == 1) {
                    eachOne[0] = tempFirst;
                }
                
                if(eachOne[1].compareTo(tempLast) == -1) {
                    eachOne[1] = tempLast;
                }
                pointees.set(ind, eachOne);
                added = true;
                break;
            }
        }
        Point[] temp = new Point[2];
        temp[0] = tempFirst;
        temp[1] = tempLast;
        if(!added){
            pointees.add(temp);
        }
                            
    }
    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }
    
    // the line segments
    public LineSegment[] segments() {
        return this.segments;
    }
    
    public static void main(String[] args) {
        
        Stopwatch sw = new Stopwatch();
        
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            if(segment != null){
                StdOut.println(segment);
                segment.draw();
            }
        }
        
        StdOut.println("Time :" + sw.elapsedTime());
    }
}