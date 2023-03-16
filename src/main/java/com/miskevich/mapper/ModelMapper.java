package com.miskevich.mapper;

import com.miskevich.dto.model.ModelDto;
import com.miskevich.dto.model.ModelSaveDto;
import com.miskevich.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(source = "brandId", target = "brand.id")
    Model toModel(ModelDto modelDto);

    @Mapping(source = "brandId", target = "brand.id")
    Model toModel(ModelSaveDto modelSaveDto);

    @Mapping(source = "brand.id", target = "brandId")
    ModelDto toModelDto(Model model);

    List<ModelDto> toModelDtoList(List<Model> modelList);

}
