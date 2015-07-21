public class Brute {
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // N-by-N percolation system
        
        Point points[] = new Point[N];
        
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);   // leave a border to write text
        
        //read in points
        boolean first = true;
        Point previousPoint = null;
        int ind =0;
        while (!in.isEmpty()) {
            
 
            
            int i = in.readInt();
            int j = in.readInt();
            Point p = new Point(i, j);
            
            points[ind++] = p;
            
            p.draw();
            
            
            if (previousPoint != null){
                
                p.drawTo(previousPoint);
            }
            previousPoint = new Point(i, j);
            StdOut.println("-> " + i + ", " + j);
            //StdDraw.setPenColor(StdDraw.RED);
            //draw(i,j);
            
            
            
            //perc.open(i, j);
            //draw(perc, N);
            //StdDraw.show(0);
        }
        
        Insertion.sort(points);
        for(int i = 0; i < points.length; i++) {
            StdOut.println( points[i] );
        }
        
        
        StdOut.println(" All done! ");
    }
    
    private void draw (Point[] points) {
        //
    }
}