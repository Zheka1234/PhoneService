package com.miskevich.service.phone;

import com.miskevich.dto.phone.PhoneDto;
import com.miskevich.dto.phone.PhoneSaveDto;
import com.miskevich.entity.Phone;

import java.util.List;

public interface PhoneService {

    List<Phone> findAll();


    Phone findById(Long id);


    Phone create(PhoneSaveDto phoneDto);


    void delete(Long id);


    Phone update(PhoneDto phoneDto);
}
