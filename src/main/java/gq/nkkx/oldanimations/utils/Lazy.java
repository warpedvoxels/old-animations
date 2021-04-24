package gq.nkkx.oldanimations.utils;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

    private Supplier<T> delegate;
    private T value;

    private Lazy(Supplier<T> delegate) {
        this.delegate = delegate;
    }

    public static <T> Lazy<T> create(Supplier<T> delegate) {
        return new Lazy<>(delegate);
    }

    public T get() {
        if (delegate != null) {
            value = this.delegate.get();
            delegate = null;
        }
        return value;
    }

}
