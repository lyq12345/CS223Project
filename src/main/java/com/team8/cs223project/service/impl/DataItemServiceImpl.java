package com.team8.cs223project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team8.cs223project.entity.DataItem;
import com.team8.cs223project.mapper.DataItemMapper;
import com.team8.cs223project.service.DataItemService;
import com.team8.cs223project.utils.Operation;
import com.team8.cs223project.utils.Transaction;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DataItemServiceImpl implements DataItemService {
    @Resource
    private DataItemMapper dataItemMapper;

    public static final String COMMIT = "C";
    public static final String WRITE = "W";
    public static final String READ = "R";

//    private ArrayList<Transaction> commits = new ArrayList<>();
//    private ArrayList<Transaction> rollbacks = new ArrayList<>();
    private ArrayList<Transaction> TSs = new ArrayList<>();
    private HashMap<String, DataItem> data = new HashMap<>();

    @Override
    public Integer readItemValue(String key) {
        QueryWrapper<DataItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("key", key);
        DataItem item = dataItemMapper.selectOne(queryWrapper);
        return item.getValue();
    }

    @Override
    public void writeItemValue(String key, Integer value) {
        DataItem item = new DataItem(key, value);
        QueryWrapper<DataItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("key", key);
        if(null == dataItemMapper.selectOne(queryWrapper)){
            dataItemMapper.insert(item);
        }else{
            dataItemMapper.update(item, queryWrapper);
        }
    }

    @Override
//    @Transactional
    public synchronized void transactionWithSychronized() {
        QueryWrapper<DataItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("key", "x");
        DataItem item = dataItemMapper.selectOne(queryWrapper);
        item.setValue(item.getValue() + 1);
        dataItemMapper.update(item, queryWrapper);
    }

    @Override
    public Pair<String, HashMap<String, DataItem>> processTransaction(Transaction txn) {
        Operation op = txn.exec();
        String opType = op.getOpType();
        String key = op.getKey();

        if(opType.equals(READ)){
//            System.out.println("Read the value");
            return new Pair<>("READ", this.data);
        }
        else if(opType.equals(WRITE)){
//            System.out.println("Write the value");
            return new Pair<>("WRITE", this.data);
        }

        // If the transaction enters the validation phase
        if(opType.equals(COMMIT)){
            this.TSs.add(txn);
            boolean success = true;
            for(int i=0; i<this.TSs.size()-1; i++){
                success = this.compare(txn, this.TSs.get(i));
                if(!success) break;
            }

            if(success){
                //enter the write phase
                this.data = txn.getData();
                txn.setFinishTS(new Timestamp(System.currentTimeMillis()));

                System.out.println("data after " + txn.getName() + " is committed: ");

                for(DataItem item :this.data.values()){
                    item.log();
                }
                System.out.println();

                // TODO: apply write to db

                return new Pair<>("COMMITTED", this.data);
            }

            //if failed, rollback
            else{
                System.out.println(txn.getName() + "'s validation failed. Must rollback.");
                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

                Date myDate1 = null;
                try{
                    myDate1 = dateFormat1.parse("3000-01-01");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Timestamp FAR_FUTURE_TIME = new Timestamp(myDate1.getTime());
                txn.setStartTS(FAR_FUTURE_TIME);
                txn.setValidationTS(FAR_FUTURE_TIME);
                txn.setFinishTS(FAR_FUTURE_TIME);
                txn.setPtr(0);
                txn.setData(this.data);
                this.TSs.remove(txn);
                return new Pair<>("ABORT", null);
            }
        }

        return null;
    }

    private boolean compare(Transaction tr1, Transaction tr2) {
        if(tr2.getFinishTS().before(tr1.getStartTS())){
            return true;
        }
        if(tr1.getStartTS().before(tr2.getFinishTS()) && tr2.getFinishTS().before(tr1.getValidationTS())){
            // get intersection
            HashSet<String> intersection = new HashSet<>();
            intersection.addAll(tr1.getReadSet());
            intersection.retainAll(tr2.getWriteSet());
            if(intersection.isEmpty())
                return true;
        }
        return false;
    }

}
