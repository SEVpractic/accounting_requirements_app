package sev_customs.accounting_requirements_app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev_customs.accounting_requirements_app.dto.MaterialDto;
import sev_customs.accounting_requirements_app.dto.MaterialIncomeDto;
import sev_customs.accounting_requirements_app.model.Material;
import sev_customs.accounting_requirements_app.model.User;
import sev_customs.accounting_requirements_app.storage.MaterialRepo;
import sev_customs.accounting_requirements_app.util.exceptions.OperationFailedException;

import java.util.List;

import static sev_customs.accounting_requirements_app.model.UserRoles.SUPERVISOR;
import static sev_customs.accounting_requirements_app.util.mappers.MaterialMapper.toMaterial;
import static sev_customs.accounting_requirements_app.util.mappers.MaterialMapper.toMaterialDto;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MaterialServiceImpl implements MaterialService {
    private final UtilService utilService;
    private final MaterialRepo materialRepo;

    @Override
    @Transactional
    public MaterialDto create(MaterialIncomeDto dto, long userId) {
        Material material = toMaterial(dto);
        User user = findAndCheckUser(userId);

        material = materialRepo.save(material);
        log.info("Сохранен материал с id = {} ", user.getId());

        return toMaterialDto(material);
    }

    @Override
    @Transactional
    public MaterialDto update(MaterialIncomeDto dto, long materialId, long userId) {
        Material material = utilService.findMaterialOrThrow(materialId);
        findAndCheckUser(userId);

        update(dto, material);
        log.info("Обновлен материал с id = {} ", material.getId());

        return toMaterialDto(material);
    }

    @Override
    public MaterialDto get(long materialId) {
        Material material = utilService.findMaterialOrThrow(materialId);

        log.info("Возвращен материал с id = {} ", material.getId());
        return toMaterialDto(material);
    }

    @Override
    public List<MaterialDto> getAll(int from, int size) {
        Pageable pageable = utilService.createPagination(from, size, "id");
        List<Material> materials = materialRepo.findAll(pageable).toList();

        log.info("Возвращен список всех материалов");
        return toMaterialDto(materials);
    }

    @Override
    @Transactional
    public void delete(long materialId, long userId) {
        Material material = utilService.findMaterialOrThrow(materialId);
        findAndCheckUser(userId);

        materialRepo.delete(material);
        log.info("Удален материал id = {} ", materialId);

    }

    private User findAndCheckUser(long userId) {
        User user = utilService.findUserOrThrow(userId);
        checkUserRole(user);
        return user;
    }

    private void checkUserRole(User user) {
        if (!user.getRole().equals(SUPERVISOR)) {
            throw new OperationFailedException(
                    "Добавлять записи имеют право только пользователи с ролью = " + SUPERVISOR
            );
        }
    }

    private void update(MaterialIncomeDto dto, Material material) {
        if (dto.getAmount() != null) material.setAmount(dto.getAmount());
    }
}
