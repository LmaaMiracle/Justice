package yarchuk.coursework.justice.repository;

import yarchuk.coursework.justice.model.IssueType;
import org.springframework.data.repository.CrudRepository;

public interface IssueTypeRepository extends CrudRepository<IssueType, Long> {
}
