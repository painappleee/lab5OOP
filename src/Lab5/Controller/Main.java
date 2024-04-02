package Lab5.Controller;

import Lab5.View.Authorization;

public class Main {
    /*                 Доктора
    * Логин - Иванова Елена Александровна, Пароль - 012
    * Логин - Петрова Надежда Евгеньевна, Пароль - 012
    *                  Пациенты
    * Логин - Петров Андрей Алексеевич, Пароль - 123
    */

    public static void main(String[] args) {

        new Repository("Поликлинника №11");
        new Authorization();
    }
}