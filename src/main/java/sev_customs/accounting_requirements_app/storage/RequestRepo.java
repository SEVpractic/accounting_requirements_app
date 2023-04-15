package sev_customs.accounting_requirements_app.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sev_customs.accounting_requirements_app.model.Request;
import sev_customs.accounting_requirements_app.model.RequestStatus;

public interface RequestRepo extends JpaRepository<Request, Long> {
    @Query("select r from Request as r where r.status like :status ")
    Page<Request> findAllByStatus(RequestStatus status, Pageable pageable);
}
