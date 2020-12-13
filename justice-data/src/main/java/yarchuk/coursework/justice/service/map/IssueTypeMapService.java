package yarchuk.coursework.justice.service.map;

import yarchuk.coursework.justice.model.IssueType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.service.IssueTypeService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class IssueTypeMapService extends AbstractMapService<IssueType, Long> implements IssueTypeService {
    @Override
    public Set<IssueType> findAll() {
        return super.findAll();
    }

    @Override
    public IssueType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public IssueType save(IssueType issueType) {
        return super.save(issueType);
    }

    @Override
    public void delete(IssueType issueType) {
        super.delete(issueType);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
