package com.company;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Main {

    public static void ArrayListFun()
    {
        ArrayList<String> arrList = new ArrayList<>();
        arrList.add("Jayesh");
        arrList.add("Meha");
        arrList.add("Joy");
        arrList.add("Dhoni");
        arrList.add("Indian Army");
        ArrayListPrac<String> AL = new ArrayListPrac<>(arrList);
        AL.displayList();

        AL.getSize();
        ArrayList<String> arrList1 = new ArrayList<>();
        arrList1.add("Jay");
        AL.setNew(arrList1);
        AL.displayList();
    }

    public static void createListFromFile(TestingBooth MondayTesting)
    {
        try
        {
            RandomAccessFile file = new RandomAccessFile("F:\\SI-2021\\Java\\DataStructures" +
                    "\\person_list.txt","r");

            while(file.getFilePointer() != file.length())
            {
                String line = file.readLine();
                String name;
                long aadharNo;
                String[] fields = line.split("-");

                aadharNo = Long.parseLong(fields[0]);
                name = fields[1];
                Person p = new Person(aadharNo,name);
                MondayTesting.registerPerson(p);
            }
            file.close();
        }
        catch(IOException err)
        {
            System.out.println(err);
        }
        finally
        {
            System.out.println("Reading completed.");
        }
    }
    public static void main(String[] args) {
	// write your code here
        //System.out.println("hii");
        TestingBooth MondayTesting = new TestingBooth("Monday");
        createListFromFile(MondayTesting);
        MondayTesting.testPerson();
        //MondayTesting.displaySituation();

        MondayTesting.displaySituation();
        System.out.println("----------");
        MondayTesting.showCriticals();
        System.out.println("----------");
        MondayTesting.getPositives();
    }
}
