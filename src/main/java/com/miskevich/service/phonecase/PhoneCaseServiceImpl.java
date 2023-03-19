package com.miskevich.service.phonecase;

import com.miskevich.dto.phonecase.PhoneCaseDto;
import com.miskevich.dto.phonecase.PhoneCaseSaveDto;
import com.miskevich.entity.PhoneCase;
import com.miskevich.exception.EntityIsUsedException;
import com.miskevich.exception.EntityNotFoundException;
import com.miskevich.mapper.PhoneCaseMapper;
import com.miskevich.repository.PhoneCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_ALREADY_EXIST;
import static com.miskevich.constant.SchemaConst.STRING;

@Service
@Transactional
@RequiredArgsConstructor
public class PhoneCaseServiceImpl implements PhoneCaseService {

    private final PhoneCaseRepository phoneCaseRepository;

    private final PhoneCaseMapper phoneCaseMapper;


    @Override
    @Transactional(readOnly = true)
    public List<PhoneCase> findAll() {
        return phoneCaseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PhoneCase findById(Long id) {
        return phoneCaseRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public PhoneCase create(PhoneCaseSaveDto phoneCaseSaveDto) {
        PhoneCase phoneCase = phoneCaseRepository.findByName(phoneCaseSaveDto.getName());
        if (Objects.nonNull(phoneCase)) {
            throw new EntityIsUsedException(String.join(PHONE_CASE_ALREADY_EXIST, STRING, phoneCase.toString()));
        }
        PhoneCase phoneCase1 = phoneCaseMapper.toPhoneCase(phoneCaseSaveDto);
        return phoneCaseRepository.save(phoneCase1);
    }

    @Override
    public void delete(Long id) {
        if (phoneCaseRepository.findById(id).isPresent()) {
            phoneCaseRepository.deleteById(id);
        }
        throw new EntityNotFoundException(id);

    }

    @Override
    public PhoneCase update(PhoneCaseDto phoneCaseDto) {
        PhoneCase phoneCase = phoneCaseRepository.findByName(phoneCaseDto.getName());
        if (Objects.nonNull(phoneCase)) {
            throw new EntityIsUsedException(String.join(PHONE_CASE_ALREADY_EXIST, STRING, phoneCase.toString()));
        }
        Optional<PhoneCase> carCaseById = phoneCaseRepository.findById(phoneCaseDto.getId());
        if (carCaseById.isPresent()) {
            PhoneCase newCarCase = phoneCaseMapper.toPhoneCase(phoneCaseDto);
            return phoneCaseRepository.save(newCarCase);
        }
        throw new EntityNotFoundException(phoneCaseDto.getId());
    }
}
