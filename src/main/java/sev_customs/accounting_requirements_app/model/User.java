package sev_customs.accounting_requirements_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "department_number")
    private int departmentNumber;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;
}
