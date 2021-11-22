package com.dterz.repositories;

import com.dterz.model.JWTToken;
import com.dterz.model.QJWTToken;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthRepository extends GenericRepository<JWTToken> {

    public String getTokenByUsername(String userName) {
        QJWTToken token = QJWTToken.jWTToken;
        final JPAQuery<JWTToken> query = new JPAQuery<>(entityManager);
        return query.select(token.token).from(token).where(token.userName.eq(userName)).fetchFirst();
    }


    public JWTToken getJWTByUsername(String userName) {
        QJWTToken token = QJWTToken.jWTToken;
        final JPAQuery<JWTToken> query = new JPAQuery<>(entityManager);
        return query.select(token).from(token).where(token.userName.eq(userName)).fetchFirst();
    }
}
