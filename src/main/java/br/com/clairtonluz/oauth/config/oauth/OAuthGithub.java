package br.com.clairtonluz.oauth.config.oauth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

import javax.servlet.Filter;

@Configuration
public class OAuthGithub extends OAuthAbstract {

    @Bean("ssoGithubFilter")
    public Filter ssoGithubFilter(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext) {
        return ssoFilter(oauth2ClientContext, "/login/github", github());
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }


}
