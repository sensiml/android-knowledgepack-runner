#include <jni.h>
#include <string>
#include <cinttypes>
#include <android/log.h>
#include <kb.h>
#include <testdata.h>

#define LOGI(...) \
  ((void)__android_log_print(ANDROID_LOG_INFO, "knowledgepack-wrapper::", __VA_ARGS__))
extern "C"
JNIEXPORT void JNICALL
Java_com_sensiml_knowledgepackrunner_KnowledgePackActivity_initSensiMLKnowledgePackModels(JNIEnv *env,
                                                                                          jclass clazz) {
    kb_model_init();
    LOGI("KB MODEL INIT");
}

extern "C"
JNIEXPORT int JNICALL
Java_com_sensiml_knowledgepackrunner_KnowledgePackActivity_runModel(JNIEnv *env,
                                                                            jobject call_object,
                                                                            jshortArray data,
                                                                            jint nsensors,
                                                                            jint model_index){
    return kb_run_model((SENSOR_DATA_T *)data, nsensors, model_index);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_sensiml_knowledgepackrunner_KnowledgePackActivity_runModelWithCascadeReset(JNIEnv *env,
                                                                                    jobject call_object,
                                                                                    jshortArray data,
                                                                                    jint nsensors,
                                                                                    jint model_index){
    return kb_run_model_with_cascade_reset((SENSOR_DATA_T *)data, nsensors, model_index);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_sensiml_knowledgepackrunner_KnowledgePackActivity_resetModel(JNIEnv *env,
                                                                      jobject call_object,
                                                                      jint model_index){
    kb_reset_model(model_index);
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_sensiml_knowledgepackrunner_KnowledgePackActivity_usingTestData(JNIEnv *env,
                                                                      jobject call_object){
#ifdef USE_TEST_RAW_SAMPLES
    return true;
#else
    return false;
#endif
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_sensiml_knowledgepackrunner_KnowledgePackActivity_runSensiMLKnowledgePackModelTestData(JNIEnv *env,
                                                                                                jobject call_object) {
#if defined(USE_TEST_RAW_SAMPLES)
    static int last_index;
    int ret = 0;
    int index;
    int num_sensors = 0; //unused
    SENSOR_DATA_T *pData;

    if(last_index >= TD_NUMROWS){
        last_index = 0;
    }

    for (index = last_index; index < TD_NUMROWS; index++) {
        pData = (SENSOR_DATA_T *) &testdata[index];
        //FILL_RUN_MODEL_MOTION
        //FILL_RUN_MODEL_AUDIO
        //FILL_RUN_MODEL_CUSTOM
    }
    return ret;
#else
    return -1;
#endif //USE_TEST_RAW_SAMPLES
}
