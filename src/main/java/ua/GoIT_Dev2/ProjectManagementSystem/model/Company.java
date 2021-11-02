package ua.GoIT_Dev2.ProjectManagementSystem.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"projects", "developers"})
@Entity(name = "companies")
public class Company implements BaseEntity<Long> {

    @Serial
    private static final long serialVersionUID = 8007115697480334892L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy="company", cascade = CascadeType.ALL)
    private Set<Project> projects;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Developer> developers;
}
