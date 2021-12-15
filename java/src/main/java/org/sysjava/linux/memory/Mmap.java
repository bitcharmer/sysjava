package org.sysjava.linux.memory;

import org.sysjava.SysJava;
import org.sysjava.UnsafeAccess;
import sun.misc.Unsafe;

import java.io.FileDescriptor;
import java.lang.reflect.Field;

public class Mmap {

    static {
        SysJava.loadNativeLib();
    }

    public static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static class Prot {
        public static final int PROT_NONE	        = 0x0;
        public static final int PROT_READ           = 0x1;
        public static final int PROT_WRITE	        = 0x2;
        public static final int PROT_EXEC	        = 0x4;
    }

    public static class Flags {
        public static final int MAP_SHARED          = 0x01;
        public static final int MAP_PRIVATE         = 0x02;
        public static final int MAP_FIXED           = 0x10;
        public static final int MAP_ANONYMOUS       = 0x20;
        public static final int MAP_GROWSDOWN       = 0x00100;
        public static final int MAP_LOCKED          = 0x02000;
        public static final int MAP_NONBLOCK        = 0x10000;
        public static final int MAP_NORESERVE       = 0x04000;
        public static final int MAP_POPULATE        = 0x08000;
        public static final int MAP_HUGETLB         = 0x40000;
    }

    public static long mmap(final FileDescriptor fileDesc, final long size, final int prot, final int flags, final long offset) {
        final long errnoAddress = UNSAFE.allocateMemory(4);
        final int fd = getFD(fileDesc);
        final long result = mmap(fd, size, prot, flags, offset, errnoAddress);
        maybeThrow(errnoAddress, result);

        return result;
    }

    public static void munmap(final long address, final long size) {
        final long errnoAddress = UNSAFE.allocateMemory(4);

        final int result = unmap(address, size, errnoAddress);
        maybeThrow(errnoAddress, result);
    }

    private static int getFD(final FileDescriptor fileDesc) {
        if (fileDesc == null)
            return -1;

        try {
            final Field fdField = FileDescriptor.class.getDeclaredField("fd");
            fdField.setAccessible(true);
            return (int) fdField.get(fileDesc);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new MmapException("Unable to obtain integer fd for file descriptor " + fileDesc, e);
        }
    }

    private static void maybeThrow(final long errnoAddress, final long result) {
        if (result == -1) {
            final int errno = UNSAFE.getInt(errnoAddress);
            UNSAFE.freeMemory(errnoAddress);
            final MmapException.Error error = MmapException.Error.forErrno(errno);
            throw new MmapException(error);
        }
    }

    private static native long mmap(int fd, long size, int prot, int flags, long offset, long errnoAddress);
    private static native int unmap(long address, long size, long errnoAddress);

}
