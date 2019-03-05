package ru.geekbrains.J2L2;

public class Main {

    public static void main(String[] args) {
	    String[][] arrString = {{"6", "5", "5", "5"},
                                {"5", "5", "5", "5"},
                                {"5", "5", "5", "5"},
                                {"5", "5", "5", "5"}};
	    int A = arrSum(arrString);
	    System.out.println(A);

	    //Создаём экземпляр класса в try with resources
	    try (MyClass myClass = new MyClass(10, 0)) {
	        if (myClass.getY() == 0){
	            //Вызываем исключение в блоке try
	            throw new Exception("Деление на ноль!");
            }
        } catch (Exception e) {
	        //Вызываем исключение в блоке close()
            System.out.println("Деление на ноль!");
        }
    }

    public static int arrSum (String[][] arrStr){
        int sum = 0;
        int i = 0;
        int j = 0;
        try {
            if (arrStr.length != 4){
                //Если размерность массива не 4х4, то вызываем исключение MyArraySizeException
                throw new MyArraySizeException("Неверная размерность массива");
            }
            for (i = 0; i < 4; i++) {
                if (arrStr[i].length != 4) {
                    //Если размерность массива не 4х4, то вызываем исключение MyArraySizeException
                    throw new MyArraySizeException("Неверная размерность массива");
                }
                for (j = 0; j < 4; j++) {
                    sum += Integer.parseInt(arrStr[i][j]);
                }
            }
        } catch (MyArraySizeException ex){
            ex.printStackTrace();
        } catch (NumberFormatException ex){
            //Если возникло исключение NumberFormatException, то вызываекм "наше" исключение MyArrayDataException
            //с указанием ячейки где введены неверные данные
            throw new MyArrayDataException ("Неверные данные в ячейке " + i + " " + j);
        }
        return sum;
    }
}
