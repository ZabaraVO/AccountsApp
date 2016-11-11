/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import com.mycompany.CommandParameters.Parameters;
import com.mycompany.CommandParameters.TransferParameters;
import com.mycompany.CommandParameters.PuttingTakingParameters;
import com.mycompany.CommandParameters.ClosingParameters;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Vlad
 */
@Path("")
@RequestScoped
public class CommandsHandler {

    @Context
    private UriInfo context;
    
    @EJB 
    private AccountFacadeLocal AccFacade;

    /**
     * Creates a new instance of CommandsHandler
     */
    public CommandsHandler() {
    }
    
    @GET
    @Path("getAccountInfo/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Account getXml(@PathParam("id") int id) {
        return AccFacade.find(id);
    }
    
    @POST
    @Path("openAccount/{startBalance}")
    public String openAccount(@PathParam("startBalance") int startBalance){
        AccFacade.openAccount(startBalance);
        return "The operation completed ";
    }
    
    @POST
    @Path("closeAccount/{id}")
    public String closeAccount(@PathParam("id") int id) throws Exception {
        ClosingParameters params=new ClosingParameters(id);
        return handleCommand(params);
    }
    
    @POST
    @Path("putMoney/{id}/{sum}")
    public String putMoney(@PathParam("id") final int id, @PathParam("sum") final int sum)throws Exception{
        PuttingTakingParameters params=new PuttingTakingParameters(ActionType.PUT,id,sum);
        return handleCommand(params);
    }
    
    @POST
    @Path("takeMoney/{id}/{sum}")
    public String takeMoney(@PathParam("id") final int id, @PathParam("sum") final int sum)throws Exception{
        PuttingTakingParameters params=new PuttingTakingParameters(ActionType.TAKE,id,sum);
        return handleCommand(params);
    }
    
    @POST
    @Path("transferMoney/{idFrom}/{idTo}/{sum}")
    public String transferMoney(@PathParam("idFrom") int idFrom,@PathParam("idTo") int idTo, @PathParam("sum") int sum) throws Exception {
        TransferParameters params=new TransferParameters(idFrom,idTo,sum);
        return handleCommand(params);
    }
    
    private String handleCommand(Parameters params) throws Exception {
        String message="The operation completed successfully";
        try{
            handleCommandInLoop(params);
        }
        catch(AccountNotFoundException e){
            message=e.getMessage();
        }
        return message;
    }
    
    private void handleCommandInLoop(Parameters params) throws Exception{
        boolean keepLooping=true;
        while(keepLooping){
            keepLooping=false;
            try{
                if(params.getAction()==ActionType.PUT || params.getAction()==ActionType.TAKE){
                    PuttingTakingParameters puttingTakingParameters=(PuttingTakingParameters)params;
                    AccFacade.operateMoney(puttingTakingParameters.getId(),
                            puttingTakingParameters.getSum(),puttingTakingParameters.getAction());
                }
                if(params.getAction()==ActionType.TRANSFER){
                    TransferParameters transferParameters=(TransferParameters)params;
                    AccFacade.transferMoney(transferParameters.getId(), 
                            transferParameters.getId2(), transferParameters.getSum());
                }
                if(params.getAction()==ActionType.CLOSE){
                    ClosingParameters closingParameters=(ClosingParameters)params;
                    AccFacade.closeAccount(closingParameters.getId());
                }
            }catch(Exception e){
                keepLooping=true;
                if(e instanceof AccountNotFoundException){
                    throw e;
                }
            }
        }
    }
}
