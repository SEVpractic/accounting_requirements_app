package sev_castom.accounting_requirements_app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sev_castom.accounting_requirements_app.util.CreateValidationGroup;
import sev_castom.accounting_requirements_app.util.UpdateValidationGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder(toBuilder = true)
@Getter
@RequiredArgsConstructor
public class RequestIncomeDto {
    @NotNull(groups = CreateValidationGroup.class)
    private final Long materialId;
    @NotNull(groups = CreateValidationGroup.class)
    @Positive(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private Integer amount;
}
