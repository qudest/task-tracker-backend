package task.tracker.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import task.tracker.backend.dto.UserDto;
import task.tracker.backend.dto.UserInfoDto;

public interface UserService extends UserDetailsService {

    String createNewUser(UserDto userDto);

    UserInfoDto getCurrentUser();

}
