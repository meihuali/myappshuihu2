package com.example.xiao.myappshuihu.entity;

import java.io.Serializable;

/**
 * Created by wx091 on 2016/2/27.
 */
public class Kettle extends Machine implements Serializable{
    public KettleState kettleState;

    public KettleState getKettleState() {
        return kettleState;
    }

    public void setKettleState(KettleState kettleState) {
        this.kettleState = kettleState;
    }
    public void initKettleState(){
        this.kettleState.init();
    }
    public Kettle(Machine machine){
        super(machine);
        this.setType(Constants.machineType.kettle);
        //initKettleState();
    }
}
