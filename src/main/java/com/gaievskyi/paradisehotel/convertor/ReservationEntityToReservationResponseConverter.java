package com.gaievskyi.paradisehotel.convertor;

import com.gaievskyi.paradisehotel.entity.ReservationEntity;
import com.gaievskyi.paradisehotel.model.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(ReservationEntity source){
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(source.getCheckin());
        reservationResponse.setCheckout(source.getCheckout());

        if(source.getRoomEntity() != null) {
            reservationResponse.setId(source.getRoomEntity().getId());
            reservationResponse.setRoomNumber(source.getRoomEntity().getRoomNumber());
        }

        if(source.getClientEntity() != null) {
            reservationResponse.setClientFirstName(source.getClientEntity().getFirstName());
            reservationResponse.setClientLastName(source.getClientEntity().getLastName());
            reservationResponse.setClientEmail(source.getClientEntity().getEmail());
            reservationResponse.setClientPhone(source.getClientEntity().getPhone());
        }
        return reservationResponse;
    }
}
