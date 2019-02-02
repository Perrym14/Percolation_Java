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
       Create N by N grid "open sites",
       Create source and cink  */
    Percolation(int N) {
        int numNodes = N * N + 2; //"2" represents source and sink.
        union = new WeightedQuickUnionUF(numNodes);
        backwash = new WeightedQuickUnionUF(numNodes);
        openSites = new boolean[N * N];
        SIZE = N;
        TOP_INDEX = N * N;
        BOTTOM_INDEX = N * N + 1;

    }

    public static void main(String[] args) {

    }
}
