package com.bsu.by;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("the name of input file is: " + args[0]);
        System.out.println("the name of output file is: " + args[1]);


        try {
            File myObj = new File("C:\\Users\\Lab4\\InputText.txt");


            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            readAndMakeListOfLines("InputText.txt");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    private static List<String> readAndMakeListOfLines(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        List<String> ArrayOfLines = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            ArrayOfLines.add(line);
        }
        br.close();
        fr.close();
        return ArrayOfLines;
    }

    private static List<Company> makeClassesCompany(List<String> ArrayOfLines){
        List<Company> Companies = new ArrayList<Company>();
    }
    class Company {
        String Name;
        String ShortName;
        Integer ActualDate;
        Integer Address;
        Integer FoundDate;
        Integer AmountOfEmploye;
        String Auditor;
        Integer PhoneNumber;
        String Email;
        String Branch;
        String KindOfActivity;
        String AddressInTheInternet;

        Company(String Name, String ShortName, Integer ActualDate, Integer Address, Integer FoundDate, Integer AmountOfEmploye, String Auditor, Integer PhoneNumber, String Email, String Branch, String KindOfActivity, String AddressInTheInternet) {
            this.Name = Name;
            this.ShortName = ShortName;
            this.ActualDate = ActualDate;
            this.Address = Address;
            this.FoundDate = FoundDate;
            this.AmountOfEmploye = AmountOfEmploye;
            this.Auditor = Auditor;
            this.PhoneNumber = PhoneNumber;
            this.Email = Email;
            this.Branch = Branch;
            this.KindOfActivity = KindOfActivity;
            this.AddressInTheInternet = AddressInTheInternet;
        }

    }
}