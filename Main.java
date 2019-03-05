package ru.geekbrains.J2L1;

public class Main {

    public static void main(String[] args) {
    	//Создаём команду животных
	    Team animalTeam = new Team("Team of animals");
	    //Создаём полосу препятствий
	    Course course = new Course();
	    //"Просим" команду пройти полосу препятствий
	    course.doIt(animalTeam);
	    //Выводим информацию о членах команды, которые прошли полосу
		animalTeam.memberCourseCompleted();
		//Выводим информацию о всех членах команды
	    animalTeam.memberTeamInfo();
    }
}
