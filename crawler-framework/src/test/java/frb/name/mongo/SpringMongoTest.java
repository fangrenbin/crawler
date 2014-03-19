///*
//* Create Author  : renbin.fang
//* Create Date    : Jan 17, 2014
//* File Name      : SpringMongoTest.java
//*/
//
//package frb.name.mongo;
//
//import java.util.List;
//
//import junit.framework.Assert;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import frb.name.mongo.vo.User;
//
///**
// * SpringMongoTest
//* <p>
//*
//* @author : renbin.fang
//* @date : Jan 17, 2014
//*/
//public class SpringMongoTest extends AbstractTestng {
//    @Qualifier("mongoTemplate")
//    @Autowired
//    MongoTemplate mongoTemplate;
//
////    @Test(dataProvider = "saveUser")
//    public void saveUserTest(User user) {
//        mongoTemplate.insert(user);
//        user.setId("");
//        mongoTemplate.insert(user);
//        user.setId("");
//        mongoTemplate.insert(user);
//        user.setId("");
//        // insert data
//        mongoTemplate.insert(user);
//
//        // find all
//        List<User> userList = mongoTemplate.findAll(User.class);
//        for (User user2 : userList) {
//            user.setId(user2.getId());
//            Assert.assertEquals(user.toString(), user2.toString());
//        }
//
//        user.setId("");
//        mongoTemplate.insert(user);
//
//        // find one
//        Query searchUserQuery = new Query(Criteria.where("username").is("liLei"));
//        User savedUser = mongoTemplate.findOne(searchUserQuery, User.class);
//
//        user.setId(savedUser.getId());
//        Assert.assertEquals(user.toString(), savedUser.toString());
//    }
//
////    @Test(dataProvider = "updateUser")
//    public void updateUserTest(User saveUser, User updateUser) {
//        mongoTemplate.insert(saveUser);
//
//        // 1st. save user
//        Query query = new Query(Criteria.where("username").is("小狗"));
//        User savedUser = mongoTemplate.findOne(query, User.class);
//
//        saveUser.setId(savedUser.getId());
//        Assert.assertEquals(saveUser.toString(), savedUser.toString());
//
//        // 2nd. update user
//        mongoTemplate.updateMulti(query, Update.update("username", updateUser.getUsername()), User.class);
//
//        Query updateQuery = new Query(Criteria.where("username").is(updateUser.getUsername()));
//        User updatedUser = mongoTemplate.findOne(updateQuery, User.class);
//
//        savedUser.setUsername(updateUser.getUsername());
//        Assert.assertEquals(savedUser.toString(), updatedUser.toString());
//    }
//
////    @Test(dataProvider = "saveUser")
//    public void removeUserTest(User saveUser) {
//        mongoTemplate.insert(saveUser);
//
//        // find one
//        Query searchUserQuery = new Query(Criteria.where("username").is("liLei"));
//        User savedUser = mongoTemplate.findOne(searchUserQuery, User.class);
//
//        saveUser.setId(savedUser.getId());
//        Assert.assertEquals(saveUser.toString(), savedUser.toString());
//
//        // remove one
//        mongoTemplate.remove(searchUserQuery, User.class);
//        List<User> userList = mongoTemplate.findAll(User.class);
//        Assert.assertTrue(userList.size() == 0);
//    }
//
//    @DataProvider(name = "saveUser")
//    public Object[][] saveUserDataProvider() {
//        return new Object[][] { { new User("liLei", "lilei123", "li", "lei", "no", "lilie@gmail.com", "No.30, jiangsu Rd., ShangHai.", "+86-13166374819", "male") } };
//    }
//
//    @DataProvider(name = "updateUser")
//    public Object[][] updateUserDataProvider() {
//        return new Object[][] { { new User("小狗", "xiaogou", "qt", "dog", "", "xiaogou@gmail.com", "zoo", "911", "male"),
//                                 new User("小猪", "xiaozhu", "qt", "pig", "", "xiaozhu@gmail.com", "zoo", "123", "female") } };
//    }
//
//    protected void setup() {
//
//    }
//
//    @AfterMethod
//    @BeforeClass
//    protected void destroy() {
////        mongoTemplate.dropCollection(User.class);
//    }
//}