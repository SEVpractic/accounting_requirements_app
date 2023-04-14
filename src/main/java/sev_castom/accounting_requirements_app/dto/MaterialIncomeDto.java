package sev_castom.accounting_requirements_app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sev_castom.accounting_requirements_app.util.CreateValidationGroup;
import sev_castom.accounting_requirements_app.util.UpdateValidationGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Builder(toBuilder = true)
@Getter
@RequiredArgsConstructor
public class MaterialIncomeDto {
    @NotBlank(groups = CreateValidationGroup.class)
    private String name;
    @NotBlank(groups = CreateValidationGroup.class)
    @Size(max = 256, groups = CreateValidationGroup.class)
    private String description;
    @NotBlank(groups = CreateValidationGroup.class)
    private String unit;
    @NotNull(groups = CreateValidationGroup.class)
    @Positive(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private Integer amount;
}
