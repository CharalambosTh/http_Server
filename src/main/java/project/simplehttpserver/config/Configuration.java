package project.simplehttpserver.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    private int port;
    private String webroot;

}
