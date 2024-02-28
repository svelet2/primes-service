package edu.iu.svelet.primesservice.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class PrimesService implements IPrimesService {

        @Override
        public boolean isPrime(long n) {
            if (n == 2){
                return true;
            }
            for(long i = 2L; i < n; i++){
                if(n % i == 0){
                    return false;
                }
            }
            return true;
        }
}
