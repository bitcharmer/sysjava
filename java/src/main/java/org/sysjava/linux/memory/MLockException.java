package org.sysjava.linux.memory;

public class MLockException extends RuntimeException {

    public enum Error {
        EPERM		( 1,"Operation not permitted. You need CAP_IPC_LOCK privilege"),
        EAGAIN		(11,"Some or all of the specified address range could not be locked"),
        ENOMEM		(12,"Tried to lock more memory than the limit permitted (RLIMIT_MEMLOCK)"),
        EINVAL		(22,"Invalid flags specified");


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

        public static Error forErrno(final int errno) {
            for (final Error error : Error.values())
                if (errno == error.errno) return error;

            throw new IllegalArgumentException();
        }
    }


    private final Error error;

    public MLockException(final Error error) {
        this.error = error;
    }

    public MLockException(final String message, Throwable cause) {
        super(message, cause);
        this.error = null;
    }

    @Override
    public String toString() {
        return "MLockException{" +
                "errno=" + error.name() +
                ", desc=" + error.desc +
                '}';
    }
}
