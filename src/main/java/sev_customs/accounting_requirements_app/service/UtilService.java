package sev_customs.accounting_requirements_app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev_customs.accounting_requirements_app.model.Material;
import sev_customs.accounting_requirements_app.model.Request;
import sev_customs.accounting_requirements_app.model.User;
import sev_customs.accounting_requirements_app.storage.MaterialRepo;
import sev_customs.accounting_requirements_app.storage.RequestRepo;
import sev_customs.accounting_requirements_app.storage.UserRepo;
import sev_customs.accounting_requirements_app.util.exceptions.EntityNotExistException;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UtilService {
    private final UserRepo userRepo;
    private final MaterialRepo materialRepo;
    private final RequestRepo requestRepo;

    public User findUserOrThrow(long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotExistException(
                        String.format("Пользователя с id = %s не существует", userId))
                );
    }

    public Material findMaterialOrThrow(long materialId) {
        return materialRepo.findById(materialId)
                .orElseThrow(() -> new EntityNotExistException(
                        String.format("Материал с id = %s не существует", materialId))
                );
    }

    public Request findRequestOrThrow(long requestId) {
        return requestRepo.findById(requestId)
                .orElseThrow(() -> new EntityNotExistException(
                        String.format("Требование с id = %s не существует", requestId))
                );
    }

    public Pageable createPagination(int from, int size, String sortBy) {
        return PageRequest.of(
                from == 0 ? 0 : (from / size),
                size,
                Sort.by(Sort.Direction.ASC, sortBy)
        );
    }
}
