package org.tyaa.customannotations.bean;

import org.tyaa.customannotations.annotations.ControlledObject;
import org.tyaa.customannotations.annotations.StartObject;
import org.tyaa.customannotations.annotations.StopObject;

@ControlledObject(name="smth")
public class Session {

    @StartObject
    public void createSession(){
        System.out.println("createSession");
    }
    @StopObject
    public void stopSession(){
        System.out.println("stopSession");
    }

}
