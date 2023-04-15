package sev_customs.accounting_requirements_app.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev_customs.accounting_requirements_app.model.Material;

public interface MaterialRepo extends JpaRepository<Material, Long> {
}
