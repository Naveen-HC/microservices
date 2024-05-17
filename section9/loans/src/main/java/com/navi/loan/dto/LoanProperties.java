package com.navi.loan.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loan")
@Setter @Getter
public class LoanProperties {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
