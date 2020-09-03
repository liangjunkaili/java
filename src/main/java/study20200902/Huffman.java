package study20200902;


import java.util.*;

/**
 * Huffman树-最优二叉树
 * 定义：给定n个权值作为n个叶子结点，构造一棵二叉树，若树的带权路径长度达到最小，则这棵树被称为哈夫曼树。
 * 如何构造Huffman树：
 * 1. 将w1、w2、…，wn看成是有n 棵树的森林(每棵树仅有一个结点)；
 * 2. 在森林中选出根结点的权值最小的两棵树进行合并，作为一棵新树的左、右子树，且新树的根结点权值为其左、右子树根结点权值之和；
 * 3. 从森林中删除选取的两棵树，并将新树加入森林；
 * 4. 重复(02)、(03)步，直到森林中只剩一棵树为止，该树即为所求得的哈夫曼树。
 */
public class Huffman {
    private HuffmanNode root;
    //1、统计原始字符串各字符出现的次数
    public Map<Character,Integer> strToMap(String ori){
        char[] chars = ori.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for (char c:chars){
            if (map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else {
                map.put(c,1);
            }
        }
        return map;
    }
    //2、根据原始字符构建所有的叶子节点
    public List<HuffmanNode> init(Map<Character,Integer> map){
        Set<Character> set = map.keySet();
        //第一步构造N棵树的森林
        List<HuffmanNode> nodeList = new ArrayList<>();
        for (Character c:set){
            nodeList.add(new HuffmanNode(c.toString(),map.get(c),null,null,null));
        }
        return nodeList;
    }
    //3、将叶子节点放入到一个堆（优先级队列）中，方便取出最小的节点，构造出一个Huffman树
    public HuffmanNode build(List<HuffmanNode> nodeList){
        Queue<HuffmanNode> queue = new PriorityQueue<>(nodeList);
        while (queue.size()>1){
            HuffmanNode l = queue.poll();
            HuffmanNode r = queue.poll();
            HuffmanNode p = new HuffmanNode(l.c.toString()+r.c.toString(),l.key+r.key,l,r,null);
            l.parent = p;
            r.parent = p;
            queue.add(p);
        }
        return root=queue.poll();
    }
    //4、得到字符的编码表
    public Map<Character,String> getEncodeMap(List<HuffmanNode> nodeList){
        Map<Character,String> map = new HashMap<>();
        HuffmanNode curr;
        for (HuffmanNode node:nodeList){
            curr = node;
            String encode = "";
            while (curr.parent!=null){
                if (curr.isLeftChild()){
                    encode = "0"+encode;
                }else {
                    encode = "1"+encode;
                }
                curr = curr.parent;
            }
            map.put(node.c.charAt(0),encode);
        }
        return map;
    }
    //5、编码
    public String encoding(String ori,Map<Character,String> map){
        StringBuilder sb = new StringBuilder();
        char[] chars = ori.toCharArray();
        for (char c:chars){
            sb.append(map.get(c));
        }
        return sb.toString();
    }
    //6、解码
    public String decoding(String encodingStr){
        char[] chars = encodingStr.toCharArray();
        LinkedList<Character> binaryList = new LinkedList<>();
        int size = chars.length;
        for (int i = 0; i < size; i++) {
            binaryList.addLast(new Character(chars[i]));
        }
        StringBuilder sb = new StringBuilder();
        while (binaryList.size()>0){
            HuffmanNode curr = root;
            while (!curr.isLeaf()){
                Character c = binaryList.removeFirst();
                if (c =='0'){
                    curr = curr.left;
                }else {
                    curr = curr.right;
                }
            }
            sb.append(curr.c);
        }
        return sb.toString();
    }
    public int cost(List<HuffmanNode> nodeList){
        int cost = 0;
        for (HuffmanNode node:nodeList){
            int level = 0;
            HuffmanNode curr = node;
            while (curr!=root){
                curr = curr.parent;
                level++;//算出该叶子节点在第几层
            }
            cost += level*node.key;
        }
        return cost;
    }
    //等长编码33的编码为100001，每个字符6位，下面的长度为888，哈夫曼编码为661
    public static void main(String[] args) {
        String ori = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, " +
                "depending on the characteristics of the data being compressed. 中华崛起";
        System.out.println(ori.length());
        Huffman huffman = new Huffman();
        Map<Character,Integer> characterIntegerMap = huffman.strToMap(ori);
        System.out.println(characterIntegerMap);
        List<HuffmanNode> nodeList = huffman.init(characterIntegerMap);
        HuffmanNode root = huffman.build(nodeList);
        Map<Character,String> characterStringMap = huffman.getEncodeMap(nodeList);
        String encode = huffman.encoding(ori,characterStringMap);
        System.out.println("编码后："+encode);
        int cost = huffman.cost(nodeList);
        System.out.println("cost："+cost);
        String decodStr = huffman.decoding(encode);
        System.out.println(decodStr);
        System.out.println(ori.length()*6);
    }
}
class HuffmanNode implements Comparable<HuffmanNode>{
    public String c;//字符
    public int key;//频率
    public HuffmanNode left;
    public HuffmanNode right;
    public HuffmanNode parent;

    public HuffmanNode(String c,int key, HuffmanNode left, HuffmanNode right, HuffmanNode parent) {
        this.c = c;
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public boolean isLeaf(){
        return c.length()==1;
    }
    public boolean isLeftChild(){
        return parent!=null && parent.left==this;
    }
    public boolean isRightChild(){
        return parent!=null && parent.right==this;
    }
    @Override
    public int compareTo(HuffmanNode o) {
        return key-o.key;
    }
}