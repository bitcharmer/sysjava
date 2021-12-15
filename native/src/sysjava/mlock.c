#include <sys/mman.h>
#include <errno.h>
#include <jni.h>

#ifndef _Included_org_sysjava_os_memory_MLock
#define _Included_org_sysjava_os_memory_MLock
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_org_sysjava_os_memory_MLock_mlock(JNIEnv *env, jclass cls, jlong addr, jint len, jint errnoAddr) {
    int result = mlock(addr, len);
    *(long int*) errnoAddr = errno;
    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_os_memory_MLock_mlock2(JNIEnv *env, jclass cls, jlong addr, jint len, jint flags, jint errnoAddr) {
    int result = mlock2(addr, len, flags);
    *(long int*) errnoAddr = errno;
    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_os_memory_MLock_munlock(JNIEnv *env, jclass cls, jlong addr, jint len, jint errnoAddr) {
    int result = munlock(addr, len);
    *(long int*) errnoAddr = errno;
    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_os_memory_MLock_mlockall(JNIEnv *env, jclass cls, jint flags, jint errnoAddr) {
    int result = mlockall(flags);
    *(long int*) errnoAddr = errno;
    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_os_memory_MLock_munlockall(JNIEnv *env, jclass cls, jint errnoAddr) {
    int result = munlockall();
    *(long int*) errnoAddr = errno;
    return result;
}

#ifdef __cplusplus
}
#endif
#endif