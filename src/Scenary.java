
enum State {
    Greeting,
    Equipment,
    Config,
    Battle,
    End
}

public class Scenary {
    private State state;

    public Scenary(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
