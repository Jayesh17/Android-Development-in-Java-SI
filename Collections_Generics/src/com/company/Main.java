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
                PersonForTestingBooth p = new PersonForTestingBooth(aadharNo,name);
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
    public static void setImplementation()
    {
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
    public static void setMapList(TestingBoothAdvance TBA)
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
                TBA.registerPerson(aadharNo,name);
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
    public static void MapImplementation()
    {
        TestingBoothAdvance TBA = new TestingBoothAdvance();
        setMapList(TBA);
        //TBA.displayRegistrations();
        TBA.testing();
        //TBA.displayAfterTesting();
        TBA.findPositives();
        TBA.showPositives();
        TBA.getTop5Criticals();
        TBA.getPersonDetails(17);
    }
    public static void studentImplementation()
    {
        StudentDataManipulation stRecords = new StudentDataManipulation();
        boolean readDone = stRecords.getData("F:\\SI-2021\\Java\\DataStructures" +
                "\\Students.txt");
        if(readDone)
        {
            //stRecords.diplayRecords();
//            stRecords.displayByID(12010);
            stRecords.calculateGrades();
//            stRecords.showGradeByID(12010);
            //stRecords.listStudentsByGrade('B');
            stRecords.top10Students();
            stRecords.getHighestRankedStudent();
            stRecords.getLowestRankedStudent();
        }
        else
        {
            System.out.println("error");
        }
    }
    public static void main(String[] args) {
        studentImplementation();
    }
}
