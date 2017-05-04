// FunGenerator.aidl
package com.example.ktang.funcommon;

// Declare any non-default types here with import statements

interface FunGenerator {
    Bitmap image(int imgNum);
    void playClip(int clipNum);
    void pause();
    void resume();
    void stop();
}
