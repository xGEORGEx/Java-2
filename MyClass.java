package ru.geekbrains.J2L2;

//Создаём класс, реализующий AutoCloseable
public class MyClass implements AutoCloseable {
    private int x;
    private int y;



    MyClass (int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY (){
        return y;
    }

    @Override
    public void close() throws Exception {
        int rezult;
        rezult = x / y;
        System.out.println("Результат:");
        System.out.println(rezult);
    }
}
