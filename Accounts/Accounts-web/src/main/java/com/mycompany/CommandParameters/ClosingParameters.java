/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.CommandParameters;

import com.mycompany.ActionType;
import java.util.ArrayList;

/**
 *
 * @author Vlad
 */
public class ClosingParameters extends Parameters{
    protected ArrayList<Integer> ids;
    
    public ClosingParameters(int id){
        super(ActionType.CLOSE);
        ids=new ArrayList<Integer>();
        ids.add(id);
    }
    
    public int getId(){
        return ids.get(0);
    }
    
}
