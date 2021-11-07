package ua.GoIT_Dev2.ProjectManagementSystem.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serial;
import java.util.Set;

//@Table(name = "developers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"company", "projects", "skills"})
@ToString (exclude = {"company", "projects", "skills"})
@Entity
@Table(name = "developers")
public class Developer implements BaseEntity<Long> {

    @Serial
    private static final long serialVersionUID = -5406210571652062908L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "other")
    private String info;

    @Column(name = "salary")
    private Long salary;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;

    @ManyToMany(mappedBy = "developers")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Project> projects;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "developer_skill",
            joinColumns = {@JoinColumn (name = "developer_id")},
            inverseJoinColumns = {@JoinColumn (name = "skill_id")}
    )
    private Set<Skill> skills;
}
