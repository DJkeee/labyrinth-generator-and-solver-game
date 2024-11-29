package backend.academy.inputs.validate;

public interface Validator<T> {
    boolean isValid(T t);
}
