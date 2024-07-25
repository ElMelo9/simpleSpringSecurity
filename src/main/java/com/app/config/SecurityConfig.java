package com.app.config;

import com.app.service.UserDetailServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
//habilita la config de seguridad basada en anotaciones
@EnableMethodSecurity
public class SecurityConfig {



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        /*
//        HttpSecurity httpSecurity --> objeto que va a pasar por cada uno de los chain
//        */
//    return httpSecurity
//            .csrf(csrf -> csrf.disable())
//            //tipo de autentificacion
//            .httpBasic(Customizer.withDefaults())
//            //politica de autentificacion
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            //autorizacion de http request
//            .authorizeHttpRequests(http -> {
//                //endpoints public
//                http.requestMatchers(HttpMethod.GET,"/auth/hello").permitAll();
//
//                //endpoints con auth de lectura o escritura o personalizado
//                http.requestMatchers(HttpMethod.GET,"/auth/hello-security").hasAuthority("CREATE");
//
//
//                //endpoints no expecificados
//                http.anyRequest().denyAll();// denegar todos los demas
//
//                //http.anyRequest().authenticated(); // permitir solo a los user auth
//            })
//            .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /*
        HttpSecurity httpSecurity --> objeto que va a pasar por cada uno de los chain
        */
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                //tipo de autentificacion
                .httpBasic(Customizer.withDefaults())
                //politica de autentificacion
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        /*
        authenticationManager --> encargado de la administracion de la autentificacion
         */
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        /*
           componente para establecer el proveedor de los usuarios
         */
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //establecer encriptado de contrasenas
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        //establecer llamada a la bd para saber los usuarios
        authenticationProvider.setUserDetailsService(userDetailService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        /*
        aqui se establece la encriptacion de las contrasenas
         */
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        /*
//        aqui se establece la encriptacion de las contrasenas
//         */
//        return NoOpPasswordEncoder.getInstance();
//    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        /*
//        Aqui se estable el detalle de los usuarios(uno o muchos) nombre,password, roles y permisos
//         */
//
//        List<UserDetails> userDetailsList = new ArrayList<>();
//
//        userDetailsList.add( User.withUsername("ElMelo")
//                .password("efmelo")
//                .roles("ADMIN")
//                .authorities("READ","CREATE")
//                .build());
//
//        userDetailsList.add( User.withUsername("edwin")
//                .password("123456")
//                .roles("USER")
//                .authorities("READ")
//                .build());
//
//
//        return  new InMemoryUserDetailsManager(userDetailsList);
//    }
}
