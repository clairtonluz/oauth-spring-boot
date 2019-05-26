package br.com.clairtonluz.oauth.config.oauth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import javax.servlet.Filter;

@Configuration
public class OAuthFacebook extends OAuthAbstract {


    @Bean("ssoFacebookFilter")
    public Filter ssoGithubFilter(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext) {
        return ssoFilter(oauth2ClientContext, "/login/facebook", facebook(), facebookResource());
    }

    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }

}
