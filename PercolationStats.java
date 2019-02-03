/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;

    public PercolationStats(int n, int trials) {
        assertPositive(n);
        assertPositive(trials);
        results = new double[trials];
        runExperiments(n, trials);
    }

    private void assertPositive(int num) {
        if (num < 1) {
            throw new IllegalArgumentException();
        }
    }

    private void runExperiments(int n, int trials) {
        for (int i = 0; i < trials; i++) {
            results[i] = runExperiment(n);
        }
    }

    private double runExperiment(int n) {
        Percolation p = new Percolation(n);
        int openSpaces = 0;
        do {
            int row = random(n);
            int col = random(n);
            if (!p.isOpen(row, col)) {
                p.open(row, col);
                openSpaces++;
            }
        } while (!p.percolates());

        return (double) openSpaces / ((double) n * n);
    }

    private int random(int n) {
        return StdRandom.uniform(1, n);
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    private double confidence() {
        return (1.96 * stddev() / Math.sqrt(results.length));
    }

    public double confidenceLo() {
        return mean() - confidence();
    }

    public double confidenceHi() {
        return mean() + confidence();
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        System.out.println("mean                =" + stats.mean());
        System.out.println("stddev              =" + stats.stddev());
        System.out.println(
                "Confidence interval =" + stats.confidenceLo() + "," + stats.confidenceHi());
    }
}
