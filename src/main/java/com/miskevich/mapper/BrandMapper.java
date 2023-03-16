package com.miskevich.mapper;

import com.miskevich.dto.brand.BrandDto;
import com.miskevich.dto.brand.BrandSaveDto;
import com.miskevich.entity.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toBrand(BrandSaveDto brandSaveDto);

    Brand toBrand(BrandDto brandDto);

    BrandDto toBrandDto(Brand brand);

    List<BrandDto> toBrandDtoList(List<Brand> list);

}