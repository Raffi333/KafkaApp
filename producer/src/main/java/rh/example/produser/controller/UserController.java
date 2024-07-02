package rh.example.produser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rh.example.kafkaapp.model.UserEvent;
import rh.example.produser.model.UserError;
import rh.example.produser.service.UserService;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserEvent userEvent) {
        String userId = null;
        try {
            userId = userService.createUser(userEvent);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserError(new Date(), e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

}
