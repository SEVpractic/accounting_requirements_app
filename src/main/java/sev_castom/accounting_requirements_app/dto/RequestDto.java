package sev_castom.accounting_requirements_app.dto;

import lombok.Builder;
import lombok.Getter;
import sev_castom.accounting_requirements_app.model.RequestStatus;

@Builder(toBuilder = true)
@Getter
public class RequestDto {
    private final Long id;
    private final MaterialDto material;
    private final UserDto user;
    private Integer amount;
    private RequestStatus status;
}
