package sev_castom.accounting_requirements_app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sev_castom.accounting_requirements_app.model.UserRoles;
import sev_castom.accounting_requirements_app.util.CreateValidationGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Builder(toBuilder = true)
@Getter
@RequiredArgsConstructor
public class UserIncomeDto {
    @NotBlank
    private final String name;
    @Positive(groups = CreateValidationGroup.class)
    private final int departmentNumber;
    @Email
    @NotEmpty
    private final String email;
    @NotBlank(groups = CreateValidationGroup.class)
    private final UserRoles role;
}
