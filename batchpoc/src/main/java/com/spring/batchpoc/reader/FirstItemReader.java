package com.spring.batchpoc.reader;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FirstItemReader implements ItemReader<Integer> {

    List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    int i=0;

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Inside Item reader");
        Integer item;
        if(i<list.size()){
            item = list.get(i);
            System.out.println(i);
            i++;
            return item;
        }
        //reuse this item reader to use to set i=0
        i=0;
        //there is no more record to return so return as null
        return null;
    }
}
