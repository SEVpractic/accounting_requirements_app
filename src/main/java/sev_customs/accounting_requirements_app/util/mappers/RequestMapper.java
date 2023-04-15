package sev_customs.accounting_requirements_app.util.mappers;

import sev_customs.accounting_requirements_app.dto.RequestDto;
import sev_customs.accounting_requirements_app.dto.RequestIncomeDto;
import sev_customs.accounting_requirements_app.model.Material;
import sev_customs.accounting_requirements_app.model.Request;
import sev_customs.accounting_requirements_app.model.User;

import java.util.List;
import java.util.stream.Collectors;

import static sev_customs.accounting_requirements_app.util.mappers.MaterialMapper.toMaterialDto;
import static sev_customs.accounting_requirements_app.util.mappers.UserMapper.toUserDto;

public class RequestMapper {
    public static Request toRequest(RequestIncomeDto dto, User user, Material material) {
        Request request = new Request();

        request.setMaterial(material);
        request.setRequestor(user);
        request.setAmount(dto.getAmount());

        return request;
    }

    public static RequestDto toRequestDto(Request request) {
        return RequestDto.builder()
                .id(request.getId())
                .amount(request.getAmount())
                .material(toMaterialDto(request.getMaterial()))
                .user(toUserDto(request.getRequestor()))
                .status(request.getStatus())
                .build();
    }

    public static List<RequestDto> toRequestDto(List<Request> requests) {
        return requests.stream()
                .map(RequestMapper::toRequestDto)
                .collect(Collectors.toList());
    }
}
