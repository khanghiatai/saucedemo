package utils.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestConfig {
    private String baseUrl;
    private String password;
    private String standardUser;
    private String lockedOutUser;
    private String problemUser;
    private String performanceUser;
    private String errorUser;
    private String visualUser;
}