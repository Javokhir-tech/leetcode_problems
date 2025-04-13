class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            int src = prereq[1], dst = prereq[0];
            adj.get(src).add(dst);
        }
        List<Integer> courses = new ArrayList<>();
        int[] visit = new int[numCourses];  // to mark visiting, visited
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, adj, visit, courses)) {
                return new int[]{};   // if cycle
            }
        }
        Collections.reverse(courses);
        return courses.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean dfs(int src, Map<Integer, List<Integer>> adj, int[] visit, List<Integer> topSort) {
        if (visit[src] == 1) {  // check if visiting
            return false;
        }
        if (visit[src] == 2) {  // if visited
            return true;
        }
        visit[src] = 1; // mark as visiting
        for (int nei : adj.get(src)) {
            if (!dfs(nei, adj, visit, topSort)) {
                return false;
            }
        }
        visit[src] = 2; // fully visited
        topSort.add(src);
        return true;
    }
}