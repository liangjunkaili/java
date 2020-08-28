package study20200828;

import java.util.HashMap;
import java.util.Map;

public class Move {
    public boolean judgeCircle(String moves) {
        int x=0,y=0;
        char[] chars = moves.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]=='U'){
                y++;
            }else if (chars[i]=='D'){
                y--;
            }else if (chars[i]=='L'){
                x++;
            }else if (chars[i]=='R'){
                x--;
            }
        }
        if (x==0&&y==0){
            return true;
        }
        return false;
    }
    public boolean judgeCircle_v2(String moves) {
        char[] chars = moves.toCharArray();
        int count[] = new int[21];
        for (char c:chars){
            count[c-'A']++;
        }
        if(count['U'-'A']==count['D'-'A']&&count['L'-'A']==count['R'-'A'])
            return true;
        return false;
    }

    public static void main(String[] args) {
        Move move = new Move();
        System.out.println(move.judgeCircle("UDLRLLLDDUU"));
        System.out.println(move.judgeCircle("UDUDUD"));
        System.out.println(move.judgeCircle_v2("UDLRLLLDDUU"));
        System.out.println(move.judgeCircle_v2("UDUDUD"));
        System.out.println('U'-'A');
        System.out.println('D'-'A');
        System.out.println('L'-'A');
        System.out.println('R'-'A');
    }
}
