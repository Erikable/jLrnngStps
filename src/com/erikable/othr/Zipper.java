package com.erikable.othr;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// test path /Users/erikable/Desktop/erikable/JAVA/ATON/testData/
//  \\al-flstore\#al-flstore\user\FinOpDEP\BirzhOtchet\
public class Zipper {
    private static final String error = "FAIL :(";

    public static void main(String[] args){
        Date start = new Date();
        // Путь к папке где папки с Годами
        String dir = "/Users/erikable/Desktop/erikable/JAVA/ATON/testData/";

        // узнаем дату сейчас для обращения в нужную папку в будущем
        LocalDate ld = LocalDate.now();

        // Если сегодня 1й месяц то идем в прошлый год папки в 12 мес.
        // Если не 1й то на минус 1мес назад от сегодняшнего
        if (ld.getMonthValue() == 1) {
            int yearFolder = ld.getYear() - 1;
            int monthFolder = 12;
            try {
                zipim(dir, yearFolder, monthFolder);
            } catch (IOException e) {
                System.out.println(error);
                e.printStackTrace();
            }
        } else {
            int yearFolder = ld.getYear();
            int monthFolder = ld.getMonthValue() - 1;
            try {
                zipim(dir, yearFolder, monthFolder);
            } catch (IOException e) {
                System.out.println(error);
                e.printStackTrace();
            }
        }
        Date finish = new Date();
        System.out.println("Архивация файлов закончена - это зяняло: " + (finish.getTime() - start.getTime()) / 1000.0  + " секунд на выполнение.");
    }

    // метод принимает: (Путь к папке с годами, Год, Месяц)
    public static void zipim (String d, int y, int m) throws IOException {
        // файл полного пути Путь/Год/мес
        File destonationDir = new File(d + "/" + y + "/" + m);

        // пробегаемся по каждому файлу в месячной папке
        for (File f : destonationDir.listFiles()) {
            //проваливаемся сюда если вес больше 1Мб
            if ((f.length() / 1000000.0) > 1) {
                // получение расширения после точки в конце файла (отрезаем все до последней точки)
                String rasshirenie = "";
                if (f.toString().contains(".")) {
                    rasshirenie = f.toString().substring(f.toString().lastIndexOf("."));
                }
                // выполнится если расширение у файла НЕ зип и Не рар
                if (!rasshirenie.equals(".zip") && !rasshirenie.equals(".rar")) {
                    // выходной файл (тот же путь и название файла + .зип или .рар)
                    FileOutputStream fos = new FileOutputStream(f.getAbsolutePath() + ".zip");
                    // создание архива (в нем лежит поток fos)
                    ZipOutputStream zos = new ZipOutputStream(fos);
                    // поток чтения файла
                    FileInputStream fis = new FileInputStream(f);
                    // создание для файла объекта зипЭнтри
                    ZipEntry zipEntry = new ZipEntry(f.getName());
                    // добавление в архив объекта зипЭнтри
                    zos.putNextEntry(zipEntry);
                    // считываем содержимое файла в массив byte
                    byte[] bytes = new byte[1024];
                    int length;
                    // добавляем содержимое к архиву кусками
                    while((length = fis.read(bytes)) >= 0) {
                        zos.write(bytes, 0, length);
                    }

                    zos.closeEntry();
                    // удаляем оригинальный файл после архивирования
                    f.delete();
                    // закрытие потоков
                    fis.close();
                    zos.close();
                    fis.close();
                }
            }
        }
    }
}