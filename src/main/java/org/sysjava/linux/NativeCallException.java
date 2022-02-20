package org.sysjava.linux;

public class NativeCallException extends RuntimeException {

    public NativeCallException(final String mnemonic, final String desc, final int errno) {
        super(mnemonic + ": " + desc + ". ERRNO: " + errno);
    }
}
