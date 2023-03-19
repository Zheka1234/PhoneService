package com.miskevich.repository;

import com.miskevich.entity.PhoneCaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCaseModelRepository extends JpaRepository<PhoneCaseModel, Long> {

    PhoneCaseModel findByPhoneCaseIdAndModelId(Long idPhoneCase, Long idModel);
}
