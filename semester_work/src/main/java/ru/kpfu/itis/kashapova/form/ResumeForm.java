package ru.kpfu.itis.kashapova.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForm {

    private String header;
    private String description;
    private String contact;
    private String creatorLogin;
}
