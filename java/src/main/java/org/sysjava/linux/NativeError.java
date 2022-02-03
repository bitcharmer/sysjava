package org.sysjava.linux;

import java.util.function.LongPredicate;

import static org.sysjava.UnsafeAccess.UNSAFE;

@SuppressWarnings("rawtypes")
public interface NativeError {

    String mnemonic();
    String desc();
    int errno();

    static NativeError forErrno(final int errno, final Class<? extends NativeError> errorEnum) {
        for (final NativeError error : errorEnum.getEnumConstants()) {
            if (errno == error.errno() ) return error;
        }

        throw new IllegalArgumentException();
    }

    static void checkError(final long errnoAddress, final long result, final LongPredicate errorCheck, Class<? extends NativeError> errorClass) {
        try {
            if (errorCheck.test(result)) {
                final int errno = UNSAFE.getInt(errnoAddress);
                final NativeError nativeError = NativeError.forErrno(errno, errorClass);
                throw new NativeCallException(nativeError.mnemonic(), nativeError.desc(), nativeError.errno());
            }
        } finally {
            UNSAFE.freeMemory(errnoAddress);
        }
    }

    class NativeCallException extends RuntimeException {

        public NativeCallException(final String mnemonic, final String desc, final int errno) {
            super(mnemonic + ": " + desc + ". ERRNO: " + errno);
        }

    }

}
