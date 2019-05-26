package br.com.clairtonluz.oauth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.Filter;

@Configuration
@EnableOAuth2Client
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final Filter ssoFacebookFilter;
    private final Filter ssoGithubFilter;

    public WebSecurityConfigurer(Filter ssoFacebookFilter, Filter ssoGithubFilter) {
        this.ssoFacebookFilter = ssoFacebookFilter;
        this.ssoGithubFilter = ssoGithubFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**", "/error**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().addFilterBefore(ssoFacebookFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(ssoGithubFilter, BasicAuthenticationFilter.class)
        ;
    }

}
