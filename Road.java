package ru.geekbrains.J2L1;

public class Road extends Obstacle {

    protected Road(int size) {
        super(size);
    }

    @Override
    public void doIt(Animal a) {
        a.run(size);
    }
}
