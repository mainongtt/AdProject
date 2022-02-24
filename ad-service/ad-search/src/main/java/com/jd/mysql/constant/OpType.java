package com.jd.mysql.constant;

import com.github.shyiko.mysql.binlog.event.EventType;


public enum OpType {

    ADD,
    UPDATE,
    DELETE,
    OTHER;

    /**
     * 将Binlog EventType转换为我们的操作类型OpType
     * @param eventType
     * @return
     */
    public static OpType to(EventType eventType) {

        // mysql版本
        switch (eventType) {
            case EXT_WRITE_ROWS:
                return ADD;
            case EXT_UPDATE_ROWS:
                return UPDATE;
            case EXT_DELETE_ROWS:
                return DELETE;

            default:
                return OTHER;
        }
    }
}
