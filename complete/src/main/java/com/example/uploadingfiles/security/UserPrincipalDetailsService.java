package com.example.uploadingfiles.security;

import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.repositories.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return new UserPrincipal(readPasswordFromFileOrDatabase(s));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //User user = this.userRepository.findByUsername(s);
        //UserPrincipal userPrincipal = new UserPrincipal(user);
    }

    private User readPasswordFromFileOrDatabase(String username) throws IOException {
        ObjectMapper mapper = new XmlMapper();
        InputStream inputStream = new FileInputStream("complete\\src\\main\\java\\com\\example\\uploadingfiles\\security\\security.xml");
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
        List<User> persons = mapper.readValue(inputStream, typeReference);
        for(User p :persons) {
            if (p.getUsername().equals(username)) return new User(p.getUsername(), p.getPassword(), p.getEmail(), p.getRoles(), p.getPermissions());
        }
        // Edit this code and read password and roles from data base or files
        return null;
    }
}