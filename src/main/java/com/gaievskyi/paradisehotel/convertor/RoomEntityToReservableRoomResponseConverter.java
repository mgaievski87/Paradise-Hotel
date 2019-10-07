package com.gaievskyi.paradisehotel.convertor;

import com.gaievskyi.paradisehotel.entity.ReservationEntity;
import com.gaievskyi.paradisehotel.entity.RoomEntity;
import com.gaievskyi.paradisehotel.model.Links;
import com.gaievskyi.paradisehotel.model.Self;
import com.gaievskyi.paradisehotel.model.response.ReservableRoomResponse;
import com.gaievskyi.paradisehotel.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse> {
    private LocalDate checkin;
    private LocalDate checkout;

    public RoomEntityToReservableRoomResponseConverter() {
    }

    public RoomEntityToReservableRoomResponseConverter(LocalDate checkin, LocalDate checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public ReservableRoomResponse convert(RoomEntity source) {

        ReservableRoomResponse reservableRoomResponse = new ReservableRoomResponse();
        reservableRoomResponse.setRoomNumber(source.getRoomNumber());
        reservableRoomResponse.setPrice(Integer.valueOf(source.getPrice()));
        reservableRoomResponse.setRoomType(source.getRoomType());
        reservableRoomResponse.setDescription(source.getRoomType().getDescription());
        reservableRoomResponse.setImgURL(source.getRoomType().getImgURL());

        Links links = new Links();
        Self self= new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
        links.setSelf(self);
        reservableRoomResponse.setLinks(links);

        reservableRoomResponse.setId(source.getId());

        reservableRoomResponse.setAvailable(true);
        //if empty constructor was called,
        // then 'isAvailable' field will be set to 'true' for all of the rooms
        if(this.checkin != null && this.checkout != null) {
            for (ReservationEntity reservationEntity : source.getReservationEntityList()) {
                if(!this.checkin.isBefore(reservationEntity.getCheckin()) && this.checkin.isBefore(reservationEntity.getCheckout())
                        || this.checkout.isAfter(reservationEntity.getCheckin()) && !this.checkout.isAfter(reservationEntity.getCheckout())
                        || this.checkin.isBefore(reservationEntity.getCheckin()) && this.checkout.isAfter(reservationEntity.getCheckout()))
                    reservableRoomResponse.setAvailable(false);
            }
        }
        return reservableRoomResponse;
    }
}
