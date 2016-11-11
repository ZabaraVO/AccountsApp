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
public class Parameters {
    protected ActionType action;

    public Parameters(ActionType action) {
        this.action = action;
    }
    
    public ActionType getAction(){
        return action;
    }
}
