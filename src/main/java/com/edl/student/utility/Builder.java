package com.edl.student.utility;

import java.util.function.BiConsumer;
import java.util.function.Supplier;
/**
* generic build class, see BuilderTest for an example
*/
public class Builder<T> {
    private final Supplier<T> supplier;

    private Builder(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    public static <T> Builder<T> of(Supplier<T> supplier) {
        return new Builder<>(supplier);
    }

    public <P> Builder<T> with(BiConsumer<T, P> consumer, P value) {
        return new Builder<>(() -> {
            T obj = supplier.get();
            consumer.accept(obj, value);
            return obj;
        });
    }
    public T build() {
        return supplier.get();
    }
}