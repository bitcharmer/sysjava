import org.sysjava.linux.memory.MLock;
import org.sysjava.linux.memory.Mmap;
import org.sysjava.linux.sched.Sched;
import org.sysjava.linux.time.Clock;

public class NativeTest {

    public static void main(String[] args) {
        MLock.mlockall(MLock.Flags.MCL_CURRENT | MLock.Flags.MCL_FUTURE);
        Mmap.mmap(null, 512, Mmap.Prot.PROT_WRITE | Mmap.Prot.PROT_READ, Mmap.Flags.MAP_PRIVATE | Mmap.Flags.MAP_ANONYMOUS, 0);
        Sched.sched_setaffinity(0, 7);

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

    private static void testClock(Clock.Type clockType) {
        final long ts = Clock.clock_gettime(clockType);
        System.out.printf("Clock type: %s: %d\n", clockType, ts);
    }

}