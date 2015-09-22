
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    
    private int gridSize;
    private int numberOfExperiments;
    private int[] numberOfOpenSitesForEachExperiment;
    
    private double[] percolationThreshold;
    
    private double mean;
    private double stddev;
    private double variance;
    private double confidenceHi;
    private double confidenceLo;
    
    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1) {
            throw new java.lang.IllegalArgumentException();
        }
        
        gridSize = N;
        numberOfExperiments = T;
        numberOfOpenSitesForEachExperiment = new int[T];
        percolationThreshold = new double[T];

        mean = 0.0;
        stddev = 0.0;
        
        int iToOpen, jToOpen;
        int numOpen = 0;
        
        int expI = 0;

        for (int i = 0; i < numberOfExperiments; i++) {
            Percolation P = new Percolation(gridSize);
            numOpen = 0;
            
            while (!P.percolates()) {

                
                 do {
                    iToOpen = StdRandom.uniform(gridSize)+1;
                    jToOpen = StdRandom.uniform(gridSize)+1;
                } while (P.isOpen(iToOpen, jToOpen));
                
                P.open(iToOpen, jToOpen);
                numOpen++;
            }
            
            //System.out.println(numOpen);
            numberOfOpenSitesForEachExperiment[expI] = numOpen;
            percolationThreshold[expI] = numOpen / (gridSize*gridSize*1.0);
            
            //System.out.println(numOpen + " -- " + expI + " -- " 
            //+ percolationThreshold[expI]);
            
            expI++;
            P = null;
            
         }
        
    }
    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (int i = 0; i < numberOfExperiments; i++) {
            sum += percolationThreshold[i];
        }

        mean =  sum / numberOfExperiments;
        return mean;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        double meanLocal = mean();
        
        double[] squared = new double[numberOfExperiments];
        variance = 0.0;
        for (int i = 0; i < numberOfExperiments; i++) {
            squared[i] = (percolationThreshold[i] - meanLocal) 
                * (percolationThreshold[i] - meanLocal);
            variance += squared[i];
        }
        variance = variance / (numberOfExperiments - 1);
        stddev = Math.sqrt(variance);
        return stddev;
    }
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        double meanLocal = mean();
        double stddevLocal = stddev();
        confidenceLo = meanLocal - 1.96*stddevLocal/Math.sqrt(numberOfExperiments);
        return confidenceLo;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double meanLocal = mean();
        double stddevLocal = stddev();
        confidenceHi = meanLocal + 1.96*stddevLocal/Math.sqrt(numberOfExperiments);
        return confidenceHi;
    }
    

    // test client (described below)
    public static void main(String[] args) {
        
        int N = 10;    
        int T = 1;
        if (args.length == 2) {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        }
        
        Stopwatch sw = new Stopwatch();
         
        PercolationStats pS = new PercolationStats(N, T);
        
        StdOut.println("mean\t\t\t= " + pS.mean());
        StdOut.println("stddev\t\t\t= " + pS.stddev());
        StdOut.println("95% confidence level\t= " + pS.confidenceLo()+ ", " 
                           + pS.confidenceHi());
        
        StdOut.println("Time :" + sw.elapsedTime());
        
//        StdOut.println(pS.confidenceHi());
//        StdOut.println(pS.stddev());
//        StdOut.println(pS.mean());
//        StdOut.println(pS.mean());
//        StdOut.println(pS.confidenceLo());
//        StdOut.println(pS.mean());
//        StdOut.println(pS.stddev());
        
    }
}