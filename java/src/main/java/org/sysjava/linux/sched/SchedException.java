package org.sysjava.linux.sched;

import org.sysjava.linux.memory.MLockException;

public class SchedException extends RuntimeException {

    private final Error error;

    public SchedException(final Error error) {
        this.error = error;
    }

    public enum Error {
        EPERM		( 1,"The calling thread does not have appropriate privileges"),
        EFAULT		(14,"Supplied memory address was invalid"),
        EINVAL		(22,"The affinity bit mask mask contains no processors that are currently physically on the system and permitted"),
        ESRCH		( 3,"The thread whose ID is pid could not be found");

        private final int errno;
        private final String desc;

        Error(final int errno, final String desc) {
            this.errno = errno;
            this.desc = desc;
        }

        public int getErrno() {
            return errno;
        }

        public String getDesc() {
            return desc;
        }

        public static SchedException.Error forErrno(final int errno) {
            for (final SchedException.Error error : SchedException.Error.values())
                if (errno == error.errno) return error;

            throw new IllegalArgumentException();
        }
    }


}
