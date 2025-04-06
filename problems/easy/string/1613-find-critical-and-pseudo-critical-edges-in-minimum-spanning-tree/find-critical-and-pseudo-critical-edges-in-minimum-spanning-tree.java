class Solution {
    class UnionFind {
        private int[] parent;
        private int[] rank;
        private UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        private int find(int v) {
            if (parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }

        private boolean union(int n1, int n2) {
            int p1 = find(n1), p2 = find(n2);
            if (p1 == p2) return false;
            if (rank[p1] > rank[p2]) {
                parent[p2] = p1;
                rank[p1] += rank[p2];
            } else {
                parent[p1] = p2;
                rank[p2] += rank[p1];
            }
            return true;
        }
    }
    
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<int[]> edgeList = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            edgeList.add(new int[] { edges[i][0], edges[i][1], edges[i][2], i });
        }
        edgeList.sort(Comparator.comparingInt(a -> a[2]));

        int mstWeight = 0;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edgeList) {
            if (uf.union(edge[0], edge[1])) {
                mstWeight += edge[2];
            }
        }

        List<Integer> critical = new ArrayList<>(); // list to store criticals
        List<Integer> pseudo = new ArrayList<>();

        for (int[] edge : edgeList) {
            // Try without current edge
            UnionFind ufWithout = new UnionFind(n);
            int weight = 0;
            for (int[] other : edgeList) {
                if (other[3] != edge[3] && ufWithout.union(other[0], other[1])) {
                    weight += other[2];
                }
            }
            if (Arrays.stream(ufWithout.rank).max().getAsInt() != n || weight > mstWeight) {
                critical.add(edge[3]);
                continue;
            }

            // Try with current edge
            UnionFind ufWith = new UnionFind(n);
            ufWith.union(edge[0], edge[1]);
            weight = edge[2];
            for (int[] other : edgeList) {
                if (ufWith.union(other[0], other[1])) {
                    weight += other[2];
                }
            }
            if (weight == mstWeight) {
                pseudo.add(edge[3]);
            }
        }

        return Arrays.asList(critical, pseudo);
    }
}