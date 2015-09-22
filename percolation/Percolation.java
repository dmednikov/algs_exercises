
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private SITESTATE[][] grid;
    private WeightedQuickUnionUF wuf;
    private int size;
    private int size2D;
    private enum SITESTATE { OPEN, BLOCKED, FULL };
    private int virtualTopIndex;
    private int virtualBottomIndex;
        
    public Percolation(int N)   // create N-by-N grid, with all sites blocked
    {
        if (N < 1) {
            throw new java.lang.IllegalArgumentException();
        }
        size = N;
        //virtualTop = SITESTATE.FULL;
        virtualTopIndex = N*N;
        virtualBottomIndex = N*N + 1;
        size2D = size * size;
        grid = new SITESTATE[N][N];
        wuf = new WeightedQuickUnionUF(N*N+2);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = SITESTATE.BLOCKED;
            }
        }
    }
    
    // open site (row i, column j) if it is not open already
    public void open(int i, int j) 
    {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(i, j)) {
            int p = i-1;
            int q = j-1;
            grid[p][q] = SITESTATE.OPEN;
            
            // now that the site is open check to see if it can be 
            // connected to its 4 neighbors
            int index1DArray = convertTo1DArrayIndex(p, q);
            int[] neighbor1DIndices = getNeighbour1DIndices(index1DArray); 
            int nL = neighbor1DIndices.length;

            for (int k = 0; k < nL; k++) {
                
                if (neighbor1DIndices[k] >= 0 
                        && (neighbor1DIndices[k] < size2D)) {
                    
                    int[] index2DArray = convertTo2DArrayIndex(neighbor1DIndices[k]);
                    
                    //isOpen deals with 1 based array, therefore ( + 1)'s
                    if (isOpen(index2DArray[0]+1, index2DArray[1]+1)) {
                        wuf.union(index1DArray, neighbor1DIndices[k]);
                    }
                }
            }
            
            //System.out.println( " Dealing with " + index1DArray + " + " 
            //+ Arrays.toString( convertTo2DArrayIndex(index1DArray) ) );
            if (index1DArray % size == 0) {
                wuf.union(index1DArray, virtualTopIndex);
                //grid[p][q] = SITESTATE.FULL;
                //System.out.println( " Connecting to top" + virtualTopIndex + 
                //" + " + index1DArray + " + " + Arrays.toString( 
                //convertTo2DArrayIndex(index1DArray) ) );
            }
            
            if ((index1DArray % size) + 1 == size) {
                //System.out.println( " Connecting to bottom" + 
                //virtualBottomIndex + " + " + index1DArray + " + " + 
                //Arrays.toString( convertTo2DArrayIndex(index1DArray) ) );
                wuf.union(index1DArray, virtualBottomIndex);
            }
            
        
            //
            //System.out.println( " Into fill " + index1DArray +
            //" + [" + p + ", " + q +"]" ) ;
            //System.out.println("---------------------- 
            //[" + p +", " + q +"] ------------------------------");
            fill(p, q, index1DArray);

            
        }
    }
    

    public boolean isOpen(int i, int j)     // is site (row i, column j) open?
    {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
        int p = i-1;
        int q = j-1;
        
        if (grid[p][q] == SITESTATE.OPEN || grid[p][q] == SITESTATE.FULL) {
             return true;
        }
        return false;
    }
    public boolean isFull(int i, int j)     // is site (row i, column j) full?
    {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
        int p = i-1;
        int q = j-1;
        
        if (grid[p][q] == SITESTATE.FULL) {
             return true;
        }
        return false;
        
    }
    
    public boolean percolates()             // does the system percolate?
    {
        //return wuf.connected(virtualTopIndex, virtualBottomIndex);
        return r2();
    }
    
    public static void main(String[] args)   // test client (optional)
    {
        
    }
    
    private boolean f1(int i, int j) {
        return wuf.connected(virtualTopIndex, virtualBottomIndex);
    }
    private boolean f2(int i, int j) {
        int p = i-1;
        int q = j-1;
//        if( grid[i][j] == SITESTATE.FULL ) {
//             return true;
//        }
        int index = convertTo1DArrayIndex(p, q);
        
        for (int k = 0; k < size; k++) {
            int topRow = convertTo1DArrayIndex(0, k);
            if (isOpen(i, j) && wuf.connected(index, topRow)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean f3(int i, int j) {
        int p = i-1;
        int q = j-1;
//        if( grid[i][j] == SITESTATE.FULL ) {
//             return true;
//        }
        int index = convertTo1DArrayIndex(p, q);
        
        
        if (isOpen(i, j) && wuf.connected(index, virtualTopIndex)) {
            return true;
        }
        
        return false;
    }
    
    
    
    private boolean r1() {
        //int upperLimit = size * size -1;
        
        for (int i = 0; i <  size; i++) {
            int topRowElement = convertTo1DArrayIndex(0, i);
            
            for (int j = 0;  j < size; j++) {
                int bottomRowElement = convertTo1DArrayIndex(size - 1, j);
                if (wuf.connected(virtualTopIndex, bottomRowElement)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean r2() {
        // check to see if 
        return wuf.connected(virtualTopIndex, virtualBottomIndex);
    }
    
    private boolean r3() {
        //int upperLimit = size * size -1;
        
        for (int j = 0;  j < size; j++) {
            int bottomRowElement = convertTo1DArrayIndex(size - 1, j);
            if (wuf.connected(virtualTopIndex, bottomRowElement)) {
                return true;
            }
        }
        return false;
    }
    
    private void fill(int p, int q, int index1DArray) {
        
        //System.out.println("just before");
        if (!isFull(p+1, q+1) && isOpen(p+1, q+1)) {
            
            if (wuf.connected(virtualTopIndex, index1DArray)) {
            
                grid[p][q] = SITESTATE.FULL;
                int[] neighbor1DIndices = getNeighbour1DIndices(index1DArray);
                int nL = neighbor1DIndices.length;
                
                for (int k = 0; k < nL; k++) {
                    
                    if (neighbor1DIndices[k] >= 0 
                           && (neighbor1DIndices[k] < size2D)) {
                        
                        int[] index2DArray = 
                            convertTo2DArrayIndex(neighbor1DIndices[k]);
                        
                        //isOpen deals with 1 based array, therefore ( + 1)'s
                        if (isOpen(index2DArray[0]+1, index2DArray[1]+1) 
                                && 
                            !isFull(index2DArray[0]+1, index2DArray[1]+1)) {

                            //System.out.println("calling recursively .......
                            //...........................[" + 
                            //index2DArray[0] +", " + index2DArray[1] +"]");
                            fill(index2DArray[0], 
                                 index2DArray[1], neighbor1DIndices[k]);
                        }
                    }
                }
            }
        }
        return;
        
    }
        
    //get 4 neighbors (left, right, up, down) for an array element denoted 
    //by index i and j
    
    // returns 1D array index of neighbors, some of them will be out of bounds
    // to indicate that element is not vlaid.
    // It responsibility of the caller to check that index is inbounds
//    private int[] getNeighbour1DIndices(int i, int j) {
//        
//        int index1DArray = convertTo1DArrayIndex(i, j);
//
//        int [] retVal = new int[4];
//        
//        // determine neighbor on the left
//        //leftmost column of the virtual 2D array
//        if (index1DArray % size == 0) { 
//            retVal[0] =  -1;
//        } else {
//            retVal[0] = index1DArray - 1;
//        }
//        
//        // determine neighbor on the right
//        //rightmost column of the virtual 2D array
//        if ((index1DArray % size) + 1 == size) {
//            retVal[1] =  -1;
//        } else {
//            retVal[1] = index1DArray + 1;
//        }
//        
//        // determine neighbor below
//        retVal[2] = index1DArray + size;
//        
//        // determine neighbor above
//        retVal[3]  = index1DArray - size;    
//        
//        return retVal;
//        
//    }
    
    private int[] getNeighbour1DIndices(int index1DArray) {
        
        //int index1DArray = convertTo1DArrayIndex(i, j);

        int [] retVal = new int[4];
        
        // determine neighbor on the left
        // leftmost column of the virtual 2D array
        if (index1DArray % size == 0) { 
            retVal[0] =  -1;
        } else {
            retVal[0] = index1DArray - 1;
        }
        
        // determine neighbor on the right
        //rightmost column of the virtual 2D array
        if ((index1DArray % size) + 1 == size) {
            retVal[1] =  -1;
        } else {
            retVal[1] = index1DArray + 1;
        }
        
        // determine neighbor below
        retVal[2] = index1DArray + size;
        
        // determine neighbor above
        retVal[3]  = index1DArray - size;   
        
        return retVal;        
    }
    
    private int[] convertTo2DArrayIndex(int index1D) {
        int [] retVal = new int[2];
        retVal[0] = index1D % size; // column
        retVal[1] = index1D / size; // row
        return retVal;
    }
    
    // convert 2D array index [column,row] into a 1D array index
    // 2D array index is a
    private int convertTo1DArrayIndex(int column, int row) {
        return column + row * size;
    }
}