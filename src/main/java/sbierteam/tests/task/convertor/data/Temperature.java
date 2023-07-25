package sbierteam.tests.task.convertor.data;

public abstract class Temperature {
    protected int value;

    protected Temperature(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
