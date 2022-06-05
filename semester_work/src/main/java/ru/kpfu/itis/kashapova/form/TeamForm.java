package ru.kpfu.itis.kashapova.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamForm {

    private String name;
    private String creatorLogin;
}
