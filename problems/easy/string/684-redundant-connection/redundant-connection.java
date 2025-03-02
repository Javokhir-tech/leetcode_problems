class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return null;
    }

    private class UnionFind {
        private Map<Integer, Integer> parent;
        private Map<Integer, Integer> rank;

        private UnionFind(int n) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            for (int i = 1; i < n + 1; i++) {
                parent.put(i, i);
                rank.put(i, 0);
            }
        }

        private int find(int x) {
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        private boolean union(int x, int y) {
            int p1 = find(x), p2 = find(y);
            if (p1 == p2) {
                return false;
            }
            if (rank.get(p1) > rank.get(p2)) {
                parent.put(p2, p1);
            } else if (rank.get(p1) < rank.get(p2)) {
                parent.put(p1, p2);
            } else {
                parent.put(p1, p2);
                rank.put(p2, rank.get(p2) + 1);
            }
            return true;
        }
    }
}