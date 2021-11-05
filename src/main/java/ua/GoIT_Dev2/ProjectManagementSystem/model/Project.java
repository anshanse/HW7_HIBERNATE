package ua.GoIT_Dev2.ProjectManagementSystem.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"company", "customer", "developers"})
@ToString (exclude = {"company", "customer", "developers"})
@Entity
@Table(name = "projects")
public class Project implements BaseEntity<Long> {

    @Serial
    private static final long serialVersionUID = -5862211916185553288L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "start_date")
    private Date startDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_developer",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "developer_id")}
    )
    private Set<Developer> developers;
}
