package com.bsu.by;

import java.io.File;
import java.io.IOException;

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
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
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
