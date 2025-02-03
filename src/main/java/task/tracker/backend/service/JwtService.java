package task.tracker.backend.service;

import task.tracker.backend.model.User;

public interface JwtService {

    String generateToken(User user);

    boolean validateToken(String token);

    String getUsernameFromToken(String token);

}
