/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vlad
 */
@Entity
@Table(name = "account")
@XmlRootElement
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    protected Integer id;
    @Column(name = "balance")
    protected Integer balance;
    @Version
    @Column(name = "version")
    private Integer version;

    public Account() {
    }
    
    public Account(Integer startBalance) {
        this.balance = startBalance;
        this.version=0;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    
    public void takeMoney(int sum){
        this.balance-=sum;
    }
    
    public void putMoney(int sum){
        this.balance+=sum;
    }    
    
}
