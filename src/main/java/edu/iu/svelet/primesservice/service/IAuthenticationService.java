package edu.iu.svelet.primesservice.service;

import edu.iu.svelet.primesservice.model.Customer;

import java.io.IOException;

public interface IAuthenticationService {
    boolean register(Customer customer) throws IOException;
    boolean login(String username, String password) throws IOException;
}