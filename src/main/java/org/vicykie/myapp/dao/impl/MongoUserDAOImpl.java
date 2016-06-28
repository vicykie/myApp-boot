package org.vicykie.myapp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.vicykie.myapp.dao.UserDAO;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.entities.authority.UserInfo;

import java.util.List;

/**
 * Created by d on 2016/5/5.
 */
@Repository("mongoUserDAO")
public class MongoUserDAOImpl implements UserDAO {
    @Autowired
    MongoTemplate template;

    @Override
    public int addUser(UserInfo user) {
//        Set<String> collections = template.getCollectionNames();
//        collections.forEach(System.out::println);
        template.insert(user);
        return 0;
    }

    @Override
    public int deleteUser(UserInfo user) {
        template.remove(user);
        return 0;
    }

    @Override
    public UserInfo getUserById(int id) {
        Criteria criteria = new Criteria("_id");
        criteria.is(id);
        Query query = new Query(criteria);

        return template.findOne(query, UserInfo.class);
    }

    @Override
    public UserInfo getUserByUsername(String username) {
        Criteria criteria = new Criteria("username");
        criteria.is(username);
        Query query = new Query(criteria);
//        Query query = new Query(criteria);
        return template.findOne(query, UserInfo.class);
    }

    @Override
    public List<UserInfo> getAllUsers() {

        return template.findAll(UserInfo.class);
    }

    @Override
    public int addRole(RoleInfo role) {
        template.insert(role);
        return 0;
    }
}
