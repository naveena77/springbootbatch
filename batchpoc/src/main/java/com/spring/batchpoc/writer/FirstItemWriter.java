package com.spring.batchpoc.writer;


import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstItemWriter implements ItemWriter<Long> {
    @Override
    public void write(Chunk<? extends Long> chunk) throws Exception {
        System.out.println("Inside Item writer");
        System.out.println("chunk Items()"+ chunk.getItems());
    }
}
