package 剑指;

/**
 * Created by zz on 2022/5/3.
 */
public class Trie {

    private final Node root;

    static class Node {

        Node[] next;

        boolean isEnd;

        public Node() {
            next = new Node[26];
        }

    }

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     * 描述：向 Trie 中插入一个单词 word
     * <p>
     * 实现：这个操作和构建链表很像。首先从根结点的子结点开始与 word 第一个字符进行匹配，一直匹配到前缀链上没有对应的字符，这时开始不断开辟新的结点，直到插入完 word 的最后一个字符，同时还要将最后一个结点isEnd = true;，表示它是一个单词的末尾
     */
    public void insert(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            // 判断对应节点是否为空，如果为空，则直接插入
            if (cur.next[ch - 'a'] == null) {
                cur.next[ch - 'a'] = new Node();
            }
            // 继续插入下一个节点
            cur = cur.next[ch - 'a'];
        }
        // 将最后一个字符设置为结尾
        cur.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     * 描述：查找 Trie 中是否存在单词 word
     * <p>
     * 实现：从根结点的子结点开始，一直向下匹配即可，如果出现结点值为空就返回 false，如果匹配到了最后一个字符，那我们只需判断 cur.isEnd 即可。
     */
    public boolean search(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            // 如果对应节点为空，则表明不存在这个单词，返回false
            if (cur.next[ch - 'a'] == null) {
                return false;
            }
            cur = cur.next[ch - 'a'];
        }
        // 检查最后一个字符是否是结尾
        return cur.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     * <p>
     * 描述：判断 Trie 中是或有以 prefix 为前缀的单词
     * <p>
     * 实现：和 search 操作类似，只是不需要判断最后一个字符结点的 isEnd，因为既然能匹配到最后一个字符，那后面一定有单词是以它为前缀的。
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (char ch : prefix.toCharArray()) {
            if (cur.next[ch - 'a'] == null) {
                return false;
            }
            cur = cur.next[ch - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        boolean f1 = trie.search("apple");   // 返回 True
        boolean f2 = trie.search("app");     // 返回 False
        boolean f3 = trie.startsWith("app"); // 返回 True
        trie.insert("app");
        boolean f4 = trie.search("app");     // 返回 True

        System.out.println(f1 + ", " + f2 + ", " + f3 + ", " + f4);
    }

}
