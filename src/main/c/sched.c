#define _GNU_SOURCE

#ifndef _Included_org_sysjava_os_Clock
#define _Included_org_sysjava_os_Clock

#include <jni.h>
#include <sched.h>


#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_org_sysjava_linux_sched_Sched_sched_1setaffinity(JNIEnv *env, jclass cls, jint pid, jint cpu, jint errnoAddr) {
    long long cpu_mask = 1UL << cpu;
    return sched_setaffinity(pid, sizeof(cpu_mask), (const cpu_set_t *) &cpu_mask);
}

#ifdef __cplusplus
}
#endif
#endif