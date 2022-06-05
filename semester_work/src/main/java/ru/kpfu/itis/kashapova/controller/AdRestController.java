package ru.kpfu.itis.kashapova.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.kpfu.itis.kashapova.dto.AdDto;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.entity.User;
import ru.kpfu.itis.kashapova.exception.NotFoundException;
import ru.kpfu.itis.kashapova.repository.UserRepository;
import ru.kpfu.itis.kashapova.service.AdService;
import ru.kpfu.itis.kashapova.service.UserService;
import ru.kpfu.itis.kashapova.service.UserServiceImpl;

/**
* Аннотация @Controller указывает, что класс является «Контроллером», например, веб-контроллером, в то время @RestController примечание @RestController указывает, что класс является контроллером, где методы @ResponseBody по умолчанию принимают семантику @ResponseBody , то есть обслуживают REST API.
**/

@RestController("/api/ads")
@PreAuthorize("permitAll()")
public class AdRestController {

  @Autowired
  private AdService adService;

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<AdDto>> getAds() {
    return ResponseEntity.ok(adService.getAllWhereStatusIsActive());
  }

  //@GetMapping("/{email}")

  @RequestMapping("/{email}")
  @ResponseBody
  public ResponseEntity<List<AdDto>> getMyAd(@PathVariable("email") String email) {
    Long id = (long) -1;
    try {
      id = Long.parseLong(email);
    }catch (IllegalArgumentException ex){
      throw new NotFoundException("it not id");
    }

    if(adService.getByIdRest(id) != null){
      return ResponseEntity.ok(adService.getByIdRest(id));
    }
    return ResponseEntity.ok(adService.getByEmail(email));
  }
}

/*
  @RequestMapping("/{email}")
  @ResponseBody
  public ResponseEntity<List<AdDto>> getMyAd(@PathVariable("email") String email) {
    return ResponseEntity.ok(adService.getByEmail(email));
  }

  // с мапингом боль ==   @GetMapping("adId/{adId}")
  //@GetMapping(path="/{adId}")

  @RequestMapping("/{adId}")
  @ResponseBody
  public ResponseEntity<AdDto> getAdPage(@PathVariable("adId") Long id) {
    Optional<AdDto> adDto = adService.getById(id);
    if (adDto.isPresent()) {
      return ResponseEntity.ok(adDto.get());
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ad isn't found");
  }
* */
