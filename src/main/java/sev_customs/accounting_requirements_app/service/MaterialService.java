package sev_customs.accounting_requirements_app.service;

import sev_customs.accounting_requirements_app.dto.MaterialDto;
import sev_customs.accounting_requirements_app.dto.MaterialIncomeDto;
import sev_customs.accounting_requirements_app.util.mappers.MaterialMapper;

import java.util.List;

public interface MaterialService {
    MaterialDto create(MaterialIncomeDto dto, long userId);

    MaterialDto update(MaterialIncomeDto dto, long materialId, long userId);

    MaterialDto get(long materialId);

    List<MaterialDto> getAll(int from, int size);

    void delete(long materialId, long userId);
}
