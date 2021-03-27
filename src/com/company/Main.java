package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader; //импорт, можно выразиться, библиотек
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String line;// Строки из файлов
        String buf[]; // Буфер для нужной нам строки которую мы разделили через пробел
        Double outputConvert;// Ответ
        Double firstValue; //Сюда записывается первое значение валюты в рублях
        Double secondValue; //Сюда второе

        outputConvert = firstValue = secondValue = null; //здесь инициализация нужна для успешного сравнения, проверка на null

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            while((line = reader.readLine()) != null){ //Считываем строки до последнего символа

                if (line.contains(args[2])){ //Ищем первую валюту
                    buf = line.split(" ");
                    firstValue = Double.parseDouble(buf[1]);
                }
                if (line.contains(args[3])){ //Ищем вторую валюту
                    buf = line.split(" ");
                    secondValue = Double.parseDouble(buf[1]);
                }
                if (firstValue != null && secondValue != null){ // Eсли оба аргумента не пусты значит конвертирует и выходит
                    outputConvert = Double.parseDouble(args[1]) * firstValue / secondValue;
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Incorrect format of input file"); // выводим ошибку в случае провала
            return;
        } catch (IOException e) {
            System.err.println("Incorrect data in file");
            return;
        }

        if (outputConvert != null){
            System.out.println(args[1] + " " + args[2] + " was successfully converted into " +
                    String.format("%.2f", outputConvert) + " " + args[3]);
        }
        else if (outputConvert == null){
            System.out.println(args[1] + " " + args[2] + " cannot be converted into " + args[3]
                    + ", because conversion rate for " + args[3] + " is undefined into \"rates.txt\"");
        }

    }
}