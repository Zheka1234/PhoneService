package com.miskevich.mapper;

import com.miskevich.dto.phone.PhoneDto;
import com.miskevich.dto.phone.PhoneSaveDto;
import com.miskevich.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    @Mapping(source = "phoneCaseModelId", target = "phoneCaseModels.id")
    Phone toPhone(PhoneDto phoneDto);

    @Mapping(source = "phoneCaseModelId", target = "phoneCaseModels.id")
    Phone toPhone(PhoneSaveDto phoneSaveDto);

    @Mapping(source = "phoneCaseModels.id", target = "phoneCaseModelId")
    PhoneDto toPhoneDto(Phone phone);

    List<PhoneDto> toPhoneDtoList(List<Phone> phoneList);
}
