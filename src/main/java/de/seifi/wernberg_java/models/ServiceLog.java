package de.seifi.wernberg_java.models;

import java.util.Date;

public class ServiceLog {
    private final String message;

    private final String level;

    private final Date logDateTime;

    private final String logDateTimeStr;

    public ServiceLog(String message,
                      String level,
                      Date logDateTime,
                      String logDateTimeStr) {
        this.message = message;
        this.level = level;
        this.logDateTime = logDateTime;
        this.logDateTimeStr = logDateTimeStr;
    }

    public String getMessage() {
        return message;
    }

    public String getLevel() {
        return level;
    }

    public Date getLogDateTime() {
        return logDateTime;
    }

    public String getLogDateTimeStr() {
        return logDateTimeStr;
    }
}
