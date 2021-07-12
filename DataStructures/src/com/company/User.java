package com.company;

public class User {
    private String phoneNo;
    private String Name;

    public User(String phoneNo, String name) {
        this.phoneNo = phoneNo;
        Name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getName() {
        return Name;
    }

    @Override
    public boolean equals(Object obj) {
        User ob = (User)obj;
        if(this.phoneNo.equals(ob.getPhoneNo()))
        {
            return true;
        }
        return false;
    }
}
