package com.example.demo.graphql;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.CreateUserRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class UserGraphQLController {

    @QueryMapping
    public List<UserDTO> users(@Argument int first, @Argument String filter) {
        // Implementation here
        return null;
    }

    @QueryMapping
    public UserDTO user(@Argument String id) {
        // Implementation here
        return null;
    }

    @MutationMapping
    public UserDTO createUser(@Argument CreateUserRequest input) {
        // Implementation here
        return null;
    }

    @MutationMapping
    public UserDTO updateUser(@Argument String id, @Argument CreateUserRequest input) {
        // Implementation here
        return null;
    }

    @MutationMapping
    public Boolean deleteUser(@Argument String id) {
        // Implementation here
        return true;
    }

    @SubscriptionMapping
    public Flux<UserDTO> userUpdates(@Argument String userId) {
        // Implementation here
        return null;
    }
}