#define _GNU_SOURCE

#ifndef _Included_org_sysjava_os_memory_MLock
#define _Included_org_sysjava_os_memory_MLock

#include <sys/mman.h>
#include <errno.h>
#include <jni.h>
#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_org_sysjava_linux_memory_MLock_mlock(JNIEnv *env, jclass cls, jlong addr, jint len, jlong errnoAddr) {
    int result = mlock(addr, len);
    *(int32_t*) errnoAddr = errno;
    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_linux_memory_MLock_mlock2(JNIEnv *env, jclass cls, jlong addr, jint len, jint flags, jlong errnoAddr) {
    int result = mlock2(addr, len, flags);
    *(int32_t*) errnoAddr = errno;
    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_linux_memory_MLock_munlock(JNIEnv *env, jclass cls, jlong addr, jint len, jlong errnoAddr) {
    int result = munlock(addr, len);
    *(int32_t*) errnoAddr = errno;
    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_linux_memory_MLock_mlockall(JNIEnv *env, jclass cls, jint flags, jlong errnoAddr) {
    int result = mlockall(flags);
    *(int32_t*) errnoAddr = errno;
    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_linux_memory_MLock_munlockall(JNIEnv *env, jclass cls, jlong errnoAddr) {
    int result = munlockall();
    *(int32_t*) errnoAddr = errno;
    return result;
}

#ifdef __cplusplus
}
#endif
#endif