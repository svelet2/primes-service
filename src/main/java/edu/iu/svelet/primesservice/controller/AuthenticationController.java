package edu.iu.svelet.primesservice.controller;


import edu.iu.svelet.primesservice.model.Customer;
import edu.iu.svelet.primesservice.service.IAuthenticationService;
import edu.iu.svelet.primesservice.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"http://127.0.0.1:5500"})
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    IAuthenticationService authenticationService,
                                    TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer) {
        try{
            System.out.println("Registering customer: " + customer.getUsername());
            return authenticationService.register(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/login")
    public String login(@RequestBody Customer customer) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                customer.getUsername(),
                                customer.getPassword()
                        )
                );
        return tokenService.generateToken(authentication);
    }
}
