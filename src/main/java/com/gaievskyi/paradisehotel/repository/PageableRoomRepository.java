package com.gaievskyi.paradisehotel.repository;

import com.gaievskyi.paradisehotel.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PageableRoomRepository extends PagingAndSortingRepository<RoomEntity, Long> {

    Page<RoomEntity> findById(Long id, Pageable page);

    //:checkin < resE.checkin AND :checkout < resE.checkin OR :checkin > resE.checkout AND :checkout > resE.checkout"

    /*@Query("select distinct re from RoomEntity re left join re.reservationEntityList list where :checkin <> list.checkin")
    Page<RoomEntity> findAvailableOnly(
            @Param("checkin") LocalDate checkin,
            @Param("checkout") LocalDate checkout,
            Pageable page);*/

}
