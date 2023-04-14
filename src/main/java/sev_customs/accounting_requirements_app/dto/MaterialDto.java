package sev_customs.accounting_requirements_app.dto;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class MaterialDto {
    private final Long id;
    private String name;
    private String description;
    private String unit;
    private Integer amount;
}
