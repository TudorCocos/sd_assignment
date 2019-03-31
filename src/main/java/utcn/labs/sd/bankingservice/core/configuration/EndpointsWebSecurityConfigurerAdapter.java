package utcn.labs.sd.bankingservice.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Banking Service security configurations password is encoded with {@link BCryptPasswordEncoder}
 */
@Configuration
@EnableWebSecurity
public class EndpointsWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

//    @Value("${credentials.employee.username}")
//    private String employeeUsername;
//
//    @Value("${credentials.employee.password}")
//    private String employeePassword;
//
    @Value("${credentials.employee.role}")
    private String employeeRole;
//
//    @Value("${credentials.admin.username}")
//    private String adminUsername;
//
//    @Value("${credentials.admin.password}")
//    private String adminPassword;
//
    @Value("${credentials.admin.role}")
    private String adminRole;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        InMemoryUserDetailsManagerConfigurer authConfigurer = auth.inMemoryAuthentication()
                .passwordEncoder(encoder);
        authConfigurer.withUser(employeeUsername)
                .password(encoder.encode(this.employeePassword))
                .roles(employeeRole);
        authConfigurer.withUser(adminUsername)
                .password(encoder.encode(this.adminPassword))
                .roles(adminRole);
         */
        //System.out.println(passwordEncoder().encode("pass"));
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username as principal, password as credentials, true from employee_table where username = ?")
                .authoritiesByUsernameQuery("select username as principal, employee_type as role from employee_table where username = ?")
                .rolePrefix("ROLE_");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/bank/employee/**").hasRole(employeeRole)
                .antMatchers("/bank/admin/**").hasRole(adminRole).anyRequest().permitAll()
                .and()
                .httpBasic();
    }
}
