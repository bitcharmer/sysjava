#include <jni.h>
#include <sched.h>

#ifndef _Included_org_sysjava_os_Clock
#define _Included_org_sysjava_os_Clock

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_org_sysjava_os_sched_Affinity_setAffinity(JNIEnv *env, jclass cls, jint cpu, jint errnoAddr) {
    long long cpu_mask = 1UL << cpu;
    sched_setaffinity(0, sizeof(cpu_mask), (const cpu_set_t *) &cpu_mask);
}

#ifdef __cplusplus
}
#endif
#endif