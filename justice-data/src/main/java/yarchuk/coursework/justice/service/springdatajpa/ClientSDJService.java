package yarchuk.coursework.justice.service.springdatajpa;

import yarchuk.coursework.justice.model.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.repository.ClientRepository;
import yarchuk.coursework.justice.repository.IssueRepository;
import yarchuk.coursework.justice.repository.IssueTypeRepository;
import yarchuk.coursework.justice.service.ClientService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ClientSDJService implements ClientService {

    private final ClientRepository clientRepository;
    private final IssueRepository issueRepository;
    private final IssueTypeRepository issueTypeRepository;


    public ClientSDJService(ClientRepository clientRepository, IssueRepository issueRepository, IssueTypeRepository issueTypeRepository) {
        this.clientRepository = clientRepository;
        this.issueRepository = issueRepository;
        this.issueTypeRepository = issueTypeRepository;
    }

    @Override
    public Client findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    @Override
    public List<Client> findAllByLastNameLike(String lastName) {
        return clientRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public List<Client> findAllByAuthCodeLike(String authCode) {
        return clientRepository.findAllByAuthCodeLike(authCode);
    }

    @Override
    public Set<Client> findAll() {
        Set<Client> clients = new HashSet<>();
        clientRepository.findAll().forEach(clients::add);

        return clients;
    }

    @Override
    public Client findById(Long aLong) {
        return clientRepository.findById(aLong).orElse(null);
    }

    @Override
    public Client save(Client object) {
        return clientRepository.save(object);
    }

    @Override
    public void delete(Client object) {
        clientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }
}
