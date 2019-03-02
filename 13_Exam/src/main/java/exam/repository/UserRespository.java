package exam.repository;

import exam.domain.entities.User;

public interface UserRespository extends GenericRepository <User, String> {
User findByUserName(String userName);
}
