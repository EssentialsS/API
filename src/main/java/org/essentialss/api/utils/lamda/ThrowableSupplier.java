package org.essentialss.api.utils.lamda;

public interface ThrowableSupplier<M, T extends Throwable> {

    M get() throws T;
}
