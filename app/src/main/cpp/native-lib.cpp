#include <jni.h>
#include <string>
#include <algorithm>
#include <iostream>

using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_uz_orifjon_registrationnative_fragments_MainFragment_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    string hello = "Hello from C++";

    return env->NewStringUTF(hello.c_str());
}



string stringParse(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    string ret = string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

extern "C" JNIEXPORT jstring JNICALL
Java_uz_orifjon_registrationnative_fragments_RegisterFragment_encryption(JNIEnv *env, jobject /* this */,
     jstring login,
     jstring password) {
//    const char * q= env->GetStringUTFChars(login,0);
//    const char * a= env->GetStringUTFChars(password,0);
//    string text1 = q,text2 = a,result = "";
//    for(char i : text1){
//            int t = i + 3;
//        result+= to_string((char) (t));
//    }
//    for(char i : text2){
//        int t = i + 3;
//        result+= to_string((char) (t));
//    }

//    return (jstring)result.c_str();


    string text1 = stringParse(env,login);
    string text2 = stringParse(env,password);
    string result ="",text =  text1 + text2;

    for (int i = 0 ; i < text.size(); i ++){
        char l=text[i] + 3;
        result+= (char)l;
    }

    return env->NewStringUTF(result.c_str());
}


extern "C" JNIEXPORT jstring JNICALL
Java_uz_orifjon_registrationnative_fragments_MainFragment_encryption(JNIEnv *env, jobject /* this */,
                                                                         jstring login,
                                                                         jstring password) {
//    const char * q= env->GetStringUTFChars(login,0);
//    const char * a= env->GetStringUTFChars(password,0);
//    string text1 = q,text2 = a,result = "";
//    for(char i : text1){
//            int t = i + 3;
//        result+= to_string((char) (t));
//    }
//    for(char i : text2){
//        int t = i + 3;
//        result+= to_string((char) (t));
//    }

//    return (jstring)result.c_str();


    string text1 = stringParse(env,login);
    string text2 = stringParse(env,password);
    string result ="",text =  text1 + text2;

    for (int i = 0 ; i < text.size(); i ++){
        char l=text[i] + 3;
        result+= (char)l;
    }

    return env->NewStringUTF(result.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_uz_orifjon_registrationnative_fragments_MainFragment_decryption(JNIEnv *env,
                                                                     jobject /* this */,
                                                                     jstring info) {
    string a = "", c = "";
    c = (string) reinterpret_cast<const char *>(info);
    for (int i = 0; i < c.size(); i++) {
        int q = c[i] - 3;
        a += to_string((char) (q));
        cout << "a  ===========================>>>>>>>> " << to_string((char) ((int) c[i] + 3));
    }
    return env->NewStringUTF(a.c_str());
}


extern "C" JNIEXPORT jstring JNICALL
Java_uz_orifjon_registrationnative_fragments_MainFragment_decryption1(JNIEnv *env,
                                                                      jobject /* this */,
                                                                      jstring info) {
    const char * q = reinterpret_cast<const char *>(info);
    const char* cstring = env->GetStringUTFChars(info, 0);
    string a = stringParse(env,info);


}




