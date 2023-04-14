package sev_customs.accounting_requirements_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sev_customs.accounting_requirements_app.dto.UserDto;
import sev_customs.accounting_requirements_app.dto.UserIncomeDto;
import sev_customs.accounting_requirements_app.service.UserService;
import sev_customs.accounting_requirements_app.util.CreateValidationGroup;
import sev_customs.accounting_requirements_app.util.UpdateValidationGroup;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto create(@Validated(CreateValidationGroup.class) @RequestBody UserIncomeDto dto) {
        return userService.create(dto);
    }

    @PatchMapping("/{userId}")
    public UserDto update(@Validated(UpdateValidationGroup.class) @RequestBody UserIncomeDto dto,
                          @PathVariable("userId") @Positive long userId) {
        return userService.update(dto, userId);
    }

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable("userId") @Positive long userId) {
        return userService.get(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") @Positive long userId) {
        userService.delete(userId);
    }
}
