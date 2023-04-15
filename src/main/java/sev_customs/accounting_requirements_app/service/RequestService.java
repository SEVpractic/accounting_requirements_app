package sev_customs.accounting_requirements_app.service;

import sev_customs.accounting_requirements_app.dto.RequestDto;
import sev_customs.accounting_requirements_app.dto.RequestIncomeDto;
import sev_customs.accounting_requirements_app.model.RequestStatus;

import java.util.List;

public interface RequestService {
    RequestDto create(RequestIncomeDto dto, long userId);

    RequestDto update(RequestIncomeDto dto, long requestId, long userId);

    RequestDto get(long requestId);

    List<RequestDto> getAll(long requestId, int from, int size);

    List<RequestDto> getPending(int from, int size);

    void delete(long requestId, long userId);

    RequestDto process(long requestId, long userId, RequestStatus status);
}
