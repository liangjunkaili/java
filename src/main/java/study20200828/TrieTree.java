package study20200828;

/**
 * 字典树、Trie树、前缀树
 */
public class TrieTree {
    class Node{
        public char data;
        public Node[] children = new Node[26];
        public boolean isEndingChar = false;
        public Node(char data){
            this.data = data;
        }
    }
    public Node root = new Node('/');
    public void insert(char[] text){
        Node p = root;
        for (int i=0;i<text.length;i++){
            int index = text[i] - 'a';
            if (p.children[index]==null){
                Node newNode = new Node(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }
    public boolean find(char[] text){
        Node p = root;
        for (int i=0;i<text.length;i++){
            int index = text[i] - 'a';
            if (p.children[index]==null){
                return false;
            }
            p = p.children[index];
        }
        return p.isEndingChar;
    }

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        String[] words = {"time", "me", "bell"};
        for (String word:words){
            trieTree.insert(word.toCharArray());
        }
    }
}
