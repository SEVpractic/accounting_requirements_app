package sev_customs.accounting_requirements_app.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev_customs.accounting_requirements_app.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
