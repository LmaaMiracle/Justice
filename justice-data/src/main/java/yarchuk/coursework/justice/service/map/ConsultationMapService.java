package yarchuk.coursework.justice.service.map;

import yarchuk.coursework.justice.model.Consultation;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.service.ConsultationService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class ConsultationMapService extends AbstractMapService<Consultation, Long> implements ConsultationService {
    @Override
    public Set<Consultation> findAll() {
        return super.findAll();
    }

    @Override
    public Consultation findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Consultation save(Consultation consultation) {
        return super.save(consultation);
    }

    @Override
    public void delete(Consultation consultation) {
        super.delete(consultation);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
