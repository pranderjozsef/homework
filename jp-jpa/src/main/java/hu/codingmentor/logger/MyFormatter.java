package hu.codingmentor.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    public MyFormatter() {
        super();
    }

    @Override
    public String format(final LogRecord record) {
        return record.getMessage();
    }
}
