package sev_customs.accounting_requirements_app.util.mappers;

import lombok.experimental.UtilityClass;
import sev_customs.accounting_requirements_app.dto.UserDto;
import sev_customs.accounting_requirements_app.dto.UserIncomeDto;
import sev_customs.accounting_requirements_app.model.User;

@UtilityClass
public class UserMapper {
    public static User toUser(UserIncomeDto dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        user.setDepartmentNumber(dto.getDepartmentNumber());

        return user;
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .departmentNumber(user.getDepartmentNumber())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
