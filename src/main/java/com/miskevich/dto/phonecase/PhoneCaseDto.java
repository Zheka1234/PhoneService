package com.miskevich.dto.phonecase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.miskevich.constant.PhoneCaseConst.PHONE_CASE_EXAMPLE;
import static com.miskevich.constant.SchemaConst.INT_1;
import static com.miskevich.constant.SchemaConst.INT_2;
import static com.miskevich.constant.SchemaConst.INT_20;
import static com.miskevich.constant.SchemaConst.STRING_1;

@Getter
@Setter
@Schema(description = "Update phone case object")
public class PhoneCaseDto {

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long id;

    @Schema(example = PHONE_CASE_EXAMPLE, minLength = INT_2, maxLength = INT_20)
    @Size(min = INT_2, max = INT_20)
    @NotNull
    private String name;
}
