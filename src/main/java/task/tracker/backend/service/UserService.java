package task.tracker.backend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.tracker.backend.dto.UserDto;
import task.tracker.backend.dto.UserInfoDto;
import task.tracker.backend.exception.EmailAlreadyTakenException;
import task.tracker.backend.model.User;
import task.tracker.backend.repository.UserRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    JwtService jwtService;
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //todo exception
        return userRepository.findByEmail(username).orElseThrow();
    }

    @Transactional(rollbackFor = Exception.class)
    public String createNewUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.email())) {
            log.warn("Email is already taken: {}", userDto.email());
            throw new EmailAlreadyTakenException();
        }
        User user = User.builder()
                .email(userDto.email())
                .password(encoder.encode(userDto.password()))
                .build();
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public UserInfoDto getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new UserInfoDto(user.getId(), user.getEmail());
    }

}
