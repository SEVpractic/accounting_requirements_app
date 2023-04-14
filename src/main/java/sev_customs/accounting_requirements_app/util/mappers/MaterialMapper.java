package sev_customs.accounting_requirements_app.util.mappers;

import lombok.experimental.UtilityClass;
import sev_customs.accounting_requirements_app.dto.MaterialDto;
import sev_customs.accounting_requirements_app.dto.MaterialIncomeDto;
import sev_customs.accounting_requirements_app.model.Material;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MaterialMapper {

    public static Material toMaterial(MaterialIncomeDto dto) {
        Material material = new Material();

        material.setName(dto.getName());
        material.setUnit(dto.getUnit());
        material.setAmount(dto.getAmount());
        material.setDescription(dto.getDescription());

        return material;
    }

    public static MaterialDto toMaterialDto(Material material) {
        return MaterialDto.builder()
                .id(material.getId())
                .name(material.getName())
                .description(material.getDescription())
                .unit(material.getUnit())
                .amount(material.getAmount())
                .build();
    }

    public static List<MaterialDto> toMaterialDto(List<Material> materials) {
        return materials.stream()
                .map(MaterialMapper::toMaterialDto)
                .collect(Collectors.toList());
    }
}
