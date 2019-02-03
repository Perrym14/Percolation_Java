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
        connectToSourceNode(row, col);
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


    private void connectToSourceNode(int row, int col) {
        union(TOP_INDEX, toIndex(row, col));
    }

    private void connectToAdjacents(int row, int col) {
        connectTopNode(row, col);
        connectBottomNode(row, col);
        connectLeftNode(row, col);
        connectRightNode(row, col);
    }

    private void connectTopNode(int row, int col) {
        if (row > 1 && isOpen(row - 1, col)) {
            union(toIndex(row - 1, col), toIndex(row, col));
        }
    }

    private void connectBottomNode(int row, int col) {
        if (row < SIZE && isOpen(row + 1, col)) {
            union(toIndex(row + 1, col), toIndex(row, col));
        }
    }

    private void connectLeftNode(int row, int col) {
        if (row > 1 && isOpen(row, col - 1)) {
            union(toIndex(row, col - 1), toIndex(row, col));
        }
    }

    private void connectRightNode(int row, int col) {
        if (row < SIZE && isOpen(row, col + 1)) {
            union(toIndex(row, col + 1), toIndex(row, col));
        }
    }

    private void connectSinkNode(int row, int col) {
        if (row == SIZE) {
            backwash.union(BOTTOM_INDEX, toIndex(row, col));
        }
    }

    public boolean isOpen(int row, int col) {
        assertInRange(row, col);
        return openSites[toIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        assertInRange(row, col);
        return union.connected(toIndex(row, col), TOP_INDEX);
    }

    public boolean percolates() {
        return backwash.connected(BOTTOM_INDEX, TOP_INDEX);
    }
}
