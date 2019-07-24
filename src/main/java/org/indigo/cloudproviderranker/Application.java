package org.indigo.cloudproviderranker;

import java.util.TimeZone;

import org.indigo.cloudproviderranker.config.properties.RuleEngineProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	RuleEngineProperties.class
})
public class Application {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(Application.class, args);
	}

}
