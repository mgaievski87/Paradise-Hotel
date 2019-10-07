package com.gaievskyi.paradisehotel.convertor;

import com.gaievskyi.paradisehotel.entity.ReservationEntity;
import com.gaievskyi.paradisehotel.entity.RoomEntity;
import com.gaievskyi.paradisehotel.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Filters {
    public static List<RoomEntity> getAvailableRoomList (RoomRepository roomRepository, LocalDate checkin, LocalDate checkout){
        Iterable<RoomEntity> roomEntityList = roomRepository.findAll();
        Stream<RoomEntity> roomEntityStream = StreamSupport.stream(roomEntityList.spliterator(),false);
        List<RoomEntity> filteredRoomEntityList = roomEntityStream.filter(roomEntity -> {
            for (ReservationEntity reservationEntity : roomEntity.getReservationEntityList()) {
                if(!checkin.isBefore(reservationEntity.getCheckin()) && checkin.isBefore(reservationEntity.getCheckout())
                        || checkout.isAfter(reservationEntity.getCheckin()) && !checkout.isAfter(reservationEntity.getCheckout())
                        || checkin.isBefore(reservationEntity.getCheckin()) && checkout.isAfter(reservationEntity.getCheckout()))
                    return false;
            }
            return true;
        }).collect(Collectors.toList());
        return filteredRoomEntityList;
    }
}
