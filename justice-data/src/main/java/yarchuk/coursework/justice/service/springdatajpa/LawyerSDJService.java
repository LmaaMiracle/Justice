package yarchuk.coursework.justice.service.springdatajpa;

import yarchuk.coursework.justice.model.Lawyer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.repository.LawyerRepository;
import yarchuk.coursework.justice.service.LawyerService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class LawyerSDJService implements LawyerService {

    private final LawyerRepository lawyerRepository;

    public LawyerSDJService(LawyerRepository lawyerRepository) {
        this.lawyerRepository = lawyerRepository;
    }

    @Override
    public Set<Lawyer> findAll() {
        Set<Lawyer> lawyers = new HashSet<>();
        lawyerRepository.findAll().forEach(lawyers::add);

        return lawyers;
    }

    @Override
    public Lawyer findById(Long aLong) {
        return lawyerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Lawyer save(Lawyer object) {
        return lawyerRepository.save(object);
    }

    @Override
    public void delete(Lawyer object) {
        lawyerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        lawyerRepository.deleteById(aLong);
    }
}
