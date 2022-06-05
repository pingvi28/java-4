package ru.kpfu.itis.kashapova.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatistic {

  private String login;
  private String email;
  private BigInteger resumeCount;
  private BigInteger adCount;
  private BigInteger teamCount;
}
