package org.essentialss.api.utils.lamda;

public interface ThrowableFunction<O, M, T extends Throwable> {

    M map(O original) throws T;
}
