import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    
    private int numberOfSegments;
    private LineSegment[] segments;
    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        
        int N = points.length;
        
        for (int i = 0; i < N; i++) {
            
            for(int j = 0; j < N; j++){
                double[] slopes = new double[N-1];
                // get each point
                // calculate slopes for all other points?
                if( i != j ) {
                    slopes[j] = points[i].slopeTo(points[j]) ;
                }
                
                // sort SLOPES
                
                // see if there sequences with the same slope,
                // if there are any then 
                // isolate them into LineSegment(s)
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        
        StdOut.println("Time :" + sw.elapsedTime());
    }
}