package com.company;

import java.util.*;

class PersonForTBA
{
    private long aadharNo;
    private String name;

    public PersonForTBA(long aadharNo, String name) {
        this.aadharNo = aadharNo;
        this.name = name;
    }

    public long getAadharNo() {
        return aadharNo;
    }

    public String getName() {
        return name;
    }
    @Override
    public int hashCode()
    {
        int hash = (int)(aadharNo%50);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        PersonForTBA p = (PersonForTBA) obj;

        return (p.name.equals(this.name)  && p.aadharNo == this.aadharNo);
    }
}
class Positives
{
    private PersonForTBA p;
    private int ctValue;

    public Positives(PersonForTBA p, int ctValue) {
        this.p = p;
        this.ctValue = ctValue;
    }

    public PersonForTBA getP() {
        return p;
    }

    public int getCtValue() {
        return ctValue;
    }
}
class PositivesComparator implements Comparator
{
    @Override
    public int compare(Object o1, Object o2) {
        Positives p1 = (Positives) o1;
        Positives p2 = (Positives) o2;
        if(p1.getCtValue() < p2.getCtValue())
        {
            return -1;
        }
        else if(p1.getCtValue() > p2.getCtValue())
        {
            return 1;
        }
        return 0;
    }
}
public class TestingBoothAdvance {
    private HashMap<PersonForTBA,Integer> personList = new HashMap<>();
    private PriorityQueue<Positives> positiveList = new PriorityQueue<>(new PositivesComparator());
    private HashMap<Long,Positives> fastSearchList = new HashMap<>();

    public void registerPerson(long aadharNo, String name)
    {
        PersonForTBA p = new PersonForTBA(aadharNo,name);
        personList.put(p,0);
    }

    public void testing()
    {
        Iterator it = personList.entrySet().iterator();
        while(it.hasNext())
        {
            Random r = new Random();
            int ct = 10+ r.nextInt(40);
            Map.Entry<PersonForTBA,Integer> en = (Map.Entry)it.next();
            en.setValue(ct);
        }
    }
    public void displayRegistrations()
    {
        Iterator it = personList.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<PersonForTBA,Integer> en = (Map.Entry)it.next();
            PersonForTBA p = (PersonForTBA) en.getKey();
            System.out.println("Person ID: "+p.getAadharNo()+ " Name: "+p.getName());
        }
        System.out.println("Total person : "+personList.size());
    }
    public void displayAfterTesting()
    {
        Iterator it = personList.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<PersonForTBA,Integer> en = (Map.Entry)it.next();
            PersonForTBA p = (PersonForTBA) en.getKey();
            System.out.println("Person ID: "+p.getAadharNo()+ " ,Name: "+p.getName()+" ,CT Value:" +
                    " "+en.getValue());
        }
        System.out.println("Total person : "+personList.size());
    }
    public void findPositives()
    {
        Iterator it = personList.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<PersonForTBA,Integer> en = (Map.Entry)it.next();
            if(en.getValue() <= 35)
            {
                PersonForTBA p = (PersonForTBA) en.getKey();
                Positives obj = new Positives(p,en.getValue());
                positiveList.add(obj);
                fastSearchList.put(obj.getP().getAadharNo(),obj);
            }
        }
    }
    public void showPositives()
    {
        System.out.println("-------------------------------------");
        Iterator it = positiveList.iterator();

        while(it.hasNext())
        {
            Positives p = (Positives) it.next();
            System.out.println("AadharNo : "+p.getP().getAadharNo()+", Name : "+p.getP().getName()+", CT Value : "+p.getCtValue());
        }
        System.out.println("-------------------------------------");
    }
    public void getTop5Criticals()
    {
        int n =5;
        System.out.println("-------------------------------------");
        while(n>0)
        {
            Positives p = positiveList.remove();
            System.out.println("Name : "+p.getP().getName()+", CT value : "+p.getCtValue());
            n--;
        }
        System.out.println("-------------------------------------");
    }
    public void getPersonDetails(long aadharNo)
    {
        if(fastSearchList.containsKey(aadharNo))
        {
            Positives p = fastSearchList.get(aadharNo);
            System.out.println("Patient Details :");
            System.out.println("Name : "+p.getP().getName());
            System.out.println("ID : "+p.getP().getAadharNo());
            System.out.println("CT Value : "+p.getCtValue());
        }
    }
}
