package letcode;

public class StringUtil {
    public static void main(String[] args) {
//        String a = "weqeqweqwsad";
//        String b = "qw";
//        char[] chars = a.toCharArray();
//        char[] chars1 = b.toCharArray();
//        int la = chars.length;
//        int lb = chars1.length;
//        for (int i=0;i<=la-lb;i++){
//            if(a.substring(i,i+lb).equals(b)){
//                for (int j=i;j<i+lb;j++){
//                    chars[j] = '*';
//                }
//            }
//        }
//        System.out.println(String.valueOf(chars));
        System.out.println(longestCommonPrefix(new String[]{"a"}));
    }
    public static String longestCommonPrefix(String[] strs) {
        char[] pre = strs[0].toCharArray();
        if (pre.length==0){
            return "";
        }
        int k = 0;
        for (int i=1;i<strs.length;i++){
            char[] tmp = strs[i].toCharArray();
            for (int j=0;j< tmp.length;j++){
                if (pre[j]!=tmp[j]){
                    break;
                }
                k = j;
            }
        }
        return k==0?"":String.valueOf(pre,0,k);
    }
}
