package yarchuk.coursework.justice.service;

import yarchuk.coursework.justice.model.Client;

import java.util.List;

public interface ClientService extends CrudService<Client, Long> {

    Client findByLastName(String LastName);

    List<Client> findAllByLastNameLike(String lastName);

    List<Client> findAllByAuthCodeLike(String authCode);
}

