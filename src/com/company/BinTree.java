package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BinTree {
    Node root;

    public void addNode(String data){
        Node newNode = new Node(data);
        //if the root node is empty, sets the new node to be the root node
        if(root == null){
            root = newNode;
        }
        // if it is not empty it sets the focusNode to be the root node, and initiates a Node called parent. The focusNode will be the programs reference point.
        else{
            Node focusNode = root;
            Node parent;
            while(true){
                parent = focusNode;
                //it the word already exists in the tree, the counter for the node containing the word will increase by 1.
                if(data.compareTo(focusNode.data) == 0){
                    focusNode.counter += 1;
                    return;
                }
                // if the new word comes before the focusNode word alphabetically, the focusNode's left child will be selected as the new focusNode. if this is empty, it will be set to the nedNode
                else if(data.compareTo(focusNode.data)< 0){
                    focusNode = focusNode.leftChild;
                    if(focusNode == null){
                        parent.leftChild = newNode;
                        return;
                    }
                } 
                else{
                    focusNode = focusNode.rightChild;
                    if(focusNode == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }
    
    //Traverses the tree and writes it out.
    public void traverseTreeInOrder(Node focusNode){
        if(focusNode != null){
            traverseTreeInOrder(focusNode.leftChild);
            System.out.println(focusNode);
            traverseTreeInOrder(focusNode.rightChild);
        }
    }

    //specifies a node
    class Node{
        String data;
        int counter;
        Node leftChild;
        Node rightChild;
        
        Node(String word){
            this.data = word;
            this.counter = 1;
        }
        public String toString(){
            return data + " Occurs "+ counter + " times";
        }
    }

    public static void main(String[] args) {
        Init();
    }

    //initiates the program. user enters filepath to read
    public static void Init(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the file path of the  txt file to read without extension like this: (C:\\User\\Desktop\\Filename)");
        String filename = sc.nextLine();
        String File = filename + ".txt";
        System.out.println(File);
        ReadFile(File);
    }

    public static void ReadFile(String file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            BinTree tree = new BinTree();
            String everything = sb.toString();
            String words[] = everything.split("[^\\p{L}']+");
            for (int i = 0; i < words.length;i++){
                words[i] = words[i].toUpperCase();
                tree.addNode(words[i]);
            }
            System.out.println(everything);
            System.out.println(Arrays.toString(words));
            tree.traverseTreeInOrder(tree.root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}


