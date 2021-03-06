package com.springboot.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.app.security.jwt.JwtAuthEntryPoint;
import com.springboot.app.security.jwt.JwtAuthTokenFilter;
import com.springboot.app.security.jwt.JwtUtils;
import com.springboot.app.security.services.UserDetailsServiceImpl;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    final private JwtAuthEntryPoint unauthorizedHandler;

    private final JwtUtils jwtUtils;

    public WebSecurityConfig (UserDetailsServiceImpl userDetailsService, JwtAuthEntryPoint unauthorizedHandler, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
    }
    
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // TODO
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            	.antMatchers("/api/auth/**", "/api/public/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            	.anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}


//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//       http.csrf().disable().authorizeRequests()
//               .antMatchers("/").permitAll() // C?? ngh??a l?? request "/" ko c???n ph???i ??c x??c th???c
//               .antMatchers(HttpMethod.POST, "/login").permitAll() // Request d???ng POST t???i "/login" lu??n ???????c ph??p truy c???p d?? l?? ???? authenticated hay ch??a
//               .anyRequest().authenticated() // C??c request c??n l???i ?????u c???n ???????c authenticated
//               .and()
//               // Add c??c filter v??o ???ng d???ng c???a ch??ng ta, th??? m?? s??? h???ng c??c request ????? x??? l?? tr?????c khi t???i c??c x??? l?? trong controllers.
//               // V??? th??? t??? c???a c??c filter, c??c b???n tham kh???o th??m t???i http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html m???c 7.3 Filter Ordering
//               .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) 
//               .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//   }
//
//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
//       
//       // M??nh comment ph???n d?????i n??y v?? ch??ng ta ko s??? d???ng DB nh??. N???u c??c b???n s??? d???ng, b??? comment v?? config query sao cho ph?? h???p. C??c b???n c?? th??? GG ????? t??m hi???u th??m
//       auth.jdbcAuthentication().dataSource(dataSource)
//               .usersByUsernameQuery("select username,password, enabled from users where username=?")
//               .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
//       
//   }
//}