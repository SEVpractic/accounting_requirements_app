package sev_customs.accounting_requirements_app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev_customs.accounting_requirements_app.dto.UserDto;
import sev_customs.accounting_requirements_app.dto.UserIncomeDto;
import sev_customs.accounting_requirements_app.storage.UserRepo;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public UserDto create(UserIncomeDto dto) {
        return null;
    }

    @Override
    public UserDto update(UserIncomeDto dto, long userId) {
        return null;
    }

    @Override
    public UserDto get(long userId) {
        return null;
    }

    @Override
    public void delete(long userId) {

    }
}
