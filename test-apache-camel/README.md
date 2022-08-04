Тестовое задание:


С помощью Apache Camel сделать сервис.



1.       Получить json по http. http-сервис должен быть поднят на порту 7000 c помощью компонента jetty .json – Массив из объектов типа [{id:123, name: “ааа”, sum: 100}, …];

// Начало маршрута будте начинаться: from(“jetty:http://….)

2.       Убедиться, что есть http заголовок Token и он равет ”SECRET”;

3.       Распарсить json в массив/список Pojo;

// .unmarshal().json(….)

4.       Записать в лог (Уровень INFO) количество элементов списка;

5.       Умножить сумму на число, полученное в виде 1 параметра командной строки;

// проще всего сделать в процессоре .process(exchange -> {})

6.       Преобразовать данный список в CSV (кодировка, разделители и пр. не важны);

//.marshal().bindy(BindyType.Csv,…

7.       Сохранить в файл с именем result.csv (В любой каталог)





Подсказки:

Обычное maven-приложение

Для запуска приложения можно использовать класс Main:


import org.apache.camel.main.Main;

public class com.timal.test_task.CamelService {
public static void main(String[] args) throws Exception {
Main m = new Main();
m.addRouteBuilder(new …);
m.run();
}
}