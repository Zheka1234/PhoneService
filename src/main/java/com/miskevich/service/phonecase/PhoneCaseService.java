package com.miskevich.service.phonecase;


import com.miskevich.dto.phonecase.PhoneCaseDto;
import com.miskevich.dto.phonecase.PhoneCaseSaveDto;
import com.miskevich.entity.PhoneCase;

import java.util.List;

public interface PhoneCaseService {


    List<PhoneCase> findAll();


    PhoneCase findById(Long id);


    PhoneCase create(PhoneCaseSaveDto phoneCaseSaveDto);


    void delete(Long id);


    PhoneCase update(PhoneCaseDto phoneCaseDto);
}
