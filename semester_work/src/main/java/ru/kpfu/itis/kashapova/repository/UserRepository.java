package ru.kpfu.itis.kashapova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.kashapova.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByLogin(String login);

    Optional<User> getByEmail(String email);

    Optional<User> getById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrLogin(String email, String login);

    //вытаскивааем необходимого участника команды
    @Query("select team.participant from Team team where team.name = :name")
    List<User> getUsersByTeamName(@Param("name") String name);

    // получить юзера, который не забанен и число объявлений, статус которых актив и автор наш юзер, брольше 0 == те он что-то писал
    // подзапросом получиили - данный, после список
    @Query("select user from User as user where user.isBanned=false and (select count(ad.id) from Ad as ad where ad.status='ACTIVE' and ad.owner.id=user.id) > 0")
    List<User> getUserWithActiveAd();

}
