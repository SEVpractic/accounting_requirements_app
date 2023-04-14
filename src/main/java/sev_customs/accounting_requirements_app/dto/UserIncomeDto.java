package sev_customs.accounting_requirements_app.dto;

import lombok.Builder;
import lombok.Getter;
import sev_customs.accounting_requirements_app.model.UserRoles;
import sev_customs.accounting_requirements_app.util.validation.CreateValidationGroup;
import sev_customs.accounting_requirements_app.util.validation.UpdateValidationGroup;

import javax.validation.constraints.*;

@Builder(toBuilder = true)
@Getter
public class UserIncomeDto {
    @NotBlank
    private final String name;
    @NotNull(groups = CreateValidationGroup.class)
    @Positive(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private final Integer departmentNumber;
    @Email
    @NotEmpty
    private final String email;
    @NotBlank(groups = CreateValidationGroup.class)
    private final UserRoles role;
}
