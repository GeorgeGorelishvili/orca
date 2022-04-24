package com.george.orca.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parse {

    public static Long parseLong(String longValue) {
        Long uploadMaxSize = null;
        try {
            uploadMaxSize = Long.parseLong(longValue);
        } catch (NumberFormatException nfe) {
            log.error("can not read property \"file.iupload.max.size\"", nfe);
        }
        return uploadMaxSize;
    }
}
