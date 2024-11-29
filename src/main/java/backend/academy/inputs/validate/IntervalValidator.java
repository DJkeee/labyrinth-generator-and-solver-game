package backend.academy.inputs.validate;

public class IntervalValidator implements Validator<Integer> {
    final private int min;
    final private int max;

    public IntervalValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(Integer input) {
        return input >= min && input <= max;
    }
}
