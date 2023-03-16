package com.miskevich.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.miskevich.constant.ModelConst.MODEL_EXAMPLE;
import static com.miskevich.constant.SchemaConst.INT_1;
import static com.miskevich.constant.SchemaConst.INT_2;
import static com.miskevich.constant.SchemaConst.INT_20;
import static com.miskevich.constant.SchemaConst.STRING_1;

@Getter
@Setter
@Schema(description = "Update model object")
public class ModelDto {

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long id;

    @Schema(example = MODEL_EXAMPLE, minLength = INT_1, maxLength = INT_20)
    @Size(min = INT_2, max = INT_20)
    @NotNull
    private String modelName;

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long brandId;

}