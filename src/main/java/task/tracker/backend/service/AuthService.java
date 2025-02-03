package task.tracker.backend.service;

import task.tracker.backend.dto.UserDto;

public interface AuthService {

    String login(UserDto userDto);

}
