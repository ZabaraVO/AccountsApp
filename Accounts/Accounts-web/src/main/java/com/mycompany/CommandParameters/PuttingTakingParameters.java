/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.CommandParameters;

import com.mycompany.ActionType;

/**
 *
 * @author Vlad
 */
public class PuttingTakingParameters extends ClosingParameters{
    protected int sum;
    
    public PuttingTakingParameters(ActionType action, int id, int sum) {
        super(id);
        this.sum=sum;
        this.action=action;
    }
    
    public int getSum(){
        return sum;
    }
}
