import java.io.Serializable;

public class Call implements Serializable {
    private final String calling;
    private final String receiving;
    private final String start;
    private final int duration;

    public Call(String calling, String receiving, String start, String duration) {
        this.calling = calling;
        this.receiving = receiving;
        this.start = start;
        this.duration = Integer.parseInt(duration);
    }

    public String getCalling() {
        return calling;
    }

    public String getReceiving() {
        return receiving;
    }

    public String getStart() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Call{" +
                "calling='" + calling + '\'' +
                ", receiving='" + receiving + '\'' +
                ", start='" + start + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
