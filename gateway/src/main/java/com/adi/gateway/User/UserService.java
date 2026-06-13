package com.adi.gateway.User;

import com.adi.gateway.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    
    private WebClient webClient;

    @Autowired
    public UserService(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.build();
    }

    public Mono<Boolean> validateUser(String keyCloakId){
        return webClient.get()
                .uri("http://userservice/api/users/validate/"+keyCloakId)
                .retrieve().bodyToMono(Boolean.class);
    }

    public Mono<UserDTO> registerUser(UserDTO registerRequest) {
        return webClient.post()
                .uri("http://userservice/api/users/register")
                .bodyValue(registerRequest)
                .retrieve().bodyToMono(UserDTO.class);
    }
}
