package org.scoula;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CSVTest1 {
    public static void main(String[] args) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("travel.csv"));
        String[] line;
        while((line = reader.readNext())!= null){
            System.out.println(String.join(",", line));
        }
    }

}
