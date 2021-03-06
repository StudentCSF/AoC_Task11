import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        task112();
    }

    static final int N = 10;

    static void task112() {
        int[][] m = parseInput();
        Queue<KeyValue<Integer, Integer>> q = new LinkedList<>();
        int t = 0;
        for (; canContinue(m); t++) {
            charging(m, q);
        }
        System.out.println(t);
    }

    static void task11() {
        int[][] m = parseInput();
        Queue<KeyValue<Integer, Integer>> q = new LinkedList<>();
        int res = 0;
        for (int t = 0; t < 100; t++) {
            res += charging(m, q);
        }
        System.out.println(res);
    }

    static int[][] parseInput() {
        Scanner in = new Scanner(System.in);
        int[][] res = new int[N][N];
        for (int i = 0; i < N; i++) {
            String src = in.nextLine();
            for (int j = 0; j < N; j++) {
                res[i][j] = Integer.parseInt(src.charAt(j) + "");
            }
        }
        return res;
    }

    static boolean canContinue(int[][] m) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (m[i][j] > 0) return true;
            }
        }
        return false;
    }

    static int charging(int[][] m, Queue<KeyValue<Integer, Integer>> q) {
        int sum = inc(m, q);
        while (!q.isEmpty()) {
            KeyValue<Integer, Integer> kv = q.poll();
            int r = kv.k;
            int c = kv.v;
            if (r - 1 >= 0) {
                if (c - 1 >= 0) {
                    sum += chargeNeighbour(m, r - 1, c - 1, q);
                }
                sum += chargeNeighbour(m, r - 1, c, q);
                if (c + 1 < N) {
                    sum += chargeNeighbour(m, r - 1, c + 1, q);
                }
            }
            if (c - 1 >= 0) {
                sum += chargeNeighbour(m, r, c - 1, q);
            }
            if (c + 1 < N) {
                sum += chargeNeighbour(m, r, c + 1, q);
            }
            if (r + 1 < N) {
                if (c - 1 >= 0) {
                    sum += chargeNeighbour(m, r + 1, c - 1, q);
                }
                sum += chargeNeighbour(m, r + 1, c, q);
                if (c + 1 < N) {
                    sum += chargeNeighbour(m, r + 1, c + 1, q);
                }
            }
        }
        return sum;
    }

    static int inc(int[][] m, Queue<KeyValue<Integer, Integer>> q) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += charge(m, i, j, q);
            }
        }
        return sum;
    }

    static int chargeNeighbour(int[][] m, int i, int j, Queue<KeyValue<Integer, Integer>> q) {
        return m[i][j] > 0 ? charge(m, i, j, q) : 0;
    }

    static int charge(int[][] m, int i, int j, Queue<KeyValue<Integer, Integer>> q) {
        if (m[i][j] == 9) {
            m[i][j] = 0;
            q.offer(new KeyValue<>(i, j));
            return 1;
        }
        m[i][j]++;
        return 0;
    }

    static class KeyValue<K, V> {
        K k;
        V v;

        public KeyValue(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyValue<?, ?> keyValue = (KeyValue<?, ?>) o;
            return Objects.equals(k, keyValue.k) && Objects.equals(v, keyValue.v);
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, v);
        }
    }
}
