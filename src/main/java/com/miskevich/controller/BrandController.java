package com.miskevich.controller;

import com.miskevich.dto.brand.BrandDto;
import com.miskevich.dto.brand.BrandSaveDto;
import com.miskevich.entity.Brand;
import com.miskevich.mapper.BrandMapper;
import com.miskevich.service.brand.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.miskevich.constant.BrandConst.BRANDS_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.BrandConst.BRAND_CREATE_SUCCESSFULLY;
import static com.miskevich.constant.BrandConst.BRAND_DELETE_SUCCESSFULLY;
import static com.miskevich.constant.BrandConst.BRAND_FOUND;
import static com.miskevich.constant.BrandConst.BRAND_NOT_CREATED_CONFLICT;
import static com.miskevich.constant.BrandConst.BRAND_NOT_CREATED_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.BrandConst.BRAND_NOT_DELETED_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.BrandConst.BRAND_NOT_FOUND;
import static com.miskevich.constant.BrandConst.BRAND_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.BrandConst.BRAND_NOT_UPDATE_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.BrandConst.BRAND_UPDATE_SUCCESSFULLY;
import static com.miskevich.constant.BrandConst.CREATE_NEW_BRAND;
import static com.miskevich.constant.BrandConst.DELETE_BRAND;
import static com.miskevich.constant.BrandConst.FIND_ALL_BRANDS;
import static com.miskevich.constant.BrandConst.FIND_BRAND_BY_ID;
import static com.miskevich.constant.BrandConst.UPDATE_BRAND;
import static com.miskevich.constant.StatusConst.BAD_REQUEST;
import static com.miskevich.constant.StatusConst.DELETED_SUCCESSFUL;
import static com.miskevich.constant.StatusConst.RESPONSE_CODE_200;
import static com.miskevich.constant.StatusConst.RESPONSE_CODE_201;
import static com.miskevich.constant.StatusConst.RESPONSE_CODE_400;
import static com.miskevich.constant.StatusConst.RESPONSE_CODE_404;
import static com.miskevich.constant.StatusConst.RESPONSE_CODE_500;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
@Tag(name = "Brand controller")
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @Operation(summary = FIND_ALL_BRANDS, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_BRANDS,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRANDS_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BrandDto>> findAll() {
        return new ResponseEntity<>(brandMapper.toBrandDtoList(brandService.findAll()), HttpStatus.OK);
    }

    @Operation(summary = FIND_BRAND_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_FOUND,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDto> findById(@PathVariable Long id) {
        Brand brandById = brandService.findById(id);
        return new ResponseEntity<>(brandMapper.toBrandDto(brandById), HttpStatus.OK);
    }

    @Operation(summary = DELETE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        brandService.delete(id);
        return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
    }

    @Operation(summary = CREATE_NEW_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = BRAND_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody BrandSaveDto brandDto) {
        Brand createBrand = brandService.create(brandDto);
        return new ResponseEntity<>(brandMapper.toBrandDto(createBrand), HttpStatus.CREATED);
    }

    @Operation(summary = UPDATE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDto> updateBrand(@Valid @RequestBody BrandDto brandDto) {
        Brand updateBrand = brandService.update(brandDto);
        return new ResponseEntity<>(brandMapper.toBrandDto(updateBrand), HttpStatus.OK);
    }

}