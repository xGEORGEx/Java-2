package ru.geekbrains.J2L3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        //Создаём массив с набором слов
	    String[] arrWords = {"Москва", "Санкт-Петербург", "Новосибирск", "Москва", "Москва", "Новосибирск",
                "Новосибирск", "Сочи", "Москва", "Сочи", "Сочи", "Санкт-Петербург", "Самра", "Санкт-Петербург", "Омск"};
	    ArrayList<String> wordsList = new ArrayList<>();
	    //Преобразовываем ранее созданный массив в ArrayList
        Collections.addAll(wordsList, arrWords);
	    for (int i = 0; i < wordsList.size(); i++){
	        //Устанавливаем значение счетчика 1
            int sum = 1;
            //Сравниваем текущий элемент ArrayList со всеми последующими элементами
            for (int j = i + 1; j < wordsList.size(); j++){
                //Если элемнты совпадают увеличиваем счётчик на 1, удаляем дублирующий элемент
                if (wordsList.get(i).equals(wordsList.get(j))){
                    sum++;
                    wordsList.remove(j);
                    //уменьшаем счётчик j на 1 , так как все элементы сдивинулись на один индекс влево
                    j--;
                }
            }
            wordsList.set(i, "Слово: " + wordsList.get(i) + " Количество повторов: " + sum);
        }

        for (String str : wordsList) {
            System.out.println(str);
        }

        PhoneBook book1 = new PhoneBook();
        book1.add("Иванов", "88-88-88");
        book1.add("Иванов", "88-88-89");
        book1.add("Иванов", "70-88-88");
        book1.add("Сидоров", "55-55-55");
        book1.add("Петров", "44-44-44");
        System.out.println(book1.get("Иванов"));

    }
}
