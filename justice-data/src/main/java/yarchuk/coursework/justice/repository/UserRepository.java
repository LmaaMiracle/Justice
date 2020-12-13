package yarchuk.coursework.justice.repository;

import org.springframework.data.repository.CrudRepository;
import yarchuk.coursework.justice.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String email);
}
