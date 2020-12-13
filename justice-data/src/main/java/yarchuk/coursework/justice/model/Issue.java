package yarchuk.coursework.justice.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issues")
public class Issue extends BaseEntity {

    @Builder
    public Issue(Long id, String description, IssueType issueType, Client client, LocalDate occurDate, Set<Consultation> consultations) {
        super(id);
        this.description = description;
        this.issueType = issueType;
        this.client = client;
        this.applicationDate = occurDate;

        if (consultations == null || consultations.size() > 0) {
            this.consultations = consultations;
        }

    }

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private IssueType issueType;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "application_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "issue")
    private Set<Consultation> consultations = new HashSet<>();

}
