package sev_customs.accounting_requirements_app.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev_customs.accounting_requirements_app.model.Material;

import java.awt.print.Pageable;
import java.util.List;

public interface MaterialRepo extends JpaRepository<Material, Long> {
}
