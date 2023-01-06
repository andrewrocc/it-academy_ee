package my.first.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "my.first")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth,
                                        @Qualifier("authService") AuthenticationService service) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/products").permitAll()
                .antMatchers(HttpMethod.POST, "/add*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/edit*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/delete*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/list*").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin();
    }
}
