package com.miskevich.mapper;

import com.miskevich.dto.phonecase.PhoneCaseDto;
import com.miskevich.dto.phonecase.PhoneCaseSaveDto;
import com.miskevich.entity.Phone;
import com.miskevich.entity.PhoneCase;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneCaseMapper {

    PhoneCase toPhoneCase(PhoneCaseDto phoneCaseDto);

    PhoneCase toPhoneCase(PhoneCaseSaveDto phoneCaseSaveDto);

    PhoneCaseDto toPhoneCaseDto(PhoneCase phoneCase);

    List<PhoneCaseDto> toPhoneCaseDtoList(List<PhoneCase> list);
}
