package com.gaievskyi.paradisehotel;

import com.gaievskyi.paradisehotel.entity.RoomEntity;
import com.gaievskyi.paradisehotel.entity.RoomType;
import com.gaievskyi.paradisehotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class H2Bootstrap implements CommandLineRunner {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Bootstrapping data: ");

        roomRepository.save(new RoomEntity(100, "150", RoomType.SINGLE));
        roomRepository.save(new RoomEntity(101, "300", RoomType.PRESIDENTIAL));
        roomRepository.save(new RoomEntity(102, "180", RoomType.DOUBLE));
        roomRepository.save(new RoomEntity(103, "200", RoomType.TWIN));
        roomRepository.save(new RoomEntity(104, "250", RoomType.FAMILY));
        roomRepository.save(new RoomEntity(105, "200", RoomType.TWIN));
        roomRepository.save(new RoomEntity(106, "150", RoomType.SINGLE));
        roomRepository.save(new RoomEntity(107, "180", RoomType.DOUBLE));
        roomRepository.save(new RoomEntity(108, "180", RoomType.DOUBLE));
        roomRepository.save(new RoomEntity(109, "180", RoomType.DOUBLE));
        roomRepository.save(new RoomEntity(110, "150", RoomType.SINGLE));
        roomRepository.save(new RoomEntity(111, "300", RoomType.PRESIDENTIAL));
        roomRepository.save(new RoomEntity(112, "250", RoomType.FAMILY));
        roomRepository.save(new RoomEntity(113, "250", RoomType.FAMILY));
        roomRepository.save(new RoomEntity(114, "250", RoomType.FAMILY));
        roomRepository.save(new RoomEntity(115, "150", RoomType.SINGLE));
        roomRepository.save(new RoomEntity(116, "180", RoomType.DOUBLE));
        roomRepository.save(new RoomEntity(117, "180", RoomType.DOUBLE));
        roomRepository.save(new RoomEntity(118, "200", RoomType.TWIN));
        roomRepository.save(new RoomEntity(119, "200", RoomType.TWIN));

        Iterable<RoomEntity> itr = roomRepository.findAll();

        System.out.println("Printing out data: ");

        for(RoomEntity room: itr){
            System.out.println(room.getRoomNumber());
        }
    }
}
