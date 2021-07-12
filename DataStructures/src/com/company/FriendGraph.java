package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

//One User is said to be another User's friend if they are connected by Link in undirected graph.
public class FriendGraph {

    LinkedHashMap<User,List<User>> friendCircle = new LinkedHashMap<>();
    HashMap<String,User> users = new HashMap<>();

    //all the registered students will be stored first
    public FriendGraph(String[]phones,String[] names)
    {
        for (int i = 0; i < phones.length; i++) {
            User user = new User(phones[i],names[i]);
            users.put(phones[i],user);
            List<User> friends = new ArrayList<>();
            friendCircle.put(user,friends);
        }
    }

    public void addFriend(String friend1, String friend2)
    {
        User f1 = users.get(friend1);
        User f2 = users.get(friend2);
        List<User> friendList1 = friendCircle.get(f1);
        List<User> friendList2 = friendCircle.get(f2);
        friendList1.add(f2);
        friendList2.add(f1);
    }
    public void viewLists()
    {
        friendCircle.forEach((user,friends)->{
            System.out.println("--------------------------------");
            System.out.println("User PhoneNo. : "+user.getPhoneNo());
            System.out.println("User Name : "+user.getName());
            System.out.println("Total Friends Have : "+friends.size());
            if(friends.size()>0)
            {
                System.out.println("Friend List: ");
                friends.forEach(friend -> {
                    System.out.println(friend.getName());
                });
            }
            System.out.println("--------------------------------");
        });
    }
}
