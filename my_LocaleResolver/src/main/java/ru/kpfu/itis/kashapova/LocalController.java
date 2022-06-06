package ru.kpfu.itis.kashapova;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.kpfu.itis.kashapova.entities.Local;
import ru.kpfu.itis.kashapova.repository.LocalRepository;
import ru.kpfu.itis.kashapova.repository.LocalRepositoryImp;

@Controller
public class LocalController {
    @Autowired
    private LocalRepository localRepository;

    public LocalController(LocalRepositoryImp localRep){this.localRepository=localRep;}

    public Local findLocalByLanguage(String lang){
        try{
            return localRepository.find(lang);
        }catch (NullPointerException ex){
            return localRepository.find("EN");
        }
    }

//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(Objects.requireNonNull("org.postgresql.Driver,");
//        dataSource.setUrl(env.getProperty("db.url"));
//        dataSource.setUsername(env.getProperty("db.username"));
//        dataSource.setPassword(env.getProperty("db.password"));
//    }
}
