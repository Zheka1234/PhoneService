package com.miskevich.service.phone;

import com.miskevich.dto.phone.PhoneDto;
import com.miskevich.dto.phone.PhoneSaveDto;
import com.miskevich.entity.Phone;
import com.miskevich.exception.EntityIsUsedException;
import com.miskevich.exception.EntityNotFoundException;
import com.miskevich.mapper.PhoneMapper;
import com.miskevich.repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.miskevich.constant.PhoneConst.PHONE_IS_ALREADY_EXIST;
import static com.miskevich.constant.SchemaConst.STRING;

@Service
@Transactional
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    private final PhoneMapper phoneMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Phone findById(Long id) {
        return phoneRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public Phone create(PhoneSaveDto phoneDto) {
        Phone phone = phoneRepository.findByVinCode(phoneDto.getVinCode());
        if (Objects.nonNull(phone)) {
            throw new EntityIsUsedException(String.join(PHONE_IS_ALREADY_EXIST, STRING, phone.toString()));
        }
        Phone newPhone = phoneMapper.toPhone(phoneDto);
        return phoneRepository.save(newPhone);
    }

    @Override
    public void delete(Long id) {
        if (phoneRepository.findById(id).isPresent()) {
            phoneRepository.deleteById(id);
        }
        throw new EntityNotFoundException(id);

    }

    @Override
    public Phone update(PhoneDto phoneDto) {
        Phone phone = phoneRepository.findByVinCode(phoneDto.getVinCode());
        if (Objects.nonNull(phone)) {
            throw new EntityIsUsedException(String.join(PHONE_IS_ALREADY_EXIST, STRING, phone.toString()));
        }
        Optional<Phone> phoneById = phoneRepository.findById(phoneDto.getId());
        if (phoneById.isPresent()) {
            Phone newPhone = phoneMapper.toPhone(phoneDto);
            return phoneRepository.save(newPhone);
        }
        throw new EntityNotFoundException(phoneDto.getId());
    }
}
