package Lab6;

import Lab6.Model.*;
import Lab6.Repository.Repository;
import Lab6.View.Authorization;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

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