package org.vicykie.myapp.config.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.vicykie.myapp.config.auth.*;
import org.vicykie.myapp.config.auth.filter.StatelessAuthenticationFilter;
import org.vicykie.myapp.config.auth.filter.TokenAuthenticationProcessingFilter;
import org.vicykie.myapp.config.auth.handler.StatelessLoginFailureHandler;
import org.vicykie.myapp.config.auth.handler.StatelessLoginSuccessHandler;
import org.vicykie.myapp.util.AppPasswordEncoder;

import javax.annotation.PostConstruct;

/**
 * Created by vicykie on 2016/6/27.
 */
@EnableWebSecurity
@Configuration
//开启注解支持
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true) // to allow secure annotation on method to control access
//These are standards-based and allow simple role-based constraints to be applied but do not have the power Spring Security’s native annotations. To use the new expression-based syntax, you would use
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//    @Override
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        // ... create and return custom MethodSecurityExpressionHandler ...
//        return expressionHandler;
//    }
//}
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger logger = Logger.getLogger(SecurityConfig.class);


    @Autowired
    private StatelessLoginFailureHandler loginFailureHandler;
    @Autowired
    private StatelessLoginSuccessHandler loginSuccessHandler;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UnAuthenticationEntryPoint unAuthenticationEntryPoint;
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private UserAuthChecker userAuthChecker;

    @PostConstruct
    public void initSecurity() {
        logger.info("security init .....");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // unAuthentication
        http.exceptionHandling().accessDeniedPage("/401.html").authenticationEntryPoint(unAuthenticationEntryPoint);
        // session stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // resources matcher auth
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/role/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .anyRequest().authenticated();
        //csrf disabled
        http.csrf().disable().httpBasic();
        // custom JSON based authentication by POST of
        // {"username":"<name>","password":"<password>"} which sets the token header upon authentication
        TokenAuthenticationProcessingFilter filter = new TokenAuthenticationProcessingFilter("/auth/login"
                , this.authenticationManagerBean());
        filter.setAuthenticationFailureHandler(loginFailureHandler);
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        // custom Token based authentication based on the header previously given to the client
        //请求拦截，验证每个header
        http.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService, userAuthChecker),
                UsernamePasswordAuthenticationFilter.class);
        http.logout().clearAuthentication(true).logoutSuccessUrl("/?logout").deleteCookies("X-AUTH-TOKEN").logoutUrl("/auth/logout").permitAll();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.authenticationProvider(authenticationProviderBean());
    }

    @Bean
    public AuthenticationProvider authenticationProviderBean() {
        StatelessAuthenticationProvider provider = new StatelessAuthenticationProvider();
        provider.setPasswordEncoder(new AppPasswordEncoder("md5"));
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("salt");//数据库对应加盐的字段
        provider.setSaltSource(saltSource);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return userDetailsService;
    }
}
