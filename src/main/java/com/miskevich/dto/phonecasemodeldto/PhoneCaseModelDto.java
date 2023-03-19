package com.miskevich.dto.phonecasemodeldto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static com.miskevich.constant.SchemaConst.INT_1;
import static com.miskevich.constant.SchemaConst.STRING_1;

@Getter
@Setter
@Schema(description = "Update phone case-model object")
public class PhoneCaseModelDto {

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long id;

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long phoneCaseId;

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long modelId;
}
