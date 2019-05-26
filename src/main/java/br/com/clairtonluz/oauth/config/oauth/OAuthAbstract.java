package br.com.clairtonluz.oauth.config.oauth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import javax.servlet.Filter;

public abstract class OAuthAbstract {

    Filter ssoFilter(OAuth2ClientContext oauth2ClientContext, String loginUrl,
                     AuthorizationCodeResourceDetails authorizationCodeResourceDetails,
                     ResourceServerProperties resourceServerProperties) {
        OAuth2ClientAuthenticationProcessingFilter filter =
                new OAuth2ClientAuthenticationProcessingFilter(loginUrl);
        OAuth2RestTemplate template = new OAuth2RestTemplate(authorizationCodeResourceDetails, oauth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
                authorizationCodeResourceDetails.getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }
}
