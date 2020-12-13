package yarchuk.coursework.justice.service.map;

import org.springframework.beans.factory.annotation.Autowired;
import yarchuk.coursework.justice.model.Issue;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.repository.IssueRepository;
import yarchuk.coursework.justice.service.IssueService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class IssueMapService extends AbstractMapService<Issue, Long> implements IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueMapService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public Set<Issue> findAll() {
        return null;
    }

    @Override
    public Issue findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Issue save(Issue issue) {
        issueRepository.save(issue);

        return issue;
//        return super.save(issue);
    }

    @Override
    public void delete(Issue issue) {
        super.delete(issue);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
