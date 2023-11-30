package com.globallogic.test.repository;

import com.globallogic.test.model.PhoneModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneModel,UUID> {
    PhoneModel findAllByid(UUID id);
}
