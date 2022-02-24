package com.jd.mysql.listener;


import com.jd.mysql.dto.BinlogRowData;

/**
 *  Ilistener for 为了后续扩展不同的实现
 */
public interface Ilistener {

    void register();

    void onEvent(BinlogRowData eventData);
}
