public class Percolation {
    
    private SITESTATE[][] grid;
    private WeightedQuickUnionUF wuf;
    private int size;
    private enum SITESTATE { OPEN, BLOCKED, FULL};
        
    public Percolation(int N)               // create N-by-N grid, with all sites blocked
    {
        size = N;
        grid = new SITESTATE[N][N];
        wuf = new WeightedQuickUnionUF(N*N);
        for (int i=0; i < N; i++){
            for(int j=0; j < N; j++) {
                grid[i][j] = SITESTATE.BLOCKED;
            }
        }
    }
    
    public void open(int i, int j)          // open site (row i, column j) if it is not open already
    {
        if( !isOpen(i, j) ) {
            int p = i-1;
            int q = j-1;
            grid[p][q] = SITESTATE.OPEN;
            
            // NOW THAT SITE IS OPEN CHECK TO SEE IF CAN BE CONNECTED TO ITS 4 NEIGBOURS
            int[] neighbors = getNeighbours(p,q);

            for(int k = 0; k < neighbors.length; k++ ){
                
                int[] coords = getCoords(neighbors[k]);
                
                if(neighbors[k] >=0 && neighbors[k] < size*size ){
                    //StdOut.println("Anyway 2: " +neighbors[k]);
                    //StdOut.println("Looping: " + coords[0] + " " + coords[1]);
                    if ( isOpen(coords[0]+1, coords[1]+1) ) {
                        int index1 = getIndex(p,q);
                        wuf.union(index1, neighbors[k]);
                    }
                }
            }
            
        }
    }
    private int[] getNeighbours(int i, int j){
        int all = 4;
        
        int index = getIndex(i, j);

        int [] retVal = new int[4];
        
        if ( index % size == 0 ) {
            retVal[0] =  - 1;
        } else {
            retVal[0] = index - 1;
        }
        
        if ( (index % size) + 1 == size ) {
            retVal[1] =  - 1;
        } else {
            retVal[1] = index + 1;
        }
        
        retVal[2] = index + size;
        retVal[3]  = index - size;    
        
//        for (int x = Math.max(i - 1, 0); x < Math.min(i + 1, size); x++){
//            for( int y = Math.max (j - 1, 0); y < Math.min(j + 1, size); y++){
//                if ( x != i && y != j ){
//                    StdOut.println("COORDS: " + x + " " + y);
//                }
//            }
//        }
        
        return retVal;
        
    }
    
    private int[] getCoords(int index) {
        int [] retVal = new int[2];
        retVal[0] = index % size;
        retVal[1] = index / size;
        return retVal;
    }
    
    private int getIndex(int i, int j) {
        return i + j * size;
    }
    public boolean isOpen(int i, int j)     // is site (row i, column j) open?
    {
        int p = i-1;
        int q = j-1;
        if( grid[p][q] == SITESTATE.OPEN ) {
             return true;
        }
        return false;
    }
    public boolean isFull(int i, int j)     // is site (row i, column j) full?
    {
        int p = i-1;
        int q = j-1;
//        if( grid[i][j] == SITESTATE.FULL ) {
//             return true;
//        }
        int index = getIndex(p,q);
        
        for (int k=0; k< size; k++){
            int topRow = getIndex(0, k);
            if( isOpen(i,j) && wuf.connected(index, topRow) ) {
                return true;
            }
        }
        return false;
    }
    
    public boolean percolates()             // does the system percolate?
    {
        return true;
    }
    
    public static void main(String[] args)   // test client (optional)
    {
        //
    }
}