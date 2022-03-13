package programmers.kakao_blind_2019;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class _4번_길_찾기_게임 {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    List<Integer> preOrderArr = new ArrayList<>();
    List<Integer> postOrderArr = new ArrayList<>();

    public int[][] solution(int[][] nodeinfo) {
        for (int i = 0; i < nodeinfo.length; i++) {
            int[] pos = nodeinfo[i];
            pq.add(new Node(pos[0], pos[1], i + 1));
        }

        Node root = pq.poll();
        while (!pq.isEmpty()) {
            insertNode(root, pq.poll());
        }

        preOrder(root);
        postOrder(root);

        return new int[][]{
                preOrderArr.stream().mapToInt(i -> i).toArray(),
                postOrderArr.stream().mapToInt(i -> i).toArray()};
    }

    private void insertNode(Node root, Node node) {
        if (root.x > node.x) {
            if (root.left == null) {
                root.left = node;
            } else {
                insertNode(root.left, node);
            }
        } else {
            if (root.right == null) {
                root.right = node;
            } else {
                insertNode(root.right, node);
            }
        }
    }

    private void preOrder(Node root) {
        preOrderArr.add(root.num);
        if (root.left != null) preOrder(root.left);
        if (root.right != null) preOrder(root.right);
    }

    private void postOrder(Node root) {
        if (root.left != null) postOrder(root.left);
        if (root.right != null) postOrder(root.right);
        postOrderArr.add(root.num);
    }

    public static class Node implements Comparable<Node> {
        int x;
        int y;
        int num;
        Node left = null;
        Node right = null;

        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            int compareY = Integer.compare(o.y, this.y);
            if (compareY == 0) {
                return Integer.compare(this.x, o.x);
            } else {
                return compareY;
            }
        }
    }

    @Test
    void test() {
        // given
        int[][] testArr = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] expectArr = {{7, 4, 6, 9, 1, 8, 5, 2, 3}, {9, 6, 5, 8, 1, 4, 3, 2, 7}};

        // when
        int[][] actualArr = solution(testArr);

        //then
        assertThat(actualArr).isDeepEqualTo(expectArr);
    }
}
