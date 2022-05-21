package com.team8.cs223project.service;

import com.team8.cs223project.entity.DataItem;
import com.team8.cs223project.utils.Transaction;
import javafx.util.Pair;

import java.text.ParseException;
import java.util.HashMap;

public interface DataItemService {
    Integer readItemValue(String key);

    void writeItemValue(String key, Integer value);

    void transactionWithSychronized();

    Pair<String, HashMap<String, DataItem>> processTransaction(Transaction txn);

}
