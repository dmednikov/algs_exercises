import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    
    private int numberOfSegments;
    private LineSegment[] segments;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        
        this.segments = new LineSegment[100];
        for(int i = 0; i< points.length; i++){
            //
            
            
            for(int j = 0; j< points.length; j++){
                if(j==i){
                    continue;
                }
                //get candidate points
                
                Point[] candidatePoints = getCandidatePoints(points, j, i);
                //pointCounter++;
                //if(pointCounter == 3){
                    //calculate and add segments 
                    this.segments[numberOfSegments()] = new LineSegment(points[i], candidatePoints[2]);
                    numberOfSegments++;
                    
                    //candidatePoints = new Point[3];
                    //pointCounter = 0;
                //}
            }
        }
    }
    
    private Point[] getCandidatePoints(Point[] points, int index, int excludePointIndex) {
        Point[] candidatePoints = new Point[3];
        int pointCounter = 0;
        
        candidatePoints[pointCounter] = points[index];
        pointCounter++;
        
        int nextPointIndex = getNextPointIndex(points.length, index, excludePointIndex);
        candidatePoints[pointCounter] = points[nextPointIndex];
        pointCounter++;
        
        nextPointIndex = getNextPointIndex(points.length, nextPointIndex, excludePointIndex);
        candidatePoints[pointCounter] = points[nextPointIndex];
        pointCounter++;
        
        /*for(int i = 1; i<3; i++){
            index++;
            
            if (index == excludePointIndex ) {
                index++;
            }
            
            if( index >= points.length) {
                index = index - points.length;
                if (index == excludePointIndex ) {
                    index++;
                }
            }     
            
            candidatePoints[pointCounter] = points[index];
            pointCounter++;
        }*/
        
        StdOut.println(Arrays.toString(candidatePoints));
        return candidatePoints;
    }
    
    private int getNextPointIndex(int pointsLength, int index, int excludePoint){
        
        index++;
        
        if(index == excludePoint ) 
            index++;
        if(index == pointsLength){
            index=index - pointsLength;
            if(index == excludePoint ) 
                index++;
        }
        return index;
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
            if(segment != null){
                StdOut.println(segment);
                segment.draw();
            }
        }
    }
}