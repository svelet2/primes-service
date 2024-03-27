package edu.iu.svelet.primesservice.service;

import edu.iu.svelet.primesservice.model.Customer;
import edu.iu.svelet.primesservice.repository.IAuthenticationRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService {
    IAuthenticationRepository authenticationRepository;

    public AuthenticationService(IAuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Customer customer = authenticationRepository.findByUsername(username);
            if(customer == null){
                throw new UsernameNotFoundException("");
            }
            return User
                    .withUsername(username)
                    .password(customer.getPassword())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean register(Customer customer) throws IOException {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String passwordEncoded = bc.encode(customer.getPassword());
        customer.setPassword(passwordEncoded);
        System.out.println("Registering customer: " + customer.getUsername());
        return authenticationRepository.save(customer);
    }

    @Override
    public boolean login(String username, String password) throws IOException {
        return false;
    }
}