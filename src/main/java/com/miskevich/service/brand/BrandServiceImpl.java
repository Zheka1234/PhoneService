package com.miskevich.service.brand;

import com.miskevich.dto.brand.BrandDto;
import com.miskevich.dto.brand.BrandSaveDto;
import com.miskevich.entity.Brand;
import com.miskevich.exception.EntityIsUsedException;
import com.miskevich.exception.EntityNotFoundException;
import com.miskevich.mapper.BrandMapper;
import com.miskevich.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.miskevich.constant.BrandConst.BRAND_ALREADY_EXIST;
import static com.miskevich.constant.SchemaConst.STRING;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public Brand create(BrandSaveDto brandDto) {
        Brand brand = brandRepository.findByBrandName(brandDto.getBrandName());
        if (Objects.nonNull(brand)) {
            throw new EntityIsUsedException(String.join(BRAND_ALREADY_EXIST, STRING, brand.toString()));
        }
        Brand newBrand = brandMapper.toBrand(brandDto);
        return brandRepository.save(newBrand);
    }

    @Override
    public void delete(Long id) {
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public Brand update(BrandDto brandDto) {
        Brand brand = brandRepository.findByBrandName(brandDto.getBrandName());
        if (Objects.nonNull(brand)) {
            throw new EntityIsUsedException(String.join(BRAND_ALREADY_EXIST, STRING, brand.toString()));
        }
        Optional<Brand> brandById = brandRepository.findById(brandDto.getId());
        if (brandById.isPresent()) {
            Brand newBrand = brandMapper.toBrand(brandDto);
            return brandRepository.save(newBrand);
        }
        throw new EntityNotFoundException(brandDto.getId());
    }

}
