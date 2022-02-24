package com.jd.sender;


import com.jd.mysql.dto.MySqlRowData;

public interface ISender {

    void sender(MySqlRowData rowData);
}
