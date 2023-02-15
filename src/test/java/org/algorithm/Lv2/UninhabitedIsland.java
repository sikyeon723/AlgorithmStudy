package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class UninhabitedIsland {

    private static Stream<Arguments> setParameter() {
        String[] maps = new String[]{ "X591X", "X1X5X", "X231X", "1XXX1"};
//                 "693X7981"
//                ,"577X6886"
//                ,"5979X773"
//                ,"X896171X"
//                ,"X658434X"
//                ,"5565X958"
//                ,"57759453"
//                ,"X1999299"
//                ,"X3861382"
//                ,"12334957"
//                ,"2447247X"
//                ,"79X746X2"
//                ,"67827986"
//                ,"4489869X"
//                ,"6X229888"
//                ,"79659X44"
//                ,"99X44793"
//                ,"X7527446"
//                ,"91173744"
//                ,"29761349"
//                ,"88X25547"
//                ,"X978XX84"
//                ,"29678382"
//                ,"8356X389"
//                ,"X5493737"
//                ,"259665X5"
//                ,"81X34118"
//                ,"41786265"
//                ,"7716X398"
//                ,"728581X7"
//                ,"899863X6"
//                ,"55674515"
//                ,"3144X731"
//                ,"9XX95597"
//                ,"X752X66X"
//                ,"489XX836"
//                ,"56357415"
//                ,"5X4XXX53"
//                ,"97358394"
//                ,"16514837"
//                ,"53641498"
//                ,"796345X4"
//                ,"66741228"
//                ,"465X4123"
//                ,"67698X48"
//                ,"18553863"
//                ,"72463316"
//                ,"71X36956"
//                ,"68373X84"
//                 "4XX7X7XX"
//                ,"8XX3X48X"
//                ,"12932X3X"
//                ,"XXX6X235"
//                ,"18187XX8"
//                ,"X1XXX2X8"
//                ,"XXX6342X"};
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