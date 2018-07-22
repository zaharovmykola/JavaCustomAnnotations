/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.customannotations.container;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.tyaa.customannotations.annotations.Inject;
import org.tyaa.customannotations.bean.Cookies;
import org.tyaa.customannotations.controller.SessionController;

/**
 *
 * @author student
 */
public class Injecter {
    
    public void addControlledInstance(Object _o) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException{
    
        if (_o instanceof SessionController) {
            
            SessionController sc = (SessionController)_o;
            Class targetClass = sc.getClass();
            Field[] fields = targetClass.getFields();
            for(Field field : fields){
                if(field.isAnnotationPresent(Inject.class)) {
                    
                    Class injectingInstatceClass = field.getType();
                    Type injectingInstatceType = field.getAnnotatedType().getType();
                    //System.out.println(injectingInstatceType);
                    Constructor constructor = injectingInstatceClass.getDeclaredConstructors()[0];
                    constructor.setAccessible(true);  
                    field.setAccessible(true);
                    field.set(sc, (injectingInstatceClass.cast(constructor.newInstance())));
                    //field.set(sc, (injectingClass.getConstructors()[0].getAnnotatedReturnType())constructor.newInstance());
                    //field.set(sc, (Object)constructor.newInstance());
                    //break;
                }
            }
        }
    }
}
