package lc;

/**
 * Created by zz on 2022/5/4.
 */
public class L1804 {

    static class Trie {
        private Trie[] children;
        private int count;
        private int preCount;

        public Trie() {
            children = new Trie[26];
            count = 0;
            preCount = 0;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); ++i) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
                node.preCount += 1;
            }
            node.count += 1;
        }

        public int countWordsEqualTo(String word) {
            Trie node = searchPrefix(word);
            return node == null ? 0 : node.count;
        }

        public int countWordsStartingWith(String prefix) {
            Trie node = searchPrefix(prefix);
            return node == null ? 0 : node.preCount;
        }

        public void erase(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); ++i) {
                int index = word.charAt(i) - 'a';
                node = node.children[index];
                node.preCount -= 1;
            }
            node.count -= 1;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); ++i) {
                int index = prefix.charAt(i) - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
}
