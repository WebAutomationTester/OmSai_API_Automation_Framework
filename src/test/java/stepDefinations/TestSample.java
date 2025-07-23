package stepDefinations;

import DataByExcel.dataDriven;
import io.cucumber.java.en.Given;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {

    @Given("read data from excel")
    public void read_data_from_excel() throws IOException {
       dataDriven d = new dataDriven();
       ArrayList dataArray = d.getData("Delete profile");
       System.out.println("Step-Defination file data: "+dataArray);
    }
}
