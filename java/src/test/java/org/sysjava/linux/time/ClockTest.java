package org.sysjava.linux.time;

import org.junit.Test;

import java.lang.reflect.Field;

public class ClockTest {

    @Test
    public void testAllClocks() {
        testClock(Clock.Type.BOOTTIME);
        testClock(Clock.Type.BOOTTIME_ALARM);
        testClock(Clock.Type.MONOTONIC);
        testClock(Clock.Type.MONOTONIC_COARSE);
        testClock(Clock.Type.MONOTONIC_RAW);
        testClock(Clock.Type.PROCESS_CPUTIME_ID);
        testClock(Clock.Type.REALTIME);
        testClock(Clock.Type.REALTIME_ALARM);
        testClock(Clock.Type.REALTIME_COARSE);
        testClock(Clock.Type.TAI);
        testClock(Clock.Type.THREAD_CPUTIME_ID);
    }

    private void testClock(Clock.Type clockType) {
        final long ts = Clock.clock_gettime(clockType);
        System.out.printf("Clock type: %s: %d\n", clockType, ts);
    }

}