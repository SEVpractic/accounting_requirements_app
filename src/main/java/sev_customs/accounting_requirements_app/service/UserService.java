package sev_customs.accounting_requirements_app.service;

import sev_customs.accounting_requirements_app.dto.UserDto;
import sev_customs.accounting_requirements_app.dto.UserIncomeDto;

public interface UserService {
    UserDto create(UserIncomeDto dto);

    UserDto update(UserIncomeDto dto, long userId);

    UserDto get(long userId);

    void delete(long userId);
}
