package com.ansa.configuration.xauth;

import com.ansa.usermanagement.User;
import com.ansa.usermanagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value = "/auth")
public class UserXAuthTokenController {
    private final TokenUtils tokenUtils = new TokenUtils();
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private final UserService userService;

    @Autowired
    public UserXAuthTokenController(AuthenticationManager am,
                                    @Qualifier("customUserDetailsService")UserDetailsService userDetailsService,
                                    UserService userService) {
        this.authenticationManager = am;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @RequestMapping(value = "/registerLong", method = { RequestMethod.POST})
    public ResponseEntity<Object> register(@RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = { RequestMethod.POST})
    public ResponseEntity<Object> register(@RequestParam String username, @RequestParam String password) {

        try {
            if (this.userDetailsService.loadUserByUsername(username)!=null) {
                Map<String,String> responseBody = new HashMap<>();
                responseBody.put("message", "duplicate username");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }

        } catch (UsernameNotFoundException ex){

        }

        userService.registerUser(username, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = { RequestMethod.POST })
    public ResponseEntity<Object> authorize(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userService.getUserByUserName(username);
            Map<String,String> responseBody = new HashMap<>();
            responseBody.put("token", tokenUtils.createToken(user));
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (AuthenticationException ex){
            Map<String,String> responseBody = new HashMap<>();
            responseBody.put("errorMessage", ex.getMessage());
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/preCheck", method = { RequestMethod.POST })
    public ResponseEntity<Object> preCheck(@RequestParam String token){
        User user = userService.getUserByToken(token);
        if (user != null){
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }

    public static class UserInfo{
        private final String username;
        private final String token;

        public UserInfo(String username, String token){
            this.username = username;
            this.token = token;
        }

        public String getUsername(){
            return username;
        }

        public String getToken(){
            return token;
        }
    }
    public static class UserTransfer {

        private final String name;
        private final Map<String, Boolean> roles;
        private final String token;

        public UserTransfer(String userName, Map<String, Boolean> roles, String token) {

            Map<String, Boolean> mapOfRoles = new ConcurrentHashMap<String, Boolean>();
            for (String k : roles.keySet())
                mapOfRoles.put(k, roles.get(k));

            this.roles = mapOfRoles;
            this.token = token;
            this.name = userName;
        }

        public String getName() {
            return this.name;
        }

        public Map<String, Boolean> getRoles() {
            return this.roles;
        }

        public String getToken() {
            return this.token;
        }
    }
}
