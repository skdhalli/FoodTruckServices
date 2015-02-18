/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.food.foodtruckservices;

/**
 *
 * @author sdhalli
 */
public class ServiceResult {
    
    public ServiceResult(status _status, String _message)
    {
        result_status = _status;
        message = _message;
    }
    
    public enum status{success,failed};
    private status result_status;
    private String message;
    public String ToJson()
    {
        String retval = "callback({\"Result\":[{\"status\":\""+result_status.name()+"\", \"message\":\""+message+"\"}]})";
        return retval;
    }
}
