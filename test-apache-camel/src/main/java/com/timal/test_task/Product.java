package com.timal.test_task;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ", ")
public class Product {
  //  @JsonView
    @DataField(pos = 1)
    private Long id;

  //  @JsonView
    @DataField(pos = 2)
    private String name;

 //   @JsonView
    @DataField(pos = 3)
    private int sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
