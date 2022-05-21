package com.team8.cs223project.utils;

import com.team8.cs223project.entity.DataItem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Transaction {
    public static final String COMMIT = "C";
    public static final String WRITE = "W";
    public static final String READ = "R";

    private ArrayList<Operation> ops = new ArrayList<>();
    private int ptr = 0;
    private String name = "";
    private HashSet<String> readSet = new HashSet<>();
    private HashSet<String> writeSet = new HashSet<>();

    private Timestamp startTS;
    private Timestamp validationTS;
    private Timestamp finishTS;
    HashMap<String, DataItem> data;

    public Transaction(String schedule, HashMap<String, DataItem> data){
        this.data = data;
        if(schedule != null){
            this.parse(schedule);
        }
        this.getWriteAndReadSet();
    }

    public void addOperation(Operation op){
        this.ops.add(op);
    }

    public void getWriteAndReadSet() {
        for(Operation op: this.ops){
            String opType = op.getOpType();
            String key = op.getKey();
            if(opType.equals(READ)){
                if(!this.readSet.contains(key)){
                    this.readSet.add(key);
                }
            }
            if(opType.equals(WRITE)){
                if(!this.writeSet.contains(key)){
                    this.writeSet.add(key);
                }
            }
        }
    }

    public void parse(String schedule) {
        if(schedule.charAt(schedule.length()-1) == '\n')
            schedule = schedule.substring(0, schedule.length()-1);
        if(schedule.charAt(schedule.length()-1) == ';')
            schedule = schedule.substring(0, schedule.length()-1);


        String[] sch = schedule.split(":");
        this.name = sch[0];
        String[] ops = sch[1].split(";");
        for(String op: ops){
            try{
                String[] split = op.split("_");
                this.addOperation(new Operation(split[0], split[1]));
            } catch (IndexOutOfBoundsException e){
                this.addOperation(new Operation(op, ""));
            }

        }

    }

    public Operation next(){
        try {
            Operation operation = this.ops.get(this.ptr);
//            this.ptr++;
            return operation;
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }

    public Operation exec(){
        try{
            Operation op = this.ops.get(this.ptr);
            if(op.getOpType().equals(WRITE)){
                this.data.get(op.getKey()).setValue(op.getValue());
            }
            if(this.ptr == 0){
                Long datetime = System.currentTimeMillis();
                this.startTS = new Timestamp(datetime);
            }
            if(op.getOpType().equals(COMMIT)){
                Long datetime = System.currentTimeMillis();
                this.validationTS = new Timestamp(datetime);
            }
            this.ptr += 1;
            return op;
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public void log(){
        System.out.println(this.name + ": ");
        int i = this.ptr;
        int n = this.ops.size();
        if(i == n)
            System.out.println("No more operation.");
        while(i < n){
            this.ops.get(i).log();
            i++;
        }
        System.out.println();
    }

    public ArrayList<Operation> getOps() {
        return ops;
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getReadSet() {
        return readSet;
    }

    public HashSet<String> getWriteSet() {
        return writeSet;
    }

    public Timestamp getStartTS() {
        return startTS;
    }

    public Timestamp getValidationTS() {
        return validationTS;
    }

    public Timestamp getFinishTS() {
        return finishTS;
    }

    public int getPtr(){
        return ptr;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (other.getName() == null) {
            return false;
        }
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
