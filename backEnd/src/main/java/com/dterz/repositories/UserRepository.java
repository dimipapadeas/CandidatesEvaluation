package com.dterz.repositories;

import com.dterz.model.QUser;
import com.dterz.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {

    default User findByUsername(String uName) {
        return findOne(QUser.user.uName.eq(uName)).orElse(null);
    }
}
