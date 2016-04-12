package hu.codingmentor.check;

import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.exception.BadRequestException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheck {

    private LoginCheck() {
        //Empty
    }
    
    public static boolean isLogedIn(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        Object userObject = session.getAttribute("user");
        if ((userObject != null) && (userObject instanceof UserDTO))
            return true;
        else 
            throw new BadRequestException("You are not logged in.");
    }
    
    public static UserDTO getLogedInUser(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        Object userObject = session.getAttribute("user");
        if ((userObject != null) && (userObject instanceof UserDTO))
            return (UserDTO) userObject;
        else 
            throw new BadRequestException("You are not logged in.");
    }
    
    public static boolean isLogedInAsAdmin(HttpServletRequest request) {
        UserDTO currentUser = getLogedInUser(request);
        if (currentUser.isAdmin()) {
            return true;
        }
        throw new BadRequestException("Only admins can use this function.");                        
    }   
}
