package sev_customs.accounting_requirements_app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev_customs.accounting_requirements_app.dto.RequestDto;
import sev_customs.accounting_requirements_app.dto.RequestIncomeDto;
import sev_customs.accounting_requirements_app.model.Material;
import sev_customs.accounting_requirements_app.model.Request;
import sev_customs.accounting_requirements_app.model.RequestStatus;
import sev_customs.accounting_requirements_app.model.User;
import sev_customs.accounting_requirements_app.storage.RequestRepo;
import sev_customs.accounting_requirements_app.util.exceptions.OperationFailedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static sev_customs.accounting_requirements_app.model.RequestStatus.PENDING;
import static sev_customs.accounting_requirements_app.model.UserRoles.SUPERVISOR;
import static sev_customs.accounting_requirements_app.util.mappers.RequestMapper.toRequest;
import static sev_customs.accounting_requirements_app.util.mappers.RequestMapper.toRequestDto;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RequestServiceImpl implements RequestService {
    private final UtilService utilService;
    private final RequestRepo requestRepo;

    @Override
    @Transactional
    public RequestDto create(RequestIncomeDto dto, long userId) {
        User user = utilService.findUserOrThrow(userId);
        Material material = utilService.findMaterialOrThrow(dto.getMaterialId());
        Request request = toRequest(dto, user, material);

        request.setCreatedOn(LocalDateTime.now());
        request.setStatus(PENDING);
        request = requestRepo.save(request);
        log.info("Сохранено требование с id = {} ", request.getId());

        return toRequestDto(request);
    }

    @Override
    @Transactional
    public RequestDto update(RequestIncomeDto dto, long requestId, long userId) {
        Request request = utilService.findRequestOrThrow(requestId);

        checkRequestor(userId, request);
        update(dto, request);
        log.info("Обновлено требование с id = {} ", request.getId());

        return toRequestDto(request);
    }

    @Override
    public RequestDto get(long requestId) {
        Request request = utilService.findRequestOrThrow(requestId);

        log.info("Возвращен запрос с id = {} ", request.getId());
        return toRequestDto(request);
    }

    @Override
    public List<RequestDto> getAll(int from, int size) {
        Pageable pageable = utilService.createPagination(from, size, "createdOn");
        List<Request> requests = requestRepo.findAll(pageable).toList();

        log.info("Возвращен список всех запросов");
        return toRequestDto(requests);
    }

    @Override
    public List<RequestDto> getPending(int from, int size) {
        Pageable pageable = utilService.createPagination(from, size, "createdOn");
        List<Request> requests = requestRepo.findAllByStatus(PENDING, pageable).toList();

        log.info("Возвращен список всех запросов, ожидающих подтверждения");
        return toRequestDto(requests);
    }

    @Override
    @Transactional
    public void delete(long requestId, long userId) {
        Request request = utilService.findRequestOrThrow(requestId);

        checkRequestor(userId, request);
        requestRepo.delete(request);
        log.info("Обновлен статус требования с id = {} ", requestId);
    }

    @Override
    @Transactional
    public RequestDto process(long requestId, long userId, RequestStatus status) {
        User user = utilService.findUserOrThrow(userId);
        Request request = utilService.findRequestOrThrow(requestId);

        checkSupervisor(user);
        process(request, status);
        log.info("Обновлено требование с id = {} ", requestId);

        return toRequestDto(request);
    }

    private void checkRequestor(Long userId, Request request) {
        if (!Objects.equals(userId, request.getRequestor().getId())) {
            throw new OperationFailedException(
                    "Редактировать требование имеет право только создатель"
            );
        }
    }

    private void checkSupervisor(User user) {
        if (!user.getRole().equals(SUPERVISOR)) {
            throw new OperationFailedException(
                    "Обрабатывать требование имеет право только пользователи с ролью = " + SUPERVISOR
            );
        }
    }

    private void update(RequestIncomeDto dto, Request request) {
        if (dto.getMaterialId() != null) {
            request.setMaterial(utilService.findMaterialOrThrow(dto.getMaterialId()));
        }
        if (dto.getAmount() != null) request.setAmount(dto.getAmount());
    }

    private void process(Request request, RequestStatus status) {
        Material material = request.getMaterial();

        if (request.getStatus().equals(PENDING) && material.getAmount() >= request.getAmount()) {
            request.setStatus(status);
            material.setAmount(material.getAmount() - request.getAmount());
        } else {
            throw new OperationFailedException(
                    "Невозможно подтвердить"
            );
        }
    }
}
