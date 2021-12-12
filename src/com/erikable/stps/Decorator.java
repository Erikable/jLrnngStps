package com.erikable.stps;

public class Decorator {
    public static void main(String[] args) {
        new Thread(new ClassTwo(new AgainNewClass(new ClassOne()))).start();
    }

    public static class ClassOne implements Runnable{
        @Override
        public void run() {
            System.out.println("Last message from Class-One");
        }
    }

    public static class ClassTwo implements Runnable{
        private Runnable runnable;

        public ClassTwo(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {
            System.out.println("First message from ClassTwo");
            runnable.run();
        }
    }

    public static class AgainNewClass implements Runnable{
        private Runnable runnable;

        public AgainNewClass(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {
            System.out.println("Second message from AgainNewClass");
            runnable.run();
        }
    }

}
