package org.sysjava.linux.memory;

import org.sysjava.SysJava;
import org.sysjava.UnsafeAccess;
import sun.misc.Unsafe;

public class MLock {

    static {
        SysJava.loadNativeLib();
    }

    public static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static class Flags {
        public static final int MCL_CURRENT         = 1;
        public static final int MCL_FUTURE          = 2;
        public static final int MCL_ONFAULT         = 4;
    }

    public static void mlock(final long addr, final int len) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = mlock(addr, len, errnoAddress);
        maybeThrow(errnoAddress, result);
    }

    public static void mlock2(final long addr, final int len, final int flags) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = mlock2(addr, len, flags, errnoAddress);
        maybeThrow(errnoAddress, result);
    }

    public static void munlock(final long addr, final int len) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = munlock(addr, len, errnoAddress);
        maybeThrow(errnoAddress, result);
    }

    public static void mlockall(final int flags) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = mlockall(flags, errnoAddress);
        maybeThrow(errnoAddress, result);
    }

    public static void munlockall() {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = munlockall(errnoAddress);
        maybeThrow(errnoAddress, result);
    }

    private static native int mlock(final long addr, final int len, final long errnoAddress);
    private static native int mlock2(final long addr, final int len, final int flags, final long errnoAddress);
    private static native int munlock(final long addr, final int len, final long errnoAddress);
    private static native int mlockall(final int flags, final long errnoAddress);
    private static native int munlockall(final long errnoAddress);

    private static void maybeThrow(final long errnoAddress, final long result) {
        if (result != 0) {
            final int errno = UNSAFE.getInt(errnoAddress);
            UNSAFE.freeMemory(errnoAddress);
            final MLockException.Error error = MLockException.Error.forErrno(errno);

            throw new MLockException(error);
        }
    }

}
