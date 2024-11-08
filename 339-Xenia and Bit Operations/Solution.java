import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int n = -1, numOfQueries = -1;
 
        for(int i=0; i<2; i++) {
            if (n == -1) n = sc.nextInt();
            if (numOfQueries == -1) numOfQueries = sc.nextInt();
        }
 
 
        int arrLen = (int)Math.pow(2, n);
 
        int[] arr = new int[arrLen];
        int[][] queries = new int[numOfQueries][2];
 
        for(int i=0; i<arrLen; i++) arr[i] = sc.nextInt();
        for(int i=0; i<numOfQueries; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }
 
        sc.close();
 
        SGTree tree = new SGTree(arrLen, arr);
 
 
        for(int[] query: queries) {
            int index = query[0];
            int value = query[1];
 
            tree.update(0, 0, arrLen-1, index-1, value);
            System.out.println(tree.seg[0]);
        }
    }
}
 
class SGTree {
    int[] seg;
 
    public SGTree(int n, int[] arr) {
        int len = 4 * n;
        seg = new int[len];
        build(0, 0, n-1, arr);
    }
 
    private int build(int ind, int low, int high, int[] arr) {
        if (low == high) {
            seg[ind] = arr[low];
            return 0;
        }
 
        int mid = low + (high-low) / 2;
 
        int ret1 = build(2*ind + 1, low, mid, arr);
        int ret2 = build(2*ind + 2, mid+1, high, arr);
 
        if (ret1 == 0) seg[ind] = seg[2*ind + 1] | seg[2*ind + 2];
        else seg[ind] = seg[2*ind + 1] ^ seg[2*ind + 2];
 
        return 1 - ret1;
 
    }
 
    public int update(int ind, int low, int high, int index, int value) {
        if (low == high) {
            seg[ind] = value;
            return 0;
        }
 
        int mid = low + (high-low) / 2;
 
        int op = 0;
        if (index <= mid) op = update(2*ind + 1, low, mid, index, value);
        else op = update(2*ind + 2, mid+1, high, index, value);
 
        if (op == 0) seg[ind] = seg[2*ind + 1] | seg[2*ind + 2];
        else seg[ind] = seg[2*ind + 1] ^ seg[2*ind + 2];
 
        return 1 - op;
    }
}
