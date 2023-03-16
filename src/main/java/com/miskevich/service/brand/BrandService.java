package com.miskevich.service.brand;

import com.miskevich.dto.brand.BrandDto;
import com.miskevich.dto.brand.BrandSaveDto;
import com.miskevich.entity.Brand;

import java.util.List;

public interface BrandService {


    List<Brand> findAll();


    Brand findById(Long id);


    Brand create(BrandSaveDto brandDto);


    void delete(Long id);


    Brand update(BrandDto brandDto);

}