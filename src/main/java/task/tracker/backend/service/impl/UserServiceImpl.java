package task.tracker.backend.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.tracker.backend.dto.UserDto;
import task.tracker.backend.dto.UserInfoDto;
import task.tracker.backend.exception.EmailAlreadyTakenException;
import task.tracker.backend.exception.UserNotFoundException;
import task.tracker.backend.mapper.UserMapper;
import task.tracker.backend.model.User;
import task.tracker.backend.repository.UserRepository;
import task.tracker.backend.service.JwtService;
import task.tracker.backend.service.NotificationService;
import task.tracker.backend.service.UserService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    JwtService jwtService;
    PasswordEncoder encoder;
    UserMapper mapper = UserMapper.INSTANCE;
    NotificationService notificationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    log.warn("User not found: {}", username);
                    return new UserNotFoundException();
                });
    }

    @Transactional(rollbackFor = Exception.class)
    public String createNewUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.email())) {
            log.warn("Email is already taken: {}", userDto.email());
            throw new EmailAlreadyTakenException();
        }
        User user = mapper.toEntity(userDto);
        user.setPassword(encoder.encode(userDto.password()));
        userRepository.save(user);
        notificationService.sendWelcomeEmail(user.getEmail());
        return jwtService.generateToken(user);
    }

    public UserInfoDto getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mapper.toDto(user);
    }

}
