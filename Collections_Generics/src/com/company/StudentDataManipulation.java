package com.company;
import java.io.RandomAccessFile;
import java.util.*;

public class StudentDataManipulation {

    private class Student
    {
        private String name;
        private Hashtable<String,Integer> subjectMarks = new Hashtable(5);
        char grade;
        private float percent;
        public Student(String name)
        {
            this.name=name;
            grade='X';
        }
        public float getPercent() {
            return percent;
        }

        public String getName() {
            return name;
        }

        public void addSubjectMarks(String sub, int marks)
        {
            subjectMarks.put(sub,marks);
        }
        public char setGrade()
        {
            int total=0;
            Iterator it = subjectMarks.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry en = (Map.Entry)it.next();
                total+=(int)en.getValue();
            }
            percent = ((float)total/500)*100;
            if(percent >= 80)
            {
                grade='A';
            }
            else if(percent >= 70 && percent < 80)
            {
                grade='B';
            }
            else if(percent >= 60 && percent < 70)
            {
                grade='C';
            }
            else
            {
                grade='D';
            }
            return grade;
        }
        public void studentDetails()
        {
            System.out.println("------------------------------");
            System.out.println("Name: "+name);
            Iterator it = subjectMarks.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry en = (Map.Entry)it.next();
                System.out.println(en.getKey()+":"+en.getValue());
            }
            System.out.println("Percentage :" +percent);
            System.out.println("Grade : "+grade);
            System.out.println("-------------------------------");
        }
    }
    private class minmaxComparator implements Comparator
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            Student s1 = (Student)o1;
            Student s2 = (Student)o2;

            if(s1.getPercent() > s2.getPercent())
                return 1;
            else if(s1.getPercent() < s2.getPercent())
                return -1;
            else
                return 0;
        }
    }
    private class sortComparator implements Comparator
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            Student s1 = (Student)o1;
            Student s2 = (Student)o2;

            if(s1.getPercent() < s2.getPercent())
                return 1;
            else if(s1.getPercent() > s2.getPercent())
                return -1;
            else
                return 0;
        }
    }

    private Hashtable<Long,Student> Students = new Hashtable<>();
    private HashMap<Long,Character> results = new HashMap<>();

    public StudentDataManipulation(){}
    public boolean getData(String filename)
    {
        long id = 12000;
        try
        {
            RandomAccessFile file = new RandomAccessFile(filename,"r");
            while(file.getFilePointer() != file.length())
            {
                String nm = file.readLine();
                Student st = new Student(nm);
                Random rd = new Random();
                int marks[] = new int[5];
                for(int i=0;i<5;i++)
                    marks[i] = 50+ (rd.nextInt(45));

                st.addSubjectMarks("Data Structures",marks[0]);
                st.addSubjectMarks("Programming",marks[1]);
                st.addSubjectMarks("System Programming",marks[2]);
                st.addSubjectMarks("Computer Networks",marks[3]);
                st.addSubjectMarks("Software Engeneering",marks[4]);

                id++;
                Students.put(id,st);

            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public void calculateGrades()
    {
        Iterator it = Students.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry en = (Map.Entry)it.next();
            Student s = (Student) en.getValue();
            char grade = s.setGrade();
            results.put((Long)en.getKey(),grade);
        }
    }
    public void showGradeByID(long ID)
    {
        if(results.containsKey(ID))
            System.out.println("StudentID: "+ID+" Grade: "+results.get(ID));
        else
            System.out.println("student Not Found.");
    }
    public void listStudentsByGrade(Character grade)
    {
        results.forEach((key,value)->{
            if(value==grade)            {
                Student s = (Student)Students.get(key);
                System.out.println("Student ID: "+key+" Name: "+s.getName());
            }
        });
    }
    public void diplayRecords()
    {
        Iterator it = Students.entrySet().iterator();
        while(it.hasNext())
        {
            System.out.println("----------------------------------------");
            Map.Entry en = (Map.Entry)it.next();
            System.out.println("Student ID: "+en.getKey());
            Student s = (Student)en.getValue();
            s.studentDetails();
            System.out.println("----------------------------------------");
        }
    }
    public void displayByID(long ID)
    {
        if(Students.containsKey(ID))
        {
            Student s = Students.get(ID);
            s.studentDetails();
        }
        else
        {
            System.out.println("This ID does not exists.");
        }
    }
    public void getHighestRankedStudent()
    {
        Vector<Student> v = new Vector<>();
        Students.forEach((key,value)->{
            v.add(value);
        });
        Student s1 = (Student) Collections.max(v,new minmaxComparator());
        s1.studentDetails();
    }
    public void getLowestRankedStudent()
    {
        Vector<Student> v = new Vector<>();
        Students.forEach((key,value)->{
            v.add(value);
        });
        Student s1 = (Student) Collections.min(v,new minmaxComparator());
        s1.studentDetails();
    }
    public void top10Students()
    {
        Vector<Student> v = new Vector<>();
        Students.forEach((key,value)->{
            v.add(value);
        });
        Collections.sort(v,new sortComparator());
        for(int i=0;i<10;i++)
        {
            v.elementAt(i).studentDetails();
        }
    }
}
