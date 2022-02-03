package org.sysjava;

import org.scijava.nativelib.NativeLoader;
import org.sysjava.linux.NativeError;
import org.sysjava.linux.memory.MmapError;

import java.io.IOException;
import java.util.function.LongPredicate;

import static org.sysjava.UnsafeAccess.UNSAFE;

public class SysJava {

    private static boolean loaded;

    public static synchronized void loadNativeLib() {
        if (loaded) return;

        try {
            NativeLoader.loadLibrary("sysjava");
            loaded = true;
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
