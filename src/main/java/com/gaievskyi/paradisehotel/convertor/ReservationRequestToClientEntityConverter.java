package com.gaievskyi.paradisehotel.convertor;

import com.gaievskyi.paradisehotel.entity.ClientEntity;
import com.gaievskyi.paradisehotel.model.request.ReservationRequest;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToClientEntityConverter implements Converter<ReservationRequest, ClientEntity> {
    @Override
    public ClientEntity convert(ReservationRequest source) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName(source.getClientFirstName());
        clientEntity.setLastName(source.getClientLastName());
        clientEntity.setEmail(source.getClientEmail());
        clientEntity.setPhone(source.getClientPhone());
        return clientEntity;
    }
}
