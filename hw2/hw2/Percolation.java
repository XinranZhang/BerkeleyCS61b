package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int numOfOpenSite;
    private final int bottomSite;
    private final int topSite;
    private WeightedQuickUnionUF uf;
    private int dimension;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        dimension = N;
        topSite = N * N;
        bottomSite = N * N + 1;
        numOfOpenSite = 0;
        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
    }


    // convert (x, y) to 1D
    public int xy21D (int row, int col) {
        return row * dimension + col;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= dimension || col >= dimension) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = 1;
        numOfOpenSite++;

        if (row != 0) {
            if (isOpen(row - 1, col)) {
                uf.union(xy21D(row - 1, col), xy21D(row, col));
            }
        } else  {
            uf.union(topSite, xy21D(row, col));
        }

        if (row != dimension - 1) {
            if (isOpen(row + 1, col)) {
                uf.union(xy21D(row + 1, col), xy21D(row, col));
            }
        } else {
            uf.union(bottomSite, xy21D(row, col));
        }

        if (col != 0 && isOpen(row, col - 1)) {
            uf.union(xy21D(row, col - 1), xy21D(row, col));
        }

        if (col != dimension - 1 && isOpen(row, col + 1)) {
            uf.union(xy21D(row, col + 1), xy21D(row, col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= dimension || col >= dimension) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <0 || col < 0 || row >= dimension || col >= dimension) {
            throw new IndexOutOfBoundsException();
        }
        return uf.connected(topSite, xy21D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }

    // unit testing (not required)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(2, 1);
        System.out.println(percolation.isFull(2, 1));
        percolation.open(1, 1);
        System.out.println(percolation.isFull(2, 1));
        percolation.open(3, 1);
        System.out.println(percolation.percolates());
        System.out.println(percolation.numberOfOpenSites());
        percolation.open(4, 1);
        System.out.println(percolation.percolates());
        percolation.open(0, 1);
        System.out.println(percolation.isFull(0, 1));
        System.out.println(percolation.percolates());
    }
}                       
