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
public class TransferParameters extends PuttingTakingParameters{

    public TransferParameters(int id1, int id2, int sum) {
        super(ActionType.TRANSFER, id1, sum);
        ids.add(id2);
    }
    
    public int getId2(){
        return ids.get(1);
    }
}
