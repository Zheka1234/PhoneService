package com.miskevich.repository;

import com.miskevich.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Phone findByVinCode(String vinCode);
}
