package com.zhoutao123.java.se.collect.pattern;

public class ProxyPattern {

    public static void main(String[] args) {
        Speak proxy = new SpeakProxy(new StudentSpeak());
        proxy.speak("Hello,Proxy!");


    }


    static class SpeakProxy implements Speak {

        private final Speak speak;

        public SpeakProxy(Speak speak) {
            this.speak = speak;
        }

        @Override
        public void speak(String content) {
            System.out.println("Proxy Before....");
            speak.speak(content);
            System.out.println("Proxy After....");
        }
    }

    static class StudentSpeak implements Speak {
        @Override
        public void speak(String content) {
            System.out.println("Student speak:" + content);
        }
    }

    static interface Speak {
        void speak(String content);
    }
}
