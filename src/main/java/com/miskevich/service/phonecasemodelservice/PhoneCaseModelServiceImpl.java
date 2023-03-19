package com.miskevich.service.phonecasemodelservice;

import com.miskevich.dto.phonecasemodeldto.PhoneCaseModelDto;
import com.miskevich.dto.phonecasemodeldto.PhoneCaseModelSaveDto;
import com.miskevich.entity.PhoneCaseModel;
import com.miskevich.exception.EntityIsUsedException;
import com.miskevich.exception.EntityNotFoundException;
import com.miskevich.mapper.PhoneCaseModelMapper;
import com.miskevich.repository.PhoneCaseModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_ALREADY_EXIST;
import static com.miskevich.constant.SchemaConst.STRING;

@Service
@Transactional
@RequiredArgsConstructor
public class PhoneCaseModelServiceImpl implements PhoneCaseModelService {

    private final PhoneCaseModelRepository phoneCaseModelRepository;

    private final PhoneCaseModelMapper phoneCaseModelMapper;


    @Override
    @Transactional(readOnly = true)
    public List<PhoneCaseModel> findAll() {
        return phoneCaseModelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PhoneCaseModel findById(Long id) {
        return phoneCaseModelRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public PhoneCaseModel create(PhoneCaseModelSaveDto phoneCaseModelDto) {
        PhoneCaseModel phoneCaseModel = phoneCaseModelRepository.findByPhoneCaseIdAndModelId(
                (phoneCaseModelDto.getPhoneCaseId()), phoneCaseModelDto.getModelId());
        if (Objects.nonNull(phoneCaseModel)) {
            throw new EntityIsUsedException(String.join(PHONE_CASE_MODEL_ALREADY_EXIST, STRING, phoneCaseModel.toString()));
        }
        PhoneCaseModel newPhoneCaseModel = phoneCaseModelMapper.toPhoneCaseModel(phoneCaseModelDto);
        return phoneCaseModelRepository.save(newPhoneCaseModel);
    }

    @Override
    public void delete(Long id) {
        if (phoneCaseModelRepository.findById(id).isPresent()) {
            phoneCaseModelRepository.deleteById(id);
        }
        throw new EntityNotFoundException(id);

    }

    @Override
    public PhoneCaseModel update(PhoneCaseModelDto phoneCaseModelDto) {
        PhoneCaseModel phoneCaseModel = phoneCaseModelRepository.findByPhoneCaseIdAndModelId(
                (phoneCaseModelDto.getPhoneCaseId()), phoneCaseModelDto.getModelId());
        if (Objects.nonNull(phoneCaseModel)) {
            throw new EntityIsUsedException(String.join(PHONE_CASE_MODEL_ALREADY_EXIST, STRING, phoneCaseModel.toString()));
        }
        Optional<PhoneCaseModel> phoneCaseModelById = phoneCaseModelRepository.findById(phoneCaseModelDto.getId());
        if (phoneCaseModelById.isPresent()) {
            PhoneCaseModel newPhoneCaseModel = phoneCaseModelMapper.toPhoneCaseModel(phoneCaseModelDto);
            return phoneCaseModelRepository.save(newPhoneCaseModel);
        }
        throw new EntityNotFoundException(phoneCaseModelDto.getId());
    }
}
