package study20200903;

import java.util.*;

/**
 * 51. N 皇后
 * n皇后问题研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的n皇后问题的解决方案。
 * 每一种解法包含一个明确的n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例：
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 提示：
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class Solution {
    public static void main(String[] args) {
        List<List<String>> lists = new Solution().solveNQueens(5);
        System.out.println(lists);
    }
    //回溯算法
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queues = new int[n];
        Arrays.fill(queues,-1);
        Set<Integer> columns = new HashSet<>();//列
        Set<Integer> diagonals1 = new HashSet<>();//左上-右下对角线（行坐标与列坐标之差相等）
        Set<Integer> diagonals2 = new HashSet<>();//右上-左下对角线（行坐标与列坐标之和相等）
        backTrack(n,0,queues,columns,diagonals1,diagonals2,solutions);
        return solutions;
    }
    public void backTrack(int n,int row,int[] queues,Set<Integer> columns,
                          Set<Integer> diagonals1,Set<Integer> diagonals2,
                          List<List<String>> solutions){
        if (row==n){//当行等于N时说明已经把N个皇后都放好了
            List<String> list = generateBoard(n,queues);
            solutions.add(list);
        }else {
            for (int i=0;i<n;i++){
                if (columns.contains(i)){
                    continue;
                }
                int diagonal1 = row-i;
                if (diagonals1.contains(diagonal1)){
                    continue;
                }
                int diagonal2 = row+i;
                if (diagonals2.contains(diagonal2)){
                    continue;
                }
                queues[row] = i;//表示当前行的值为第几列
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                //上一行放好之后，继续放置下一个
                backTrack(n, row+1, queues, columns, diagonals1, diagonals2,solutions);
                //以下操作是在得到一种解法后，重置状态
                queues[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }
    public List<String> generateBoard(int n,int[] queues){
        List<String> list = new ArrayList<>();
        for (int i=0;i<n;i++){
            char[] chars = new char[n];
            Arrays.fill(chars,'.');
            chars[queues[i]] = 'Q';
            list.add(new String(chars));
        }
        return list;
    }
}
