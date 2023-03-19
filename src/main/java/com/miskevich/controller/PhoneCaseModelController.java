package com.miskevich.controller;

import com.miskevich.dto.phonecasemodeldto.PhoneCaseModelDto;
import com.miskevich.dto.phonecasemodeldto.PhoneCaseModelSaveDto;
import com.miskevich.entity.PhoneCaseModel;
import com.miskevich.mapper.PhoneCaseModelMapper;
import com.miskevich.service.phonecasemodelservice.PhoneCaseModelService;
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

import static com.miskevich.constant.PhoneCaseModelConst.CREATE_NEW_PHONE_CASE_MODEL;
import static com.miskevich.constant.PhoneCaseModelConst.DELETE_PHONE_CASE_MODEL;
import static com.miskevich.constant.PhoneCaseModelConst.FIND_ALL_PHONE_CASE_MODEL;
import static com.miskevich.constant.PhoneCaseModelConst.FIND_PHONE_CASE_MODEL_BY_ID;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_CREATE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_DELETE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_FOUND;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_NOT_CREATED_CONFLICT;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_NOT_CREATED_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_NOT_DELETED_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_NOT_FOUND;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_NOT_UPDATE_ILLEGAL_ARGUMENTS;
import static com.miskevich.constant.PhoneCaseModelConst.PHONE_CASE_MODEL_UPDATE_SUCCESSFULLY;
import static com.miskevich.constant.PhoneCaseModelConst.UPDATE_PHONE_CASE_MODEL;
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
@RequestMapping("/phone_case_model")
@Tag(name = "Phone case model controller")
public class PhoneCaseModelController {

    private final PhoneCaseModelService phoneCaseModelService;

    private final PhoneCaseModelMapper phoneCaseModelMapper;


    @Operation(summary = FIND_ALL_PHONE_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_PHONE_CASE_MODEL,
                    content = @Content(schema = @Schema(implementation = PhoneCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_MODEL_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneCaseModelDto>> findAll() {
        List<PhoneCaseModel> all = phoneCaseModelService.findAll();
        return new ResponseEntity<>(phoneCaseModelMapper.toPhoneCaseModelDtoList(all), HttpStatus.OK);
    }


    @Operation(summary = FIND_PHONE_CASE_MODEL_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_CASE_MODEL_FOUND,
                    content = @Content(schema = @Schema(implementation = PhoneCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_CASE_MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_MODEL_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneCaseModelDto> findById(@PathVariable Long id) {
        PhoneCaseModel phoneCaseModelById = phoneCaseModelService.findById(id);
        return new ResponseEntity<>(phoneCaseModelMapper.toPhoneCaseModelDto(phoneCaseModelById), HttpStatus.OK);
    }

    @Operation(summary = DELETE_PHONE_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_CASE_MODEL_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = PhoneCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_CASE_MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_MODEL_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        phoneCaseModelService.delete(id);
        return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
    }

    @Operation(summary = CREATE_NEW_PHONE_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = PHONE_CASE_MODEL_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = PhoneCaseModelSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_CASE_MODEL_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_MODEL_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneCaseModelDto> createPhoneCaseModel(@Valid @RequestBody PhoneCaseModelSaveDto phoneCaseModelDto) {
        PhoneCaseModel phoneCaseModelCreate = phoneCaseModelService.create(phoneCaseModelDto);
        return new ResponseEntity<>(phoneCaseModelMapper.toPhoneCaseModelDto(phoneCaseModelCreate), HttpStatus.CREATED);
    }

    @Operation(summary = UPDATE_PHONE_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = PHONE_CASE_MODEL_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = PhoneCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = PHONE_CASE_MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = PHONE_CASE_MODEL_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneCaseModelDto> updatePhoneCaseModel(@Valid @RequestBody PhoneCaseModelDto phoneCaseModelDto) {
        PhoneCaseModel phoneCaseModelUpdate = phoneCaseModelService.update(phoneCaseModelDto);
        return new ResponseEntity<>(phoneCaseModelMapper.toPhoneCaseModelDto(phoneCaseModelUpdate), HttpStatus.OK);
    }
}
