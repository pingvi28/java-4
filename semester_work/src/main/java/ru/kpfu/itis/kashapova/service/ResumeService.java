package ru.kpfu.itis.kashapova.service;


import ru.kpfu.itis.kashapova.dto.ResumeDto;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.form.ResumeForm;

import java.util.List;
import java.util.Optional;

public interface ResumeService {

    List<ResumeDto> getAll();
    void add(ResumeForm resume);
    List<ResumeDto> getByUserEmail(String email);

    Optional<ResumeDto> getById(Long id);

    Optional<UserDto> getUserByUserEmail(String name);
}
