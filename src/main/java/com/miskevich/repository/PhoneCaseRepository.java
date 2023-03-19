package com.miskevich.repository;

import com.miskevich.entity.PhoneCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCaseRepository extends JpaRepository<PhoneCase, Long> {

    PhoneCase findByName(String name);
}
