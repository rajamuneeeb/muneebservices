package com.muneebcode.job.csvtodatabase;

import org.springframework.batch.item.file.FlatFileParseException;

public class CsvProcessingException extends FlatFileParseException {

    public CsvProcessingException(String message, String input) {
        super(message, input);
    }

    public CsvProcessingException(String message, String input, int lineNumber) {
        super(message, input, lineNumber);
    }

    public CsvProcessingException(String message, Throwable cause, String input, int lineNumber) {
        super(message, cause, input, lineNumber);
    }
}
