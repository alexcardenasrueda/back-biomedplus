package com.softdevelop.biomedplus.enums;

public enum Status {
    CREATED,
    IN_PROCESS,
    FINISHED;

    public static Status GetStatusByName(String status) {
            if (status.equals(CREATED.name())) {
            return CREATED;
            }

            if (IN_PROCESS.name().equals(status)) {
                return IN_PROCESS;
            }

            if (FINISHED.name().equals(status)) {
                return FINISHED;
            }
        return null;
    }
}
