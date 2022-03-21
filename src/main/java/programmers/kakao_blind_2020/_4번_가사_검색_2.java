package programmers.kakao_blind_2020;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class _4번_가사_검색_2 {
    final int WILD_CHAR = '?';

    Node[] prefixTrie = new Node[10001];
    Node[] subfixTrie = new Node[10001];

    public int[] solution(String[] words, String[] queries) {
        for (int i = 0; i < prefixTrie.length; i++) prefixTrie[i] = new Node();
        for (int i = 0; i < subfixTrie.length; i++) subfixTrie[i] = new Node();

        for (String word : words) {
            insertWord(word, prefixTrie[word.length()]);
            insertWord(new StringBuffer(word).reverse().toString(), subfixTrie[word.length()]);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = search(queries[i]);
        }

        return answer;
    }

    private void insertWord(String word, Node root) {
        Node curNode = root;
        curNode.count++;
        for (int i = 0; i < word.length(); i++) {
            int cur = word.charAt(i) - 'a';
            if (curNode.arr[cur] == null) {
                curNode.arr[cur] = new Node();
            }

            curNode = curNode.arr[cur];
            curNode.count++;
        }
    }


    private int search(String query) {
        Node curNode;
        if (WILD_CHAR == query.charAt(0)) {
            curNode = subfixTrie[query.length()];
            query = new StringBuilder(query).reverse().toString();
        } else
            curNode = prefixTrie[query.length()];
        for (int i = 0; i < query.length(); i++) {
            if (WILD_CHAR == query.charAt(i)) break;
            int cur = query.charAt(i) - 'a';
            if (curNode.arr[cur] == null) return 0;
            else {
                curNode = curNode.arr[cur];
            }
        }

        return curNode.count;
    }

    public static class Node {
        int count;
        Node[] arr;

        public Node() {
            this.arr = new Node['z' - 'a' + 1];
        }
    }

    @Test
    void test() {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] expectedResult = {3, 2, 4, 1, 0};

        assertThat(solution(words, queries)).containsExactly(expectedResult);
    }
}
