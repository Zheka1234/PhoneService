package com.miskevich.controller;

import com.miskevich.dto.phone.PhoneDto;
import com.miskevich.dto.phone.PhoneSaveDto;
import com.miskevich.entity.Phone;
import com.miskevich.mapper.PhoneMapper;
import com.miskevich.service.phone.PhoneService;
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

import static com.miskevich.constant.PhoneConst.CREATE_NEW_PHONE;
import static com.miskevich.constant.PhoneConst.DELETE_PHONE;
import static com.miskevich.constant.PhoneConst.FIND_ALL_PHONES;
import static com.miskevich.constant.PhoneConst.FIND_PHONE_BY_ID;
import static com.miskevich.constant.PhoneConst.PHONES_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneConst.PHONE_CREATE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneConst.PHONE_DELETE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneConst.PHONE_FOUND;
import static com.miskevich.constant.PhoneConst.PHONE_NOT_CREATED_CONFLICT;
import static com.miskevich.constant.PhoneConst.PHONE_NOT_CREATED_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneConst.PHONE_NOT_DELETED_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneConst.PHONE_NOT_FOUND;
import static com.miskevich.constant.PhoneConst.PHONE_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneConst.PHONE_NOT_UPDATE_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneConst.PHONE_UPDATE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneConst.UPDATE_PHONE;
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
@RequestMapping("/phone")
@Tag(name = "Phone controller")
public class PhoneController {

    private final PhoneService phoneService;

    private final PhoneMapper phoneMapper;

    @Operation(summary = FIND_ALL_PHONES, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_PHONES,
                    content = @Content(schema = @Schema(implementation = PhoneDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONES_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneDto>> findAll() {
        return new ResponseEntity<>(phoneMapper.toPhoneDtoList(phoneService.findAll()), HttpStatus.OK);
    }

    @Operation(summary = FIND_PHONE_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_FOUND,
                    content = @Content(schema = @Schema(implementation = PhoneDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneDto> findById(@PathVariable Long id) {
        Phone phoneById = phoneService.findById(id);
        return new ResponseEntity<>(phoneMapper.toPhoneDto(phoneById), HttpStatus.OK);
    }

    @Operation(summary = DELETE_PHONE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = PhoneDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        phoneService.delete(id);
        return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
    }

    @Operation(summary = CREATE_NEW_PHONE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = PHONE_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = PhoneSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneDto> createPhone(@Valid @RequestBody PhoneSaveDto phoneDto) {
        Phone phoneCreate = phoneService.create(phoneDto);
        return new ResponseEntity<>(phoneMapper.toPhoneDto(phoneCreate), HttpStatus.CREATED);
    }

    @Operation(summary = UPDATE_PHONE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = Phone.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneDto> updatePhone(@Valid @RequestBody PhoneDto phoneDto) {
        Phone phoneUpdate = phoneService.update(phoneDto);
        return new ResponseEntity<>(phoneMapper.toPhoneDto(phoneUpdate), HttpStatus.OK);
    }
}
