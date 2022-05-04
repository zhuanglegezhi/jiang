# 题目描述

给面试者

```java
Trie 前缀树是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

1.请实现Trie类：
Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 
提示：
1 <= word.length, prefix.length <= 2000
word 和 prefix 仅由小写英文字母组成

2.写测试用例

3.加分项：
int countWordsEqualTo(String word) 返回前缀树中字符串 word 的实例个数。
int countWordsStartingWith(String prefix) 返回前缀树中以 prefix 为前缀的字符串个数。


解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True
```

接口规范

```java
class Trie {

    /** Initialize your data structure here. */
    public Trie() {

    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {

    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {

    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {

    }
}
```



# 过程

基本性质

1. 根节点不包含字符，除根节点外每一个节点都只包含一个字符。
2. 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串。
3. 每个节点的所有子节点包含的字符都不相同。



# 答案

```java
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
```

