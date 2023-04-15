package sev_customs.accounting_requirements_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Setter
@Getter
public class Request {
    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User requestor;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "material_id")
    private Material material;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @Column(name = "amount")
    private int amount;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
