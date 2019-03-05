package ru.geekbrains.J2L1;

//Создаём класс Team
public class Team {
    //Создаем поля с названием команды и массивом животных
    String name;
    Animal[] arrTeam;

    //Создаём конструктор в котором сразу инициализируем массив
    public Team (String name){
        this.name = name;
        arrTeam = new Animal[]{new Dog(5, 12, 2),
                                new Cat(8, 4),
                                new Dog(6,10,3),
                                new Duck(5, 20)};
    }

    //Метод выводит инвормацию о членах команды, которые прошли полосу препятствий
    public void memberCourseCompleted(){
        for (Animal animal : arrTeam) {
            if (animal.isOnDistance()){
                System.out.println("Name: " + animal.getName() + " Course is completed " + animal.isOnDistance());
            }
        }
    }

    //Метод выводит инвормацию о всех членах команды
    public void memberTeamInfo (){
        for (Animal animal : arrTeam) {
            System.out.println("Name: " + animal.getName() + " Course is completed " + animal.isOnDistance());
        }
    }
}
