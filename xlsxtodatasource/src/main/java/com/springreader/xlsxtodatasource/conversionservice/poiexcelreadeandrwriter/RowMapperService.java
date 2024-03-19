package com.springreader.xlsxtodatasource.conversionservice.poiexcelreadeandrwriter;

import org.apache.poi.ss.usermodel.Row;

import java.text.ParseException;

/**
 * @param <T> Object the type
 * @author aek
 */
public interface RowMapperService<T> {

    /**
     * @param row the Row used for mapping.
     * @return mapped object of type T
     * @throws ParseException if an exception is raised.
     */
    T transformerRow(Row row) throws ParseException;
}
