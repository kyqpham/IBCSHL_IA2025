public class Task {
    private String name;
    private int priority;
    private String description;
    private double deadline;
    private int duration;

    // constructors
    public Task() {
        name = " ";
        priority = 0;
        description = " ";
        deadline = 0.0;
        duration = 0;
    }

    public Task(String name) {
        this.name = name;
        priority = 0;
        description = " ";
        deadline = 0.0;
        duration = 0;
    }

    public Task (String name, int priority) {
        this.name = name;
        this.priority = priority;
        description = " ";
        deadline = 0.0;
        duration = 0;
    }
    
    public Task (String name, int priority, String description) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        deadline = 0.0;
        duration = 0;
    }

    public Task (String name, int priority, String description, double deadline) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.deadline = deadline;
        duration = 0;
    }

    public Task (String name, int priority, String description, double deadline, int duration) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.deadline = deadline;
        this.duration = duration;
    }

    // necessary methods
    public String getName() {
        return name;
    }

    public int getPriority () {
        return priority;
    }

    public String getDescription () {
        return description;
    }

    public double getDeadline () {
        return deadline;
    }

    public int getDuration () {
        return duration;
    }
}
