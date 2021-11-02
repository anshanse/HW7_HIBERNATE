package ua.GoIT_Dev2.ProjectManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serial;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "skills")
public class Skill implements BaseEntity<Long>{

    @Serial
    private static final long serialVersionUID = -5358471669094964579L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private String grade;

    @ManyToMany(mappedBy = "skills")
    private Set<Developer> developers;
}
