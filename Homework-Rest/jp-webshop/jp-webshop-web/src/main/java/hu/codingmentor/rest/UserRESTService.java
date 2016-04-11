package hu.codingmentor.rest;

import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.exception.BadRequestException;
import hu.codingmentor.interceptor.BeanValidation;
import hu.codingmentor.service.UserManagementService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/users")
@BeanValidation
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRESTService{
    private static final String NOT_LOGED_IN = "You are not logged in."; 
    
    @Inject
    private UserManagementService userManagementService;
    
    @GET
    public List<UserDTO> getUsers(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                UserDTO currentUser = (UserDTO) userObject;
                if (currentUser.isAdmin()) {
                    return new ArrayList<>(userManagementService.getUsers());
                }
                throw new BadRequestException("Only admins can see the list of users.");
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);
    }
    
    @GET
    @Path("/{userName}")
    public UserDTO getUser(@Context HttpServletRequest request, @PathParam("userName") String username) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                UserDTO currentUser = (UserDTO) userObject;
                if (currentUser.isAdmin()) {
                    return userManagementService.getUser(username);
                }
                throw new BadRequestException("Only admins can see the users' data.");
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);
    }
    
    @POST
    public UserDTO addUser(@Context HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                UserDTO currentUser = (UserDTO) userObject;
                if (currentUser.isAdmin()) {
                    if(userManagementService.getUser(user.getUsername()) == null){
                        userManagementService.addUser(user);
                        return user;
                    }
                    throw new BadRequestException("This username is already taken, please choose another.");
                }
                throw new BadRequestException("Only admins can add users.");
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);
    }
    
    @DELETE
    @Path("/{userName}")
    public UserDTO removeUser(@Context HttpServletRequest request, @PathParam("userName") String userName) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                UserDTO currentUser = (UserDTO) userObject;
                if (currentUser.isAdmin()) {
                    if(userManagementService.getUser(userName) != null){
                        return userManagementService.deleteUser(userName);
                    }
                    throw new BadRequestException("This user(name) doesn't exist.");
                }
                throw new BadRequestException("Only admins can remove users.");
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);
    }
    
    @PUT
    @Path("/{userName}")
    public UserDTO editUser(@Context HttpServletRequest request, @PathParam("userName") String userName, UserDTO user) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                UserDTO currentUser = (UserDTO) userObject;
                if (currentUser.isAdmin()) {
                    if(userManagementService.getUser(userName) != null){
                        return userManagementService.editUser(user);
                    }
                    throw new BadRequestException("The chosen user doesn't exist.");
                }
                throw new BadRequestException("Only admins can edit users.");
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);                        
    }
    
    @POST
    @Path("/login")
    public UserDTO login(@Context HttpServletRequest request, @QueryParam("username") String username, @QueryParam("password") String password) {
        HttpSession session = request.getSession(true);
        UserDTO user = userManagementService.getUser(username);
        if(user != null && user.getPassword().equals(password)){
            session.setMaxInactiveInterval(600);
            session.setAttribute("user", user);
            return user;
        }
        session.invalidate();
        throw new BadRequestException("Wrong username or password.");
    }
    
    @POST
    @Path("/logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.invalidate();
            return 1;
        }
        else{
           throw new BadRequestException(NOT_LOGED_IN); 
        }
    }    
}
