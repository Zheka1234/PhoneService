package com.miskevich.service.model;

import com.miskevich.dto.model.ModelDto;
import com.miskevich.dto.model.ModelSaveDto;
import com.miskevich.entity.Model;
import com.miskevich.exception.EntityIsUsedException;
import com.miskevich.exception.EntityNotFoundException;
import com.miskevich.mapper.ModelMapper;
import com.miskevich.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.miskevich.constant.ModelConst.MODEL_ALREADY_EXIST;
import static com.miskevich.constant.SchemaConst.STRING;

@Service
@Transactional
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Model findById(Long id) {
        return modelRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public Model create(ModelSaveDto modelDto) {
        Model model = modelRepository.findByModelName(modelDto.getModelName());
        if (Objects.nonNull(model)) {
            throw new EntityIsUsedException(String.join(MODEL_ALREADY_EXIST, STRING, model.toString()));
        }
        Model newModel = modelMapper.toModel(modelDto);
        return modelRepository.save(newModel);
    }

    @Override
    public void delete(Long id) {
        if (modelRepository.findById(id).isPresent()) {
            modelRepository.deleteById(id);
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public Model update(ModelDto modelDto) {
        Model model = modelRepository.findByModelName(modelDto.getModelName());
        if (Objects.nonNull(model)) {
            throw new EntityIsUsedException(String.join(MODEL_ALREADY_EXIST, STRING, model.toString()));
        }
        Optional<Model> modelById = modelRepository.findById(modelDto.getId());
        if (modelById.isPresent()) {
            Model newModel = modelMapper.toModel(modelDto);
            return modelRepository.save(newModel);
        }
        throw new EntityNotFoundException(modelDto.getId());
    }
}
