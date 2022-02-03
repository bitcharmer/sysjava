package org.sysjava.linux.sched;

import org.sysjava.linux.NativeError;

public enum SchedError implements NativeError {

    EPERM		( 1,"The calling thread does not have appropriate privileges"),
    EFAULT		(14,"Supplied memory address was invalid"),
    EINVAL		(22,"The affinity bit mask mask contains no processors that are currently physically on the system and permitted"),
    ESRCH		( 3,"The thread whose ID is pid could not be found");

    private final int errno;
    private final String desc;

    SchedError(final int errno, final String desc) {
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
