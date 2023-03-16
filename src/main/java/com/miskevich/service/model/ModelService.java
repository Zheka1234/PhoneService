package com.miskevich.service.model;

import com.miskevich.dto.model.ModelDto;
import com.miskevich.dto.model.ModelSaveDto;
import com.miskevich.entity.Model;

import java.util.List;

public interface ModelService {


    List<Model> findAll();


    Model findById(Long id);


    Model create(ModelSaveDto modelDto);


    void delete(Long id);


    Model update(ModelDto modelDto);
}
