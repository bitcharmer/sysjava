package org.sysjava.linux.sched;

import org.sysjava.SysJava;
import org.sysjava.UnsafeAccess;
import sun.misc.Unsafe;

public class Affinity {

    static {
        SysJava.loadNativeLib();
    }

    public static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static void sched_setaffinity(final int pid, final int cpu) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = sched_setaffinity(pid, cpu, errnoAddress);

        maybeThrow(errnoAddress, result);
    }

    private static native int sched_setaffinity(final int pid, final int cpu, final long errnoAddress);

    private static void maybeThrow(final long errnoAddress, final long result) {
        if (result != 0) {
            final int errno = UNSAFE.getInt(errnoAddress);
            UNSAFE.freeMemory(errnoAddress);
            final SchedException.Error error = SchedException.Error.forErrno(errno);

            throw new SchedException(error);
        }
    }

}
