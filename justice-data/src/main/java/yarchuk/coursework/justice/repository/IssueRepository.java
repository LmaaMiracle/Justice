package yarchuk.coursework.justice.repository;

import yarchuk.coursework.justice.model.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue, Long> {
}
