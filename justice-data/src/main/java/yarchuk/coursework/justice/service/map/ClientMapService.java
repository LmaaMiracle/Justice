package yarchuk.coursework.justice.service.map;

import yarchuk.coursework.justice.model.Client;
import yarchuk.coursework.justice.model.Issue;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import yarchuk.coursework.justice.service.ClientService;
import yarchuk.coursework.justice.service.IssueService;
import yarchuk.coursework.justice.service.IssueTypeService;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class ClientMapService extends AbstractMapService<Client, Long> implements ClientService {
    private final IssueTypeService issueTypeService;
    private final IssueService issueService;
    private final ClientService clientService;

    public ClientMapService(IssueTypeService issueTypeService, IssueService issueService, ClientService clientService) {
        this.issueTypeService = issueTypeService;
        this.issueService = issueService;
        this.clientService = clientService;
    }

    @Override
    public Set<Client> findAll() {
        return super.findAll();
    }

    @Override
    public Client findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Client save(Client client) {

        if (client != null) {
            if (client.getIssues() != null) {
                client.getIssues().forEach(issue -> {
                    if (issue.getIssueType() != null) {
                        if (issue.getIssueType().getId() == null) {
                            issue.setIssueType(issueTypeService.save(issue.getIssueType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if (issue.getId() == null) {
                        Issue savedIssue = issueService.save(issue);
                        issue.setId(savedIssue.getId());
                    }
                });
            }

            return super.save(client);

        } else {
            return null;
        }
    }

    @Override
    public void delete(Client client) {
        super.delete(client);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Client findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Client> findAllByLastNameLike(String lastName) {
        return clientService.findAllByLastNameLike("%" + lastName + "%");
    }

    @Override
    public List<Client> findAllByAuthCodeLike(String authCode) {
        return clientService.findAllByAuthCodeLike(authCode);
    }
}
