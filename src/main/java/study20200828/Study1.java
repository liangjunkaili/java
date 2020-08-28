package study20200828;

import java.util.Arrays;

/**
 * 单词的压缩编码
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * 示例：
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 * 提示：
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 */
public class Study1 {
    public static void main(String[] args) {
        Study1 study1 = new Study1();
        System.out.println(study1.minimumLengthEncoding(new String[]{"time","me","bell"}));
        System.out.println(study1.minimumLengthEncoding_v2(new String[]{"time","me","bell"}));
    }
    public int minimumLengthEncoding(String[] words) {
        int len = 0;
        Trie trie = new Trie();
        // 先对单词列表根据单词长度由长到短排序
        Arrays.sort(words,(s1,s2) -> s2.length()-s1.length());
        //插入到Trie树
        for (String word:words){
            len += trie.insert(word);
        }
        return len;
    }
    public int minimumLengthEncoding_v2(String[] words){
        int len = 0;
        int n = words.length;
        String[] words_reverse = new String[n];
        for (int i=0;i<n;i++){
            String reverse = new StringBuilder(words[i]).reverse().toString();
            words_reverse[i] = reverse;
        }
        Arrays.sort(words_reverse);
        String result = "";
        for (int i=0;i<n;i++){
            if (i+1<n && words_reverse[i+1].startsWith(words_reverse[i])){
            }else {
                result += words_reverse[i]+"#";
                len += words_reverse[i].length()+1;
            }
        }
        System.out.println(result);
        return len;
    }
}
class Trie{
    TrieNode root;
    public Trie(){
        root = new TrieNode();
    }
    public int insert(String word){
        TrieNode cur = root;
        boolean isNew = false;
        for (int i=word.length()-1;i>=0;i--){
            char c = word.charAt(i);
            if (cur.children[c-'a']==null){
                isNew = true;
                cur.children[c-'a'] = new TrieNode();
            }
            cur = cur.children[c-'a'];
        }
        return isNew ? word.length()+1:0;
    }
}
class TrieNode{
    char val;
    TrieNode[] children = new TrieNode[26];
    public TrieNode(){

    }
}