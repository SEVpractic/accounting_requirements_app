package sev_customs.accounting_requirements_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sev_customs.accounting_requirements_app.dto.MaterialDto;
import sev_customs.accounting_requirements_app.dto.MaterialIncomeDto;
import sev_customs.accounting_requirements_app.service.MaterialService;
import sev_customs.accounting_requirements_app.util.mappers.MaterialMapper;
import sev_customs.accounting_requirements_app.util.validation.CreateValidationGroup;
import sev_customs.accounting_requirements_app.util.validation.UpdateValidationGroup;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/materials")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class MaterialController {
    private final MaterialService materialsService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MaterialDto create(@Validated(CreateValidationGroup.class) @RequestBody MaterialIncomeDto dto,
                              @RequestParam(name = "userId") long userId) {
        return materialsService.create(dto, userId);
    }

    @PatchMapping("/{materialId}")
    public MaterialDto update(@Validated(UpdateValidationGroup.class) @RequestBody MaterialIncomeDto dto,
                          @PathVariable("materialId") @Positive long materialId,
                          @RequestParam(name = "userId") long userId) {
        return materialsService.update(dto, materialId, userId);
    }

    @GetMapping("/{materialId}")
    public MaterialDto get(@PathVariable("materialId") @Positive long materialId) {
        return materialsService.get(materialId);
    }

    @GetMapping
    public List<MaterialDto> getAll(@RequestParam(name = "from", defaultValue = "0") @PositiveOrZero int from,
                                @RequestParam(name = "size", defaultValue = "10") @Positive int size) {
        return materialsService.getAll(from, size);
    }

    @DeleteMapping("/{materialId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("materialId") @Positive long materialId,
                       @RequestParam(name = "userId") long userId) {
        materialsService.delete(materialId, userId);
    }
}
