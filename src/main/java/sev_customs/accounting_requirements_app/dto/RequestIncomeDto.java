package sev_customs.accounting_requirements_app.dto;

import lombok.Builder;
import lombok.Getter;
import sev_customs.accounting_requirements_app.util.validation.CreateValidationGroup;
import sev_customs.accounting_requirements_app.util.validation.UpdateValidationGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder(toBuilder = true)
@Getter
public class RequestIncomeDto {
    @NotNull(groups = CreateValidationGroup.class)
    private final Long materialId;
    @NotNull(groups = CreateValidationGroup.class)
    @Positive(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private Integer amount;
}
