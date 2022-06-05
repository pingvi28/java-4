package ru.kpfu.itis.kashapova.service;

import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.entity.enums.LoginMethod;
import ru.kpfu.itis.kashapova.entity.enums.Role;
import ru.kpfu.itis.kashapova.entity.User;
import ru.kpfu.itis.kashapova.form.LoginForm;
import ru.kpfu.itis.kashapova.repository.UserRepository;

@Service
public class TokenServiceImpl implements TokenService {

  @Value("${oauth.vk.client_secret}")
  private String client_secret;

  @Value("${oauth.vk.client_id}")
  private String client_id;

  @Value("${oauth.vk.domain}")
  private String domain;

  @Value("${server.port}")
  private String port;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  @SneakyThrows
  public LoginForm authenticate(String code) {
    String accessToken = getAccessToken(code);
    return getUserFromApi(accessToken);
  }

  @SneakyThrows
  private String getAccessToken(String code) {
    URL vkAathUrl = new HttpUrl.Builder()
        .scheme("https")
        .host("oauth.vk.com")
        .addPathSegment("access_token")
        .addQueryParameter("client_id", client_id)
        .addQueryParameter("client_secret", client_secret)
        .addQueryParameter("redirect_uri", domain + port + "/signIn/token")
        .addQueryParameter("code", code)
        .build()
        .url();
    try (ResponseBody authBody = new OkHttpClient().newCall(
        new Request.Builder().url(vkAathUrl)
            .build()).execute().body()) {
      JsonNode jsonNode = objectMapper.readTree(authBody.string());
      return jsonNode.get("access_token").asText();
    }
  }

  @SneakyThrows
  private LoginForm getUserFromApi(String accessToken) {
    URL userInfoUrl = new HttpUrl.Builder()
        .scheme("https")
        .host("api.vk.com")
        .addPathSegment("method")
        .addPathSegment("users.get")
        .addQueryParameter("v", "5.131")
        .addQueryParameter("fields", "sex,screen_name,email")
        .addQueryParameter("access_token", accessToken).build().url();
    try (ResponseBody userBody = new OkHttpClient().newCall(
        new Request.Builder().url(userInfoUrl)
            .build()).execute().body()) {
      JsonNode node = objectMapper.readTree(userBody.string()).get("response").elements().next();

      String login = node.get("screen_name").asText();
      int genderValue = node.get("sex").asInt();
      //String gender = (genderValue == 1) ? "female" : (genderValue == 2 ? "male" : "undefined");

      //email вытащить
      User user = User.builder()
          .login(login)
          .role(Role.USER)
              .email("kashapyara2@gmail.com")
              .password(accessToken)
          .way(LoginMethod.OAUTH)
          .build();
      userRepository.save(user);
      return LoginForm.builder().email(login).password(accessToken).build();
    }
  }
}
