package com.miskevich.dto.phone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.miskevich.constant.PhoneConst.COLOR;
import static com.miskevich.constant.PhoneConst.INT_30;
import static com.miskevich.constant.PhoneConst.VIN_CODE_EXAMPLE;
import static com.miskevich.constant.SchemaConst.INT_1;
import static com.miskevich.constant.SchemaConst.INT_2;
import static com.miskevich.constant.SchemaConst.INT_20;
import static com.miskevich.constant.SchemaConst.STRING_1;

@Getter
@Setter
@Schema(description = "Update phone object")
public class PhoneDto {

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long id;

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long phoneCaseModelId;

    @NotNull
    private LocalDate dateOfIssue;

    @Schema(example = VIN_CODE_EXAMPLE, minLength = INT_1, maxLength = INT_30)
    @Size(min = INT_1, max = INT_30)
    @NotNull
    private String vinCode;

    @Schema(example = COLOR, minLength = INT_2, maxLength = INT_20)
    @Size(min = INT_2, max = INT_20)
    @NotNull
    private String color;
}
