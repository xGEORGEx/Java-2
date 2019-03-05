package ru.geekbrains.J2L1;

public class Wall extends Obstacle {
    public Wall(int size) {
        super(size);
    }

    @Override
    public void doIt(Animal a) {
        if (a instanceof Jump) {
            ((Jump) a).doJump(size);
        }
    }
}
