import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    
    private int numberOfSegments;
    private LineSegment[] segments;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        
        //this.segments = new LineSegment[10000];
        ArrayList<Point[]> pointees = new ArrayList<Point[]>();
        
        int N = points.length;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    for (int m = k+1; m < N; m++) {
                        
                        double slope0 = points[i].slopeTo(points[j]);
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[k])== points[i].slopeTo(points[m])) {
                            StdOut.println(points[i] + " " + points[j] + " " + points[k] + " " + points[m]);
                            Point[] temp = new Point[4];
                            temp[0] = points[i];
                            temp[1] = points[j];
                            temp[2] = points[k];
                            temp[3] = points[m];
                            Arrays.sort(temp);
                            
                            // before adding a new segment I gues I need to see if the new segment extends any existing segments or fits into them
                            // loop thru all segments and
                            // get slope and points
            
                            boolean added = false;
                            for(int ind=0; ind<pointees.size();ind++ ){
                                
                                Point[] eachOne = pointees.get(ind);
                                double slope1 = eachOne[0].slopeTo(eachOne[1]);
                                if( slope0 == slope1 ){
                                    if(eachOne[0].compareTo(temp[0]) == -1) {
                                        eachOne[0] = temp[0];
                                    }
                                       
                                    if(eachOne[3].compareTo(temp[3]) == -1) {
                                        eachOne[3] = temp[3];
                                    }
                                    pointees.set(ind, eachOne);
                                    added = true;
                                    break;
                                }
                            }
                            
                            if(!added){
                                pointees.add(temp);
                            }

                            
                            //for(Point[] eachy : 
                            //this.segments[numberOfSegments()] = new LineSegment(temp[0], temp[3]);
                            //pointees.add(temp);
                            
                            //numberOfSegments++;
                        }
                    }
                }
            }
        }
        
        StdOut.println(pointees.size());
        this.segments = new LineSegment[pointees.size()];
        for(Point[] eachOne : pointees){
            this.segments[numberOfSegments()] = new LineSegment(eachOne[0], eachOne[3]);
            numberOfSegments++;
        }
        //this.segments[numberOfSegments()] = new LineSegment(temp[0], temp[3]);
        //numberOfSegments++;
        
        /*
        for(int i = 0; i< points.length; i++){
            //
            for(int j = 0; j< points.length; j++){
                if(j==i){
                    continue;
                }
                
                Point[] candidatePoints = getCandidatePoints(points, j, i);
                // check if they are points are collinear
                
                double slope1 =  points[i].slopeTo(candidatePoints[0]);
                double slope2 =  points[i].slopeTo(candidatePoints[1]);
                double slope3 =  points[i].slopeTo(candidatePoints[2]);
                StdOut.println(points[i]);
                    StdOut.println(slope1 + " :: " + slope2 + " :: " + slope3);
                if( slope1 == slope2 && slope2 == slope3 ){
                    
                    this.segments[numberOfSegments()] = new LineSegment(points[i], candidatePoints[2]);
                    numberOfSegments++;                    
                }
            }
        }
        */
        StdOut.println("done");
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