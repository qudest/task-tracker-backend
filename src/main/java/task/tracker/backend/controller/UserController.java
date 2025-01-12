package task.tracker.backend.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import task.tracker.backend.dto.JwtResponse;
import task.tracker.backend.dto.UserDto;
import task.tracker.backend.dto.UserInfoDto;
import task.tracker.backend.service.AuthService;
import task.tracker.backend.service.UserService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserService userService;
    AuthService authService;

    @PostMapping("/user")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        //todo handling
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        String jwt = userService.createNewUser(userDto);
        return ResponseEntity.ok().header("Authorization", jwt).body(new JwtResponse(jwt));
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoDto> getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        //todo handling
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        String jwt = authService.login(userDto);
        return ResponseEntity.ok().header("Authorization", jwt).body(new JwtResponse(jwt));
    }

}
