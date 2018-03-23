package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] percolationThresholdArray;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;


    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        percolationThresholdArray = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates()) {
                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);
                percolation.open(randomRow, randomCol);
            }
            int numOpenSites = percolation.numberOfOpenSites();
            percolationThresholdArray[i] = ((double) numOpenSites) / (N * N);
        }
        mean = StdStats.mean(percolationThresholdArray);
        stddev = StdStats.stddev(percolationThresholdArray);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(T);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(T);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLow() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println(percolationStats.confidenceLow());
        System.out.println(percolationStats.confidenceHigh());
    }
}                       
