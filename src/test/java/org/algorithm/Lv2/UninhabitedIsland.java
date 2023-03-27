package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class UninhabitedIsland {

    private static Stream<Arguments> setParameter() {
        String[] maps = new String[]{ "X591X", "X1X5X", "X231X", "1XXX1"};

        return Stream.of(
                Arguments.of((Object) maps)
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/154540
     * 무인도 여행
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void uninhabitedIsland(String[] maps){
        System.out.println(Arrays.toString(solution(maps)));
    }

    public int[] solution(String[] maps) {
        int width = maps[0].length();
        int height = maps.length;
        Point[][] map = new Point[height][width];
        for(int h = 0; h < height; h++) {
            String[] row = maps[h].split("");
            for(int w = 0; w < row.length; w++) {
                row[w] = row[w].equals("X") ? "0" : row[w];
                map[h][w] = new Point(w, h, Integer.parseInt(row[w]));
            }
        }
        List<Set<Point>> islandList = new ArrayList<>();
        for(int h = 0; h < map.length; h++) {

            for (int w = 0; w < map[0].length; w++) {
                if (map[h][w].getFoodCount() == 0) continue;
                Point curPoint = map[h][w];

                if(islandList.isEmpty()) {
                    Set<Point> current = new HashSet<>();
                    current.add(curPoint);
                    islandList.add(current);
                    continue;
                }

                if(h == 0) {
                    if (map[h][w - 1].getFoodCount() > 0) {
                        Set<Point> leftPointList = getIsland(islandList, map[h][w - 1]);
                        if (leftPointList == null) {
                            leftPointList = new HashSet<>();
                        }
                        leftPointList.add(curPoint);
                    } else {
                        Set<Point> currentList = new HashSet<>();
                        currentList.add(curPoint);
                        islandList.add(currentList);
                    }
                    continue;
                }

                // 위쪽에 데이터가 있는지 체크
                boolean hasTop = false;
                Set<Point> currentList = new HashSet<>();
                if (map[h - 1][w].getFoodCount() > 0) {
                    Set<Point> topPointList = getIsland(islandList, map[h - 1][w]);
                    topPointList.add(curPoint);
                    currentList = topPointList;
                    hasTop = true;
                } else {
                    currentList.add(curPoint);
                    islandList.add(currentList);
                }

                if (w > 0) {
                    if(map[h][w-1].getFoodCount() > 0) {
                        Set<Point> leftPointList = getIsland(islandList, map[h][w - 1]);
                        if(hasTop) {
                            if(!leftPointList.equals(currentList)) {
                                leftPointList.addAll(currentList);
                                final Set<Point> chkList = currentList;
                                islandList.removeIf(e -> e.equals(chkList));
                            }
                        } else {
                            leftPointList.add(curPoint);
                            final Set<Point> chkList = currentList;
                            islandList.removeIf(e->e.equals(chkList));

                        }
                    }
                }
            }
        }
        if(islandList.isEmpty()) return new int[]{-1};
        return islandList.stream().mapToInt(e->e.stream().mapToInt(ob -> ob.getFoodCount()).sum()).sorted().toArray();
    }

    public Set<Point> getIsland(List<Set<Point>> islandList,Point currentPoint) {
        for(Set<Point> island : islandList) {
            if(island.contains(currentPoint)) return island;
        }
        return null;
    }

    class Point {
        private int x;
        private int y;
        private int foodCount;

        public Point(int x, int y, int foodCount) {
            this.x = x;
            this.y = y;
            this.foodCount = foodCount;
        }

        public int getFoodCount() {
            return foodCount;
        }

    }
}