package com.timal.test_task;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.main.Main;
import org.apache.camel.model.dataformat.BindyType;

import java.util.List;

public class CamelService {

    public static void main(String[] args) {

        JacksonDataFormat format = new ListJacksonDataFormat(Product.class);
        Main m = new Main();

        m.configure().addRoutesBuilder(new RouteBuilder() {
            public void configure() {
                from("jetty:http://localhost:7000/my_test")
                        .validate(exchange -> {
                            return exchange.getIn().getHeader("Token").equals("SECRET");
                        })
                        .unmarshal(format)
                        .log(LoggingLevel.INFO, "Количество элементов списка: ${in.body.size}")
                        .process(exchange -> {
                            List<Product> products = exchange.getIn().getBody(List.class);
                            products.forEach(p -> p.setSum(p.getSum() * Integer.parseInt(args[0])));
                            exchange.getIn().setBody(products);
                        })
                        .marshal().bindy(BindyType.Csv, Product.class)
                        .to("file://c:/ftproot?fileName=result.csv");
            }
        });

        try {
            m.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
