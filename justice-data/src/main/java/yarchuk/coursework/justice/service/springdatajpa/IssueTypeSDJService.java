package yarchuk.coursework.justice.service.springdatajpa;

import yarchuk.coursework.justice.model.IssueType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.repository.IssueTypeRepository;
import yarchuk.coursework.justice.service.IssueTypeService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class IssueTypeSDJService implements IssueTypeService {

    private final IssueTypeRepository issueTypeRepository;

    public IssueTypeSDJService(IssueTypeRepository issueTypeRepository) {
        this.issueTypeRepository = issueTypeRepository;
    }

    @Override
    public Set<IssueType> findAll() {
        Set<IssueType> issueTypes = new HashSet<>();
        issueTypeRepository.findAll().forEach(issueTypes::add);

        return issueTypes;
    }

    @Override
    public IssueType findById(Long aLong) {
        return issueTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public IssueType save(IssueType object) {
        return issueTypeRepository.save(object);
    }

    @Override
    public void delete(IssueType object) {
        issueTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        issueTypeRepository.deleteById(aLong);
    }
}
