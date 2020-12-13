package yarchuk.coursework.justice.service.springdatajpa;

import yarchuk.coursework.justice.model.Issue;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.repository.IssueRepository;
import yarchuk.coursework.justice.service.IssueService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class IssueSDJService implements IssueService {

    private final IssueRepository issueRepository;

    public IssueSDJService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public Set<Issue> findAll() {
        Set<Issue> issues = new HashSet<>();
        issueRepository.findAll().forEach(issues::add);

        return issues;
    }

    @Override
    public Issue findById(Long aLong) {
        return issueRepository.findById(aLong).orElse(null);
    }

    @Override
    public Issue save(Issue object) {
        return issueRepository.save(object);
    }

    @Override
    public void delete(Issue object) {
        issueRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        issueRepository.deleteById(aLong);
    }
}
