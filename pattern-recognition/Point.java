/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class Point implements Comparable<Point> {
    
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertcal;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        
        if(this.compareTo(that) == 0 ){
            return Double.NEGATIVE_INFINITY;
        } else if( (that.x - this.x) == 0 ) {
            return Double.POSITIVE_INFINITY;
        } else if( (that.y - this.y) == 0 ) {
            return +0.0;
        }

        double slope = (that.y - this.y)*1.0/(that.x - this.x)*1.0;
        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        //Formally, the invoking point (x0, y0) is less than the 
        //argument point (x1, y1) if and only if 
        //either y0 < y1 or if y0 = y1 and x0 < x1
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else if (this.y > that.y || (this.y == that.y && this.x > that.x)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    
    /*
     * Formally, the point (x1, y1) is less than the point (x2, y2) if 
     * and only if the slope (y1 ? y0) / (x1 ? x0) is less than the 
     * slope (y2 ? y0) / (x2 ? x0). Treat horizontal, vertical, 
     * and degenerate line segments as in the slopeTo() method
     */
    public Comparator<Point> slopeOrder() {
   
        return new Comparator<Point>() { 
            public int compare(Point p1, Point p2) {
                double slope1 = slopeTo(p1);
                double slope2 = slopeTo(p2);
                if(slope1 < slope2){
                    return -1;
                } else if (slope1 > slope2){
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        // case for slopeTo when this and that points are the same point ...
        Point one = new Point(1,1);
        StdOut.println( one.slopeTo(new Point(2,2)) );
        StdOut.println( one.slopeTo(new Point(0,0)) );
        
        StdOut.println( one.compareTo(new Point(1,1)) );
        StdOut.println( one.compareTo(new Point(0,0)) );
        StdOut.println( one.compareTo(new Point(2,2)) );
        
        Point[] points = new Point[5];
        points[0] = new Point(2, 2);
        points[1] = new Point(1, 3);
        points[2] = new Point(2, 1);
        points[3] = new Point(0, 0);
        points[4] = new Point(1, 2);
        
        StdOut.println(Arrays.toString(points));
        Arrays.sort(points, points[3].slopeOrder() );
        StdOut.println(Arrays.toString(points));
        
        for(int i=1; i<points.length; i++){
            double slope = points[0].slopeTo(points[i]);
            StdOut.println(slope);
        }
        
        
    }  
}