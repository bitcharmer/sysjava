package org.sysjava.linux.sched;

import org.sysjava.SysJava;
import org.sysjava.UnsafeAccess;
import org.sysjava.linux.NativeError;
import sun.misc.Unsafe;

public class Affinity {

    static {
        SysJava.loadNativeLib();
    }

    public static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static void sched_setaffinity(final int pid, final int cpu) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int result = sched_setaffinity(pid, cpu, errnoAddress);
        NativeError.checkError(errnoAddress, result, (r) -> r != 0, SchedError.class);
    }

    private static native int sched_setaffinity(final int pid, final int cpu, final long errnoAddress);
}
