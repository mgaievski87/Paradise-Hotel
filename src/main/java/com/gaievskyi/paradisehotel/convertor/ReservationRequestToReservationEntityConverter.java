package com.gaievskyi.paradisehotel.convertor;

import com.gaievskyi.paradisehotel.entity.ReservationEntity;
import com.gaievskyi.paradisehotel.model.request.ReservationRequest;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest, ReservationEntity>{
    @Override
    public ReservationEntity convert(ReservationRequest source) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(source.getCheckin());
        reservationEntity.setCheckout(source.getCheckout());
        reservationEntity.setRoom_id(source.getRoomId());

        /*if(source.getRoomId() != null)
            reservationEntity.setId(source.getRoomId());*/

        return reservationEntity;
    }
}
