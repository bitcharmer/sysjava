package org.sysjava.linux.memory;

import org.sysjava.linux.NativeError;

public enum MLockError implements NativeError {

    EPERM		( 1,"Operation not permitted. You need CAP_IPC_LOCK privilege"),
    EAGAIN		(11,"Some or all of the specified address range could not be locked"),
    ENOMEM		(12,"Tried to lock more memory than the limit permitted (RLIMIT_MEMLOCK)"),
    EINVAL		(22,"Invalid flags specified");


    private final int errno;
    private final String desc;

    MLockError(final int errno, final String desc) {
        this.errno = errno;
        this.desc = desc;
    }

    @Override
    public String mnemonic() {
        return name();
    }

    @Override
    public String desc() {
        return desc;
    }

    @Override
    public int errno() {
        return errno;
    }
}
