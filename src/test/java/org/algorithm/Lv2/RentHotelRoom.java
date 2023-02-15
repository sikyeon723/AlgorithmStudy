package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class RentHotelRoom {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"},{"14:20", "15:20"},{"14:10", "19:20"},{"18:20", "21:20"}})
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/155651
     * 호텔 대실
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void uninhabitedIsland(String[][] book_time){
        System.out.println(solution(book_time));
    }

    public int solution(String[][] book_time) {
        List<Room> rooms = new ArrayList<>();

        // 예약 목록
        for(String[] bookArr : book_time) {

            // 청소시간 10분+ [S]
            String[] startTime = bookArr[0].split(":");
            String[] endTime = bookArr[1].split(":");

            int startTimestamp = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            int endTimestamp = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]) + 10;

            Reservation newResv = new Reservation(startTimestamp,endTimestamp);

            // 첫 데이터 add
            if(rooms.isEmpty()){
                Room room = new Room();
                room.addReservation(newResv);
                rooms.add(room);
                continue;
            }

            boolean chkResv = true;
            // 방에 해당 시간 예약이 있는지 검증
            for(Room room : rooms) {
                if(room.checkReservation(newResv)){
                    room.addReservation(newResv);
                    chkResv = false;
                }
            }

            // 방에 예약 가능한 시간이 없으면 방 추가
            if(chkResv) {
                Room newRoom = new Room();
                newRoom.addReservation(newResv);
                rooms.add(newRoom);
            }
        }
        return rooms.size();
    }

    class Room {
        private List<Reservation> reservation;

        Room() {
            this.reservation = new ArrayList<>();
        }

        public void addReservation(Reservation resv) {
            this.reservation.add(resv);
        }

        public boolean checkReservation(Reservation compResv) {
            for(Reservation resv : this.reservation) {
                if(resv.compareTo(compResv)) {
                    return true;
                }
            }
            return false;
        }

        public List<Reservation> getReservation() {
            return reservation;
        }

        public void setReservation(List<Reservation> reservation) {
            this.reservation = reservation;
        }
    }

    class Reservation {
        private int startTime;
        private int endTime;

        Reservation(int startDate, int endDate) {
            this.startTime = startDate;
            this.endTime = endDate;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public boolean compareTo(Reservation resv) {
            if((this.getStartTime() >= resv.getStartTime() && this.getStartTime() >= resv.getEndTime()) ||
                    (this.getEndTime() <= resv.getStartTime() && this.getEndTime() <= resv.getEndTime()))
                return true;
            return false;
        }


        @Override
        public String toString() {
            return "Reservation{" +
                    "startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    '}';
        }
    }
}
