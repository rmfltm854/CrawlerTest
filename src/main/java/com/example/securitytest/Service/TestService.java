package com.example.securitytest.Service;


import com.example.securitytest.EntityClass.TestCollection;
import com.example.securitytest.Repository.TestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestService {

    @Autowired
    TestRepository repo;

    public String selectUser(String name){
        ObjectMapper mapper = new ObjectMapper();
        try{
            if(repo.findByName(name) == null){
                log.info("[Service] user name : {}not exist!@!",name);
                return String.format("user name : %s not exist!!",name);
            }else{
                return mapper.writeValueAsString(repo.findByName(name));
            }
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return "ERROR!@!";
        }
    }

    public void saveUser(String name, int age,String Email){
        TestCollection collection = new TestCollection();
        collection.setName(name);
        collection.setAge(age);
        collection.setEmail(Email);

        if(repo.findByName(name) != null){
            log.info("[Service][Update] name is already exist!!");
            collection.setId(repo.findByName(name).getId());
        } else{
            log.info("[Service][insert] New name received!!");
        }

        repo.save(collection);
    }
}
