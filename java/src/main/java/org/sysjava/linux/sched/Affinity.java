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

    }

}
