package sev_customs.accounting_requirements_app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev_customs.accounting_requirements_app.dto.UserDto;
import sev_customs.accounting_requirements_app.dto.UserIncomeDto;
import sev_customs.accounting_requirements_app.model.User;
import sev_customs.accounting_requirements_app.storage.UserRepo;
import sev_customs.accounting_requirements_app.util.exceptions.EntityNotExistException;

import static sev_customs.accounting_requirements_app.util.mappers.UserMapper.toUser;
import static sev_customs.accounting_requirements_app.util.mappers.UserMapper.toUserDto;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    @Transactional
    public UserDto create(UserIncomeDto dto) {
        User user = toUser(dto);

        user = findUserOrSave(user);
        log.info("Сохранен пользователь с id = {} ", user.getId());

        return toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto update(UserIncomeDto dto, long userId) {
        User user = findUserOrThrow(userId);

        update(dto, user);
        log.info("Обновлен пользователь с id = {} ", user.getId());

        return toUserDto(user);
    }

    @Override
    public UserDto get(long userId) {
        User user = findUserOrThrow(userId);

        log.info("Возвращен пользователь с id = {} ", user.getId());
        return toUserDto(user);
    }

    @Override
    public void delete(long userId) {
        User user = findUserOrThrow(userId);

        userRepo.delete(user);
        log.info("Удален пользователь id = {} ", userId);
    }

    private User findUserOrSave(User user) {
        return userRepo.findByEmail(user.getEmail())
                .orElseGet(() -> userRepo.save(user));
    }

    private User findUserOrThrow(long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotExistException(
                        String.format("Пользователя с id = %s не существует", userId))
                );
    }

    private User update(UserIncomeDto dto, User user) {
        if (dto.getDepartmentNumber() != null) user.setDepartmentNumber(dto.getDepartmentNumber());
        if (dto.getRole() != null) user.setRole(dto.getRole());
    }
}
