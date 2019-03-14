package ru.geekbrains.J2L5;

import java.util.Arrays;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        withoutThread();
        withThread();

    }

    private static void withoutThread(){
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        transformArr(arr);
        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void withThread() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);
        //создаём новый потов в который передаём массив a2
        Thread thread = new Thread(new MyThread(a2));
        //запускаем поток
        thread.start();
        //в основном потоке выполняем пересчет массива
        transformArr(a1);
        //жем завершения выполнения потока thread
        //что бы объеденить массивы
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //объединяем массивы
        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(MyThread.arr, 0, arr, HALF, HALF);
        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);
        System.out.println(arr[9999999]);
    }

    //метод в котором пересчитываем исходный массив
    private static void transformArr (float[] arr){
        for (int i = 0; i < arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }


    static class MyThread implements Runnable {
        static float[] arr;

        //конструктор класса в который передаем в качестве параметра массив
        MyThread(float[] arr) {
            MyThread.arr = arr;
        }


        @Override
        public void run() {
            transformArr(arr);
        }
    }

}
