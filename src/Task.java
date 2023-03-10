import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private static int idGenerator = 1;
    String tittle;
    Type type;
    int id;
    LocalDate dateTime;
    RepeatPeriod repeatPeriod;
    String description;

    enum Type {
        work,
        personal
    }

    ;

    public Task(String tittle,
                Type type,
                LocalDate dateTime,
                RepeatPeriod repeatPeriod) {
        this.tittle = tittle;
        this.type = type;
        this.dateTime = dateTime;
        this.repeatPeriod = repeatPeriod;
        this.id = idGenerator++;
    }

    public Task(String tittle,
                Type type,
                LocalDate dateTime,
                RepeatPeriod repeatPeriod,
                String description) {
        this.tittle = tittle;
        this.type = type;
        this.dateTime = dateTime;
        this.description = description;
        this.repeatPeriod = repeatPeriod;
        this.id = idGenerator++;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tittle,
                type,
                id,
                dateTime,
                description);
    }

    @Override
    public String toString() {
        return dateTime + " " +
                tittle + " " +
                (getType() == Type.work ? "(Work " : "(Personal ") +
                RepeatPeriod.valueOf(String.valueOf(repeatPeriod)) + ") " +
                (getDescription() == null || getDescription().isEmpty() || getDescription().isBlank()
                        ? "No description"
                        : description);
    }

    public String toStringWithID() {
        return "ID:" + id + " " +
                this;
    }
}
