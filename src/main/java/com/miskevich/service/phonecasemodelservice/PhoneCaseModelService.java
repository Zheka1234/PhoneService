package com.miskevich.service.phonecasemodelservice;

import com.miskevich.dto.phonecasemodeldto.PhoneCaseModelDto;
import com.miskevich.dto.phonecasemodeldto.PhoneCaseModelSaveDto;
import com.miskevich.entity.PhoneCaseModel;

import java.util.List;

public interface PhoneCaseModelService {

    List<PhoneCaseModel> findAll();

    PhoneCaseModel findById(Long id);

    PhoneCaseModel create(PhoneCaseModelSaveDto phoneCaseModelDto);

    void delete(Long id);

    PhoneCaseModel update(PhoneCaseModelDto phoneCaseModelDto);
}
