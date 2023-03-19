package com.miskevich.mapper;

import com.miskevich.dto.phonecasemodeldto.PhoneCaseModelDto;
import com.miskevich.dto.phonecasemodeldto.PhoneCaseModelSaveDto;
import com.miskevich.entity.PhoneCaseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneCaseModelMapper {

    @Mapping(source = "phoneCaseId", target = "phoneCase.id")
    @Mapping(source = "modelId", target = "model.id")
    PhoneCaseModel toPhoneCaseModel(PhoneCaseModelDto phoneCaseModelDto);

    @Mapping(source = "phoneCaseId", target = "phoneCase.id")
    @Mapping(source = "modelId", target = "model.id")
    PhoneCaseModel toPhoneCaseModel(PhoneCaseModelSaveDto phoneCaseModelSaveDto);

    @Mapping(source = "phoneCase.id", target = "phoneCaseId")
    @Mapping(source = "model.id", target = "modelId")
    PhoneCaseModelDto toPhoneCaseModelDto(PhoneCaseModel phoneCaseModel);

    List<PhoneCaseModelDto> toPhoneCaseModelDtoList(List<PhoneCaseModel> phoneCaseModelList);
}
