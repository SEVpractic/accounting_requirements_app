package sev_castom.accounting_requirements_app.dto;

import lombok.Builder;
import lombok.Getter;
import sev_castom.accounting_requirements_app.model.UserRoles;

@Builder(toBuilder = true)
@Getter
public class UserDto {
    private final Long id;
    private final String name;
    private final int departmentNumber;
    private final String email;
    private final UserRoles role;
}
