package com.miskevich.dto.phonecase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_EXAMPLE;
import static com.miskevich.constant.SchemaConst.INT_2;
import static com.miskevich.constant.SchemaConst.INT_20;

@Getter
@Setter
@Schema(description = "Create phone case object")
public class PhoneCaseSaveDto {

    @Schema(example = PHONE_CASE_EXAMPLE, minLength = INT_2, maxLength = INT_20)
    @Size(min = INT_2, max = INT_20)
    @NotNull
    private String name;
}
