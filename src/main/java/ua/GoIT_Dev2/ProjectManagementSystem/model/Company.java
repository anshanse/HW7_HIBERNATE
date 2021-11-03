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
@Entity
@Table(name = "companies")
public class Company implements BaseEntity<Long> {

    @Serial
    private static final long serialVersionUID = 6196148398791897660L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy="company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projects;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Developer> developers;
}
