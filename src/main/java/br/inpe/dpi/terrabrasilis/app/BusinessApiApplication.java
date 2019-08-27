package br.inpe.dpi.terrabrasilis.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import br.inpe.dpi.terrabrasilis.util.Constants;

@SpringBootApplication
@ComponentScan(basePackages = "br.inpe.dpi.terrabrasilis")
@EntityScan("br.inpe.dpi.terrabrasilis.domain")
@EnableMongoAuditing
@EnableReactiveMongoRepositories(basePackages = {
		"br.inpe.dpi.terrabrasilis.repository"
})
public class BusinessApiApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessApiApplication.class);
	private Environment environment;
	
	public BusinessApiApplication(Environment environment) {
		this.environment = environment;
	}
	
	/**
     * <p>
     * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile
     * <p>     
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) 
        		&& activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
            logger.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
    }
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BusinessApiApplication.class);
		Environment environment = application.run(args).getEnvironment(); 
		logApplicationStartup(environment);
	}

	private static void logApplicationStartup(Environment env) {
		String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.warn("The host name could not be determined, using `localhost` as fallback");
        }
        
        System.setProperty("host", String.format("%s://localhost:%s/%s", protocol, serverPort, contextPath));

        logger.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}{}\n\t" +
                "External: \t{}://{}:{}{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            "Business-API",
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath,
            env.getActiveProfiles());
	}
}

