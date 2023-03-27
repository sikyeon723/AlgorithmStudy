package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RicoChat {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of((Object)new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."})
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/169199?language=java
     * 리코쳇 로봇
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void ricoChat(String[] board){
        System.out.println(solution(board));
    }

    public int solution(String[] board) {
        if (board.length == 0) return -1;
        Node[][] boardMap = new Node[board.length][board[0].length()];
        int x = 0;
        int y = 0;

        int stX = 0;
        int stY = 0;

        for(String map : board) {
            x = 0;
            for (String s : map.split("")) {
                // 시작점 좌표 설정
                if (s.equals("R")) {
                    stX = x;
                    stY = y;
                }

                // Node 배열로 재정의
                Node node = new Node(x, y, s);
                boardMap[y][x] = node;
                x++;
            }
            y++;
        }

        int answer = checkDeadEnd(stX, stY, boardMap);

        if(answer == -1) return -1;
        return answer;
    }

    public int checkDeadEnd(int stX, int stY, Node[][] map) {
        Set<Node> openList = new HashSet<>();
        Set<Node> closeList = new HashSet<>();

        openList.add(map[stY][stX]);

        int cnt = 0;
        while(!closeList.containsAll(openList)) {
            /**
             *  bfs 방식
             *  처음 openList에 시작점을 넣고 시작
             *  openList와 closeList의 노드들이 같을 경우 break -1반환
             *  로직 수행전에 closeList에 openList에 들어있는 모든 요소들을 추가
             *  closeList에 존재하지 않았던 openList 요소들로 로직 수행
             *  상, 하, 좌, 우 끝까지 간 지점의 좌표들을 openList에 추가
             */
            Set<Node> nodes = openList.stream().filter(node -> !closeList.contains(node)).collect(Collectors.toSet());
            closeList.addAll(openList);
            for(Node list : nodes) {
                slidingNode("Right", list.getX(), list.getY(), map, openList);
                slidingNode("Left", list.getX(), list.getY(), map, openList);
                slidingNode("Up", list.getX(), list.getY(), map, openList);
                slidingNode("Down", list.getX(), list.getY(), map, openList);
            }
            cnt++;
            if(openList.stream().anyMatch(e->"G".equals(e.getV()))) return cnt;
        }

        return -1;
    }

    public void slidingNode(String direction, int x, int y, Node[][] map, Set openList) {

        switch (direction) {
            case "Right":
                if(x + 1 >= map[0].length || "D".equals(map[y][x + 1].getV())) {
                    openList.add(map[y][x]);
                    return;
                }
                slidingNode("Right", x + 1, y, map, openList);
                break;
            case "Left":
                if(x - 1 < 0 || "D".equals(map[y][x - 1].getV())) {
                    openList.add(map[y][x]);
                    return;
                }
                slidingNode("Left", x - 1, y, map, openList);
                break;
            case "Up":
                if(y - 1 < 0 || "D".equals(map[y - 1][x].getV())) {
                    openList.add(map[y][x]);
                    return;
                }
                slidingNode("Up", x, y - 1, map, openList);
                break;
            case "Down":
                if(y + 1 >= map.length || "D".equals(map[y + 1][x].getV())) {
                    openList.add(map[y][x]);
                    return;
                }
                slidingNode("Down", x, y + 1, map, openList);
                break;
        }
    }

    class Node {

        private int x;
        private int y;
        private String v;

        public Node(int x, int y, String v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", x=" + x +
                    ", y=" + y +
                    ", v=" + v +
                    '}';
        }
    }
}
