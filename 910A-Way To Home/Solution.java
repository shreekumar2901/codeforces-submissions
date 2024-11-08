import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int target = sc.nextInt()-1;
        int d = sc.nextInt();
 
        String lillies = sc.next();
        sc.close();
        
        int src = 0, stepTaken = 0;
 
        PriorityQueue<Pair> queue = new PriorityQueue<>((a,b) -> a.step - b.step);
        int[] visited = new int[target+1];
        queue.offer(new Pair(src, 0));
        visited[0] = 1;
 
        boolean reached = false;
 
        while(!queue.isEmpty()) {
            Pair pair = queue.poll();
            int node = pair.node, step = pair.step;
 
 
            if (node == target) {
                reached = true;
                stepTaken = step;
                break;
            }
 
            for(int i=1; i<=d; i++) {
                if (node + i < lillies.length() && lillies.charAt(node + i) == '1' && visited[node + i] != 1) {
                    visited[node + i] = 1;
                    queue.offer(new Pair(node+i, step+1));
                }
            }
        }
 
        if (reached) System.out.println(stepTaken);
        else System.out.println(-1);
 
 
    }
}
 
class Pair {
    int node, step;
 
    public Pair(int node, int step) {
        this.node = node;
        this.step = step;
    }
}
