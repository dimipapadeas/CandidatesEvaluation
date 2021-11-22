package com.dterz.repositories;

import com.dterz.model.QUser;
import com.dterz.model.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class UserRepository extends GenericRepository<User> {

    public User getUserByUsername(String uName) {
        QUser user = QUser.user;
        final JPAQuery<User> query = new JPAQuery<>(entityManager);
        return query.select(user).from(user).where(user.uName.eq(uName)).fetchFirst();
    }

    public List<User> findAll() {
        QUser user = QUser.user;
        final JPAQuery<User> query = new JPAQuery<>(entityManager);
        return query.select(user).from(user).fetch();
    }
}
