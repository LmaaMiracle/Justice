package yarchuk.coursework.justice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends Person {

    @Builder
    public Client(Long id, String firstName, String lastName, String city,
                  String telephone, Set<Issue> issues) {
        super(id, firstName, lastName);
        this.city = city;
        this.telephone = telephone;
        this.issues = issues;
    }

    @Column(name = "auth_code")
    private String authCode;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Issue> issues = new HashSet<>();

    public Issue getIssue(String issue) {
        return getIssue(issue, false);
    }

    public Issue getIssue(String description, boolean ignoreNew) {
        description = description.toLowerCase();
        for (Issue issue : issues) {
            if (!ignoreNew || !issue.isNew()) {
                String findDescription = issue.getDescription();
                findDescription = findDescription.toLowerCase();
                if (findDescription.equals(description)) {
                    return issue;
                }
            }
        }
        return null;
    }

}
