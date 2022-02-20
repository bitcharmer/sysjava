package org.sysjava.linux.memory;

import org.sysjava.SysJava;
import org.sysjava.UnsafeAccess;
import org.sysjava.linux.NativeError;
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
        NativeError.checkError(errnoAddress, result, (r) -> r != 0, MLockError.class);
    }

    public static void mlock2(final long addr, final int len, final int flags) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = mlock2(addr, len, flags, errnoAddress);
        NativeError.checkError(errnoAddress, result, (r) -> r != 0, MLockError.class);
    }

    public static void munlock(final long addr, final int len) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = munlock(addr, len, errnoAddress);
        NativeError.checkError(errnoAddress, result, (r) -> r != 0, MLockError.class);
    }

    public static void mlockall(final int flags) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = mlockall(flags, errnoAddress);
        NativeError.checkError(errnoAddress, result, (r) -> r != 0, MLockError.class);
    }

    public static void munlockall() {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = munlockall(errnoAddress);
        NativeError.checkError(errnoAddress, result, (r) -> r != 0, MLockError.class);
    }

    private static native int mlock(final long addr, final int len, final long errnoAddress);
    private static native int mlock2(final long addr, final int len, final int flags, final long errnoAddress);
    private static native int munlock(final long addr, final int len, final long errnoAddress);
    private static native int mlockall(final int flags, final long errnoAddress);
    private static native int munlockall(final long errnoAddress);

}
