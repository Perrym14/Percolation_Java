/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF union;
    private WeightedQuickUnionUF backwash;
    private boolean[] openSites;
    private final int SIZE, TOP_INDEX, BOTTOM_INDEX;

    /* Calculate number of nodes,
       Create n by n grid "open sites",
       Create source and cink  */
    Percolation(int n) {
        int numNodes = n * n + 2; //"2" represents source and sink.
        union = new WeightedQuickUnionUF(numNodes);
        backwash = new WeightedQuickUnionUF(numNodes);
        openSites = new boolean[n * n];
        SIZE = n;
        TOP_INDEX = n * n;
        BOTTOM_INDEX = n * n + 1;

    }

    public void open(int row, int col) {
        assertInRange(row, col);
        openSite(row, col);
        connectToVirtualTopNode(row, col);
    }

    private void assertInRange(int row, int col) {
        if (row < 1 || row > SIZE || col < 1 || col > SIZE) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void openSite(int row, int col) {
        openSites[toIndex(row, col)] = true;
    }

    private int toIndex(int row, int col) {
        return (row - 1) * SIZE + (col - 1);
    }

    private void union(int row, int col) {
        union.union(row, col);
        backwash.union(row, col);
    }

    private void connectToVirtualTopNode(int row, int col) {
        if (row == 1) {
            union(TOP_INDEX, toIndex(row, col));
        }
    }

    public static void main(String[] args) {

    }
}
