package sev_customs.accounting_requirements_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sev_customs.accounting_requirements_app.dto.RequestDto;
import sev_customs.accounting_requirements_app.dto.RequestIncomeDto;
import sev_customs.accounting_requirements_app.model.RequestStatus;
import sev_customs.accounting_requirements_app.service.RequestService;
import sev_customs.accounting_requirements_app.util.validation.CreateValidationGroup;
import sev_customs.accounting_requirements_app.util.validation.UpdateValidationGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class RequestController {
    private final RequestService requestService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public RequestDto create(@Validated(CreateValidationGroup.class) @RequestBody RequestIncomeDto dto,
                             @RequestParam(name = "userId") long userId) {
        return requestService.create(dto, userId);
    }

    @PatchMapping("/{requestId}")
    public RequestDto update(@Validated(UpdateValidationGroup.class) @RequestBody RequestIncomeDto dto,
                              @PathVariable("requestId") @Positive long requestId,
                              @RequestParam(name = "userId") long userId) {
        return requestService.update(dto, requestId, userId);
    }

    @PatchMapping("/process/{requestId}")
    public RequestDto process(@PathVariable("requestId") @Positive long requestId,
                              @RequestParam(name = "userId") long userId,
                              @RequestParam(name = "status") @NotNull RequestStatus status) {
        return requestService.process(requestId, userId, status);
    }

    @GetMapping("/{requestId}")
    public RequestDto get(@PathVariable("requestId") @Positive long requestId) {
        return requestService.get(requestId);
    }

    @GetMapping("/{requestId}")
    public List<RequestDto> getAll(@PathVariable("requestId") @Positive long requestId,
                                    @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero int from,
                                    @RequestParam(name = "size", defaultValue = "10") @Positive int size) {
        return requestService.getAll(requestId, from, size);
    }

    @GetMapping("/pending")
    public List<RequestDto> getPending(@RequestParam(name = "from", defaultValue = "0") @PositiveOrZero int from,
                                        @RequestParam(name = "size", defaultValue = "10") @Positive int size) {
        return requestService.getPending(from, size);
    }

    @DeleteMapping("/{requestId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("requestId") @Positive long requestId,
                       @RequestParam(name = "userId") long userId) {
        requestService.delete(requestId, userId);
    }
}
