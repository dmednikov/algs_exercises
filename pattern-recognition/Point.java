/*************************************************************************
  * Name:
  * Email:
  *
  * Compilation:  javac Point.java
  * Execution:
  * Dependencies: StdDraw.java
  *
  * Description: An immutable data type for points in the plane.
  *
  *************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
    
    private class SlopeOrder implements Comparator<Point> 
    {
       public int compare(Point p, Point q) 
       {
            
           return 0;
       }
    }
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }
    
    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double slope = (that.y - this.y)/(that.x - this.x)*1.0;
        //TODO handle edge cases for vertical and horizontals lines
        return slope;
    }
    
    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        //Formally, the invoking point (x0, y0) is less than the 
        //argument point (x1, y1) if and only if 
        //either y0 < y1 or if y0 = y1 and x0 < x1
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else {
            return 1;
        }
    }
    
    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}