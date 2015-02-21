/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.food.foodtruckservices;

import com.foodtruckdata.interfaces.IUsersInput;
import com.foodtruckdata.mongodb.UsersInput;
import java.net.UnknownHostException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author sdhalli
 */
@Path("rest")
public class registration {

    @Context
    private UriInfo context;

    IUsersInput usersInput;
    /**
     * Creates a new instance of registration
     */
    public registration() throws UnknownHostException {
        usersInput = new UsersInput("ds041841.mongolab.com", 41841, "streetfood", "developer", "code@9932");
    }

    @GET
    @Produces("application/json")
    @Path("add/truck")
    public String addTruck(@QueryParam("title") String title, @QueryParam("logo_img") String logo_img, @QueryParam("menu_img")  String menu_img, @QueryParam("phone")  String phone, 
                @QueryParam("email") String email, @QueryParam("username") String username, @QueryParam("password") String password)
    {
        ServiceResult retval = null;
        boolean truck_exists = usersInput.TruckExists(title, email);
        
        if(truck_exists)
        {
            retval = new ServiceResult(ServiceResult.status.failed, "Truck with similar email/title already exists");
        }
        else
        {
            String truck_id = usersInput.AddTruck(title, logo_img, menu_img, phone, email, username, password);
            retval = new ServiceResult(ServiceResult.status.success, truck_id);
        }
        return retval.ToJson();
    }
    
    /**
     * Retrieves representation of an instance of com.food.foodtruckservices.registration
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        return "Welcome to Food Truck Data Services";
    }

    /**
     * PUT method for updating or creating an instance of registration
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
