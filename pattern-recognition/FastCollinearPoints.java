import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    
    private int numberOfSegments;
    private LineSegment[] segments;
    ArrayList<Point> pointees = new ArrayList<Point>();
    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        
        this.segments = new LineSegment[1000];
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
                //val++;
                //StdOut.println( val );
                // calculate slopes for all other points?
                double slope = points[0].slopeTo(points[j]);
                StdOut.println(slope);
                if( slopeKeepee == slope ){ //
                    if(pointees.size() ==0){
                        pointees.add(points[j-1]);
                    } 
                    pointees.add(points[j]);
                } else {
                    if(pointees.size() >= 4 ){
                        //put pointees into LineSegment
                        this.segments[numberOfSegments()] = new LineSegment(pointees.get(0), pointees.get(pointees.size()-1));
                        numberOfSegments++;
                        
                    }
                    pointees.clear();
                    slopeKeepee = slope;
                }
                
                
            }
            
        }
        
        StdOut.println("done");
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