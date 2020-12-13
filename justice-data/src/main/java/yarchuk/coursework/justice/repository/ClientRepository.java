package yarchuk.coursework.justice.repository;

import yarchuk.coursework.justice.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByLastName(String lastName);

    List<Client> findAllByLastNameLike(String lastName);

    List<Client> findAllByAuthCodeLike(String authCode);
}

