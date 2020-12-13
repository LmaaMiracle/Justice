package yarchuk.coursework.justice.service.map;

import yarchuk.coursework.justice.model.Lawyer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.service.LawyerService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class LawyerMapService extends AbstractMapService<Lawyer, Long> implements LawyerService {
    @Override
    public Set<Lawyer> findAll() {
        return super.findAll();
    }

    @Override
    public Lawyer findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Lawyer save(Lawyer lawyer) {
        return save(lawyer);
    }

    @Override
    public void delete(Lawyer lawyer) {
        super.delete(lawyer);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
