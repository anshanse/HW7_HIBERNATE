package ua.GoIT_Dev2.ProjectManagementSystem.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"projects"})
@Entity(name = "customers")
public class Customer implements BaseEntity<Long> {

    @Serial
    private static final long serialVersionUID = 5184860151334342492L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    private Set<Project> projects;
}