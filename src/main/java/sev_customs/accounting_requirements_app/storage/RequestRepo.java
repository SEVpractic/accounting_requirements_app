package sev_customs.accounting_requirements_app.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sev_customs.accounting_requirements_app.model.Request;
import sev_customs.accounting_requirements_app.model.RequestStatus;

public interface RequestRepo extends JpaRepository<Request, Long> {
    Page<Request> findAllByStatusIs(RequestStatus status, Pageable pageable);
}
