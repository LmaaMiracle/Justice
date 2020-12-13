package yarchuk.coursework.justice.service.springdatajpa;

import yarchuk.coursework.justice.model.Consultation;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.repository.ConsultationRepository;
import yarchuk.coursework.justice.service.ConsultationService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ConsultationSDJService implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationSDJService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Set<Consultation> findAll() {
        Set<Consultation> consultations = new HashSet<>();
        consultationRepository.findAll().forEach(consultations::add);

        return consultations;
    }

    @Override
    public Consultation findById(Long aLong) {
        return consultationRepository.findById(aLong).orElse(null);
    }

    @Override
    public Consultation save(Consultation object) {
        return consultationRepository.save(object);
    }

    @Override
    public void delete(Consultation object) {
        consultationRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        consultationRepository.deleteById(aLong);
    }
}
