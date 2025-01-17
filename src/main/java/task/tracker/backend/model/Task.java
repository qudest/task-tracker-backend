package task.tracker.backend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    UUID id;

    String title;

    String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(nullable = false)
    boolean isCompleted = false;

    Instant completedAt;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (isCompleted) {
            completedAt = Instant.now();
        } else {
            completedAt = null;
        }
    }

}
