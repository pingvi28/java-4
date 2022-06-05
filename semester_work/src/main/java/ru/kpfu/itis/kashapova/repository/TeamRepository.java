package ru.kpfu.itis.kashapova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.kashapova.entity.Team;
import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> getTeamByCreatorEmail(String email);

    // нестанд
    @Query("select user.team from User user where user.email = :email")
    List<Team> getTeamByParticipantEmail(@Param("email") String email);

    @Query("select user.team from User user where user.login = :login")
    List<Team> getTeamByParticipantLogin(@Param("login") String login);

    Optional<Team> findByName(String name);
}
