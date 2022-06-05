package ru.kpfu.itis.kashapova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.dto.ResumeDto;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.entity.Resume;
import ru.kpfu.itis.kashapova.entity.User;
import ru.kpfu.itis.kashapova.form.ResumeForm;
import ru.kpfu.itis.kashapova.repository.ResumeRepository;
import ru.kpfu.itis.kashapova.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;

    private final Class<Resume> targetType = Resume.class;


    @Override
    public List<ResumeDto> getAll() {
        return resumeRepository.findAll().stream()
                .map(ResumeDto::to).collect(Collectors.toList());
    }

    @Override
    public void add(ResumeForm resumeForm) {
        Resume resume = conversionService.convert(resumeForm, targetType);
        User user = userRepository.getByLogin(resumeForm.getCreatorLogin())
                .orElseThrow(() -> new UsernameNotFoundException("User isn't found with login " + resumeForm.getCreatorLogin()));
        assert resume != null;
        resume.setOwner(user);
        resumeRepository.save(resume);
    }

    @Override
    public List<ResumeDto> getByUserEmail(String email) {
        return resumeRepository.getAllByOwnerEmail(email).stream()
                .map(ResumeDto::to).collect(Collectors.toList());
    }

    @Override
    public Optional<ResumeDto> getById(Long id) {
        return resumeRepository.findById(id).map(ResumeDto::to);
    }

    @Override
    public Optional<UserDto> getUserByUserEmail(String email) {
        return userRepository.getByEmail(email).map(UserDto::to);
    }

}
