package com.gaievskyi.paradisehotel.repository;

import com.gaievskyi.paradisehotel.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
}
