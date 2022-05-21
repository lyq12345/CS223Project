package com.team8.cs223project.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team8.cs223project.entity.DataItem;
import com.team8.cs223project.mapper.DataItemMapper;
import com.team8.cs223project.service.DataItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataItemServiceImpl implements DataItemService {
    @Resource
    private DataItemMapper dataItemMapper;

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
}
