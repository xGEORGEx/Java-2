package ru.geekbrains.J2L1;

//Создаём класс Course
public class Course {
    //Создаём поле с массивом препядствий
    private Obstacle[] arrObstacle;

    //Конструктор в котором заполняем массив препятствиями
    public Course(){
        arrObstacle = new Obstacle[]{new Wall(1),
                                    new Water(5),
                                    new Road(10)};
    }

    //Метод который "просит" проиёти всю команду полосу препятствий
    public void doIt (Team team){
        for (Obstacle obstacle : arrObstacle) {
            for (int j = 0; j < team.arrTeam.length; j++) {
                obstacle.doIt(team.arrTeam[j]);
            }
        }
    }
}
