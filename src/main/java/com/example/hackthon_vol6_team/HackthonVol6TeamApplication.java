package com.example.hackthon_vol6_team;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class HackthonVol6TeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackthonVol6TeamApplication.class, args);
	}

	@Configuration
	public static class TomcatConfig {

		@Bean
		public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
			return server -> server.addAdditionalTomcatConnectors(createHttpConnector());
		}

		private Connector createHttpConnector() {
			Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
			connector.setScheme("http");
			connector.setPort(8080);  // HTTPポート
			connector.setSecure(false);
			connector.setRedirectPort(8443);  // HTTPSポートにリダイレクト
			return connector;
		}
	}

}
