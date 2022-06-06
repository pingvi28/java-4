package ru.kpfu.itis.kashapova.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.kashapova.entities.Local;
import ru.kpfu.itis.kashapova.repository.LocalRepository;
import ru.kpfu.itis.kashapova.repository.LocalRepositoryImp;

public class LocalService {
    @Autowired
    private LocalRepository localRepository;

    public LocalService(LocalRepositoryImp localRep){this.localRepository=localRep;}

    public Local findLocalByLanguage(String lang){
        try{
            return localRepository.find(lang);
        }catch (Exception ex){
            return localRepository.find("EN");
        }
    }
}

