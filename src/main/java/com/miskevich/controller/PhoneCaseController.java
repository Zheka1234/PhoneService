package com.miskevich.controller;


import com.miskevich.dto.phonecase.PhoneCaseDto;
import com.miskevich.dto.phonecase.PhoneCaseSaveDto;
import com.miskevich.entity.PhoneCase;
import com.miskevich.mapper.PhoneCaseMapper;
import com.miskevich.service.phonecase.PhoneCaseService;
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

import static com.miskevich.constant.PhoneCaseConst.CREATE_NEW_PHONE_CASE;
import static com.miskevich.constant.PhoneCaseConst.DELETE_PHONE_CASE;
import static com.miskevich.constant.PhoneCaseConst.FINDS_ALL_PHONE_CASE;
import static com.miskevich.constant.PhoneCaseConst.FIND_ALL_PHONE_CASE;
import static com.miskevich.constant.PhoneCaseConst.FIND_PHONE_CASE_BY_ID;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_CREATE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_DELETE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_FOUND;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_NOT_CREATED_CONFLICT;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_NOT_CREATED_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_NOT_DELETED_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_NOT_FOUND;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_NOT_UPDATE_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_UPDATE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneCaseConst.UPDATE_PHONE_CASE;
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
@RequestMapping("/phone_case")
@Tag(name = "Phone case controller")
public class PhoneCaseController {

    private final PhoneCaseService phoneCaseService;
    private final PhoneCaseMapper phoneCaseMapper;

    @Operation(summary = FIND_ALL_PHONE_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FINDS_ALL_PHONE_CASE,
                    content = @Content(schema = @Schema(implementation = PhoneCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneCaseDto>> findAll() {
        return new ResponseEntity<>(phoneCaseMapper.toPhoneCaseDtoList(phoneCaseService.findAll()), HttpStatus.OK);
    }

    @Operation(summary = FIND_PHONE_CASE_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_CASE_FOUND,
                    content = @Content(schema = @Schema(implementation = PhoneCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_CASE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneCaseDto> findById(@PathVariable Long id) {
        PhoneCase phoneCaseById = phoneCaseService.findById(id);
        return new ResponseEntity<>(phoneCaseMapper.toPhoneCaseDto(phoneCaseById), HttpStatus.OK);
    }

    @Operation(summary = DELETE_PHONE_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_CASE_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = PhoneCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_CASE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        phoneCaseService.delete(id);
        return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
    }

    @Operation(summary = CREATE_NEW_PHONE_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = PHONE_CASE_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = PhoneCaseSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_CASE_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneCaseDto> createBodyType(@Valid @RequestBody PhoneCaseSaveDto phoneCaseDto) {
        PhoneCase phoneCasePost = phoneCaseService.create(phoneCaseDto);
        return new ResponseEntity<>(phoneCaseMapper.toPhoneCaseDto(phoneCasePost), HttpStatus.CREATED);
    }

    @Operation(summary = UPDATE_PHONE_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_CASE_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = PhoneCase.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_CASE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneCaseDto> updateBodyType(@Valid @RequestBody PhoneCaseDto phoneCaseDto) {
        PhoneCase updatePhoneCase = phoneCaseService.update(phoneCaseDto);
        return new ResponseEntity<>(phoneCaseMapper.toPhoneCaseDto(updatePhoneCase), HttpStatus.OK);
    }
}
