package com.company;

import java.util.*;

class PersonForTestingBooth

{
    private long aadharNo;
    private String name;
    private int ctValue;
    private boolean isPositive;

    public PersonForTestingBooth
(long aadharNo, String name) {
        this.aadharNo = aadharNo;
        this.name = name;
        ctValue=0;
        isPositive=false;
    }

    public long getAadharNo() {
        return aadharNo;
    }

    public void setCtValue(int ctValue) {
        this.ctValue = ctValue;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }
    public void displayDetails()
    {
        System.out.println(name+" is "+ (isPositive ?"Positive":"Negative")+" with CT " +
                "Value: "+ctValue);
    }

    public int getCtValue() {
        return ctValue;
    }

    public boolean isPositive() {
        return isPositive;
    }
}

public class TestingBooth {
    String day;
    private LinkedHashSet<PersonForTestingBooth
> personList = new LinkedHashSet<>();
    private LinkedHashSet<PersonForTestingBooth
> criticalPatients = new LinkedHashSet<>();

    public TestingBooth(String day) {
        this.day = day;
    }
    public boolean registerPerson(PersonForTestingBooth
 p)
    {
        return personList.add(p);
    }
    public void testPerson(){
        Iterator it = personList.iterator();
        while(it.hasNext())
        {
            PersonForTestingBooth
 p = (PersonForTestingBooth
)it.next();
            //System.out.println("PersonForTestingBoothwith ID: "+p.getAadharNo()+ " is being tested."); " +
            Random r = new Random();
            int ct = r.nextInt(50);
            p.setCtValue(ct);
            if(ct>=10 && ct<35)
            {
                p.setPositive(true);
            }
            else if(ct > 35)
            {
                p.setPositive(false);
            }
        }
    }
    public void getPositives()
    {
        LinkedHashSet<PersonForTestingBooth
> all = new LinkedHashSet<>();
        all.addAll(personList);
        all.addAll(criticalPatients);
        Iterator it = all.iterator();
        while(it.hasNext())
        {
            PersonForTestingBooth
 p = (PersonForTestingBooth
)it.next();
            if(p.isPositive())
            {
                p.displayDetails();
            }
        }
    }
    private void getCriticals()
    {
        Iterator it = personList.iterator();
        while(it.hasNext())
        {
            PersonForTestingBooth
 p = (PersonForTestingBooth
)it.next();
            if(p.getCtValue() < 15 && p.getCtValue() >= 10)
            {
                criticalPatients.add(p);
                it.remove();
            }
        }
    }
    public void showCriticals()
    {
        getCriticals();
        Iterator it = criticalPatients.iterator();
        while(it.hasNext())
        {
            PersonForTestingBooth
 p = (PersonForTestingBooth
)it.next();
            if(p.getCtValue() < 15)
            {
                p.displayDetails();
            }
        }
    }
    public void displaySituation()
    {
        Iterator it = personList.iterator();
        while(it.hasNext())
        {
            PersonForTestingBooth
 p = (PersonForTestingBooth
)it.next();
            p.displayDetails();
        }
    }
}
