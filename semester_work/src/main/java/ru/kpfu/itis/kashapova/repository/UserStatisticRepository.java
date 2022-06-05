package ru.kpfu.itis.kashapova.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.kashapova.controller.ExceptionHandlerController;
import ru.kpfu.itis.kashapova.entity.UserStatistic;

import java.math.BigInteger;


@Repository
public class UserStatisticRepository {

    private final EntityManager entityManager;

    public UserStatisticRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Аннотация @Transactional определяет область действия одной транзакции БД.
    //Запись LEFT JOIN идентична LEFT OUTER JOIN. -
    @Transactional
    public UserStatistic getUserStatisticWithParticipantTeam(String email) {
        Tuple tuple = (Tuple) entityManager.createNativeQuery(
                "select us.login as login, us.email as email, count(ad.id) as adCount, count(resume.id) as resumeCount, count(account_team.team_id) as teamCount "
                        + "from account as us "
                        + "left outer join ad on us.id = ad.owner_id "
                        + "left outer join resume on us.id = resume.owner_id "
                        + "left outer join account_team on account_team.participant_id = us.id where us.email=:email group by (us.login, us.email)",
                Tuple.class).setParameter("email", email).getSingleResult();
        try{
            return mapToEntity(tuple);
        } catch (javax.persistence.NoResultException ex){
            ExceptionHandlerController.notFound();
        }
        return null;
    }

    private UserStatistic mapToEntity(Tuple tuple) {
        UserStatistic userStatistic = new UserStatistic();
        userStatistic.setEmail(tuple.get("email", String.class));
        userStatistic.setLogin(tuple.get("login", String.class));
        userStatistic.setAdCount(tuple.get("adCount", BigInteger.class));
        userStatistic.setResumeCount(tuple.get("resumeCount", BigInteger.class));
        userStatistic.setTeamCount(tuple.get("teamCount", BigInteger.class));
        return userStatistic;
    }
}
