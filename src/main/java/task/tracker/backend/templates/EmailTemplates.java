package task.tracker.backend.templates;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplates {

    public static final String WELCOME_EMAIL_SUBJECT = "Welcome to Task Tracker – Let’s Get Started!";
    public static final String WELCOME_EMAIL_MESSAGE = """
            Hello %s,
            
            Welcome to Task Tracker! 🎉 We’re thrilled to have you on board.
            
            Task Tracker is designed to help you stay organized, boost productivity, and manage your tasks effortlessly. Here’s what you can do next:
            
            ✅ Set up your first task and track your progress.
            ✅ Explore our features to stay on top of your goals.
            ✅ Stay connected with notifications and reminders.
            
            If you have any questions, feel free to reach out to our support team.
            
            Let’s make productivity effortless! 🚀
            
            Best wishes,
            The Task Tracker Team
            """;

}
