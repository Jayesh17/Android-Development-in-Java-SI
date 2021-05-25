package com.company;
import java.util.*;
import java.io.*;

class Link
{
    private long aadharNo;
    private String name;
    public Link next;

    Link()
    {
        aadharNo=-99;
        name="";
        next=null;
    }
    Link(long aadharNo, String nm)
    {
        this.aadharNo = aadharNo;
        name = nm;
        this.next=null;
    }
    public long getaadharNo()
    {
        return aadharNo;
    }
    public void display()
    {
        System.out.println(aadharNo+" "+name);
    }
}

class LinkList
{
    private Link first;
    private Link last;
    private int count;
    LinkList()
    {
        count=0;
        first=null;
        last= null;
    }
    public int totalSize()
    {
        return count;
    }
    public void addFirst(long aadharNo,String nm)
    {
        Link newNode = new Link(aadharNo,nm);
        newNode.next = first;
        first = newNode;
        count++;
    }
    public Link getFirst()
    {
        return first;
    }
    public void addNode(long aadharNo,String nm)
    {
        count++;
        Link newNode = new Link(aadharNo,nm);
        if(first == null)
        {
            first = newNode;
            last=newNode;
            return;
        }
        last.next = newNode;
        last = newNode;
    }
    public void displayList()
    {
        Link curr = first;
        while(curr != last.next)
        {
            curr.display();
            curr= curr.next;
        }
    }
}
class BinaryTreeNode
{
    private Link infoNode;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    BinaryTreeNode()
    {
        infoNode = null;
        left = null;
        right = null;
    }
    BinaryTreeNode(Link node)
    {
        infoNode = node;
        left = null;
        right = null;
    }
    public void treeNodeDisplay()
    {
        infoNode.display();
    }
    public long getInfo()
    {
        return infoNode.getaadharNo();
    }
}
class BinaryTree
{
    private BinaryTreeNode root;
    BinaryTree()
    {
        root = null;
    }
    public void placeNodeInTree(BinaryTreeNode tnode,BinaryTreeNode root)
    {
        if(tnode.getInfo() < root.getInfo())
        {
            if(root.left == null)
            {
                root.left=tnode;
                return;
            }
            placeNodeInTree(tnode,root.left);
        }
        else if(tnode.getInfo() > root.getInfo())
        {
            if(root.right == null)
            {
                root.right=tnode;
                return;
            }
            placeNodeInTree(tnode,root.right);
        }
    }
    public void addTreeNode(Link node)
    {
        BinaryTreeNode tnode = new BinaryTreeNode(node);
        if(root==null)
        {
            root = tnode;
            return;
        }
        placeNodeInTree(tnode,root);
    }
    public void display(BinaryTreeNode root)
    {
        if(root != null)
        {
            display(root.left);
            root.treeNodeDisplay();
            display(root.right);
        }
    }
    public void displayTree()
    {
        display(root);
    }
}
public class Main {

    public static void createListFromFile(LinkList list)
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
                    list.addNode(aadharNo,name);
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
    public static BinaryTree createTree(LinkList list)
    {
        Link curr = list.getFirst();
        BinaryTree treeRoot = new BinaryTree();
        while(curr != null)
        {
            treeRoot.addTreeNode(curr);
            curr = curr.next;
        }
        return treeRoot;
    }
    public static void main(String[] args) {

        LinkList personList = new LinkList();
        createListFromFile(personList);
        personList.displayList();
        System.out.println(personList.totalSize());

        //binary tree
        BinaryTree treeRoot = createTree(personList);
        treeRoot.displayTree();
    }
}
