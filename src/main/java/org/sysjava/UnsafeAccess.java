package org.sysjava;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

public class UnsafeAccess {

    public static final Unsafe UNSAFE = getUnsafe();

    private static Unsafe getUnsafe() {
        Unsafe unsafe;
        try {
            unsafe = AccessController.doPrivileged((PrivilegedExceptionAction<Unsafe>) () ->  {
                final Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                return (Unsafe) field.get(null);
            });
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
        return unsafe;
    }

}