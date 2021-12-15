package org.sysjava;

import org.scijava.nativelib.NativeLoader;

import java.io.IOException;

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
