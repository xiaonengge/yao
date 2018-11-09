package com.yao.client;

import java.net.InetSocketAddress;
import java.util.List;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.client.*;

public class CanalClient{

    public static void main(String args[]) {
        //destination
        //设置canal server的ip和port，还有一个destination参数。
        //destination对应的是canal server的instance，默认是example，
        // 它代表一个完整的监听实例，这里如果有多个连接example实例的client，这里的实例就是指conf文件夹下的example
        // 则只有一个client能获取mysql的数据变更通知。所以要想不同的应用都获取变更通知，
        // 则需要连接不同的instance，再此我们可以在canal server复制一个conf/example文件夹，并重命名为example1即可。
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
                9099), "example", "", "");
        int batchSize = 1000;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            while (true) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    printEntry(message.getEntries());
                }
                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }
        } finally {
            connector.disconnect();
        }
    }

    private static void printEntry( List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }
            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
//                if (eventType == EventType.DELETE) {
//                    redisDelete(rowData.getBeforeColumnsList());
//                } else if (eventType == EventType.INSERT) {
//                    redisInsert(rowData.getAfterColumnsList());
//                } else {
//                    System.out.println("-------> before");
//                    printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("-------> after");
//                    redisUpdate(rowData.getAfterColumnsList());
//                }
            }
        }
    }

    private static void printColumn( List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }

//    private static void redisInsert( List<Column> columns){
//        JSONObject json=new JSONObject();
//        for (Column column : columns) {
//            json.put(column.getName(), column.getValue());
//        }
//        if(columns.size()>0){
//            RedisUtil.stringSet("user:"+ columns.get(0).getValue(),json.toJSONString());
//        }
//    }
//
//    private static  void redisUpdate( List<Column> columns){
//        JSONObject json=new JSONObject();
//        for (Column column : columns) {
//            json.put(column.getName(), column.getValue());
//        }
//        if(columns.size()>0){
//            RedisUtil.stringSet("user:"+ columns.get(0).getValue(),json.toJSONString());
//        }
//    }
//
//    private static  void redisDelete( List<Column> columns){
//        JSONObject json=new JSONObject();
//        for (Column column : columns) {
//            json.put(column.getName(), column.getValue());
//        }
//        if(columns.size()>0){
//            RedisUtil.delKey("user:"+ columns.get(0).getValue());
//        }
//    }
} 
