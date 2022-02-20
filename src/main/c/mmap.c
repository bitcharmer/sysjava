#define _GNU_SOURCE

#ifndef _Included_org_sysjava_os_mmap_Mmap
#define _Included_org_sysjava_os_mmap_Mmap

#include <sys/mman.h>
#include <errno.h>
#include <jni.h>
#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jlong JNICALL Java_org_sysjava_linux_memory_Mmap_mmap(JNIEnv *env, jclass cls, jint fd, jlong size, jint prot, jint flags, jlong offset, jlong errnoAddr) {
    void *result = mmap(NULL, size, prot, flags, fd, offset);
    *(int32_t*) errnoAddr = errno;

    return result;
}

JNIEXPORT jint JNICALL Java_org_sysjava_linux_memory_Mmap_unmap(JNIEnv *env, jclass cls, jlong addr, jlong size, jlong errnoAddr) {
    int result = munmap(addr, size);
    *(int32_t*)(errnoAddr) = errno;

    return result;
}

#ifdef __cplusplus
}
#endif
#endif

