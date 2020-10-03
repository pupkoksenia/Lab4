package com.bsu.by;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import static org.simple.coollection.Coollection.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("the name of input file is: " + args[0]);
        System.out.println("the name of output file is: " + args[1]);

        List<Company> secondStep=null;
        Scanner sc = new Scanner(System.in);

        try {
            File myObj = new File("C:\\Users\\Lab4\\InputText.txt");


            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            List<String> firstStep = readAndMakeListOfLines("InputText.txt");
            secondStep = makeClassesCompany(firstStep);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (Company com : secondStep) {
            System.out.println(com.ToCsv());
        }
        System.out.println("----------------------------------------------------------");
        System.out.print("Write up value of amount of people");
        int upNumber = sc.nextInt();
        System.out.print("Write down value of amount of people");
        int downNumber=sc.nextInt();

        for (Company com : from(secondStep).where("AmountOfEmploye", greaterThan(downNumber)).and("AmountOfEmploye",lessThan(upNumber)).orderBy("AmountOfEmploye").all()) {
            System.out.println(com.ToCsv());
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

    public static boolean isDigit(String s) {
        String regex = "[0-9]*\\.?[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }


    private static List<Company> makeClassesCompany(List<String> ArrayOfLines) {
        List<Company> Companies = new ArrayList<Company>();

        for (String line : ArrayOfLines) {
            int beg = 0;
            List<String> first = new ArrayList<>();

            StringBuilder str = new StringBuilder(line);

            for (int j = 0; j < line.length(); j++) {

                if (line.charAt(j) == ';') {
                    char[] dst = new char[j - beg];
                    str.getChars(beg, j, dst, 0);
                    first.add(String.valueOf(dst));
                    beg = j + 1;

                }
            }
            char[] dst = new char[line.length() - beg];
            str.getChars(beg, line.length(), dst, 0);
            first.add(String.valueOf(dst));
            //first.add(dst.toString());

            List<String> second = new ArrayList<>();
            List<Integer> third = new ArrayList<>();

            for (String f : first) {
                if (isDigit(f)) {
                    third.add(Integer.valueOf(f));
                } else second.add(f);
            }


            Company a = new Company();
            a.addIntegers(third.get(0), third.get(1), third.get(2), third.get(3));
            a.addStrings(second.get(0), second.get(1), second.get(2), second.get(3),
                    second.get(4), second.get(5), second.get(6), second.get(7));

            Companies.add(a);
        }


        return Companies;


    }


    static class Company {
        String Name;
        String ShortName;
        Integer ActualDate;
        String Address;
        Integer FoundDate;
        Integer AmountOfEmploye;
        String Auditor;
        Integer PhoneNumber;
        String Email;
        String Branch;
        String KindOfActivity;
        String AddressInTheInternet;

        public String ToCsv() {
            return Name+";"+ShortName+";"+ActualDate+";"+Address+";"+FoundDate+";"+AmountOfEmploye
                    +";"+Auditor+";"+PhoneNumber+";"+Email+";"+Branch+";"+KindOfActivity+";"+AddressInTheInternet;

        }

        void addStrings(String Name, String ShortName, String Address, String Auditor, String Email,
                        String Branch, String KindOfActivity, String AddressInTheInternet) {
            this.Name = Name;
            this.ShortName = ShortName;
            this.Address = Address;
            this.Auditor = Auditor;
            this.Email = Email;
            this.Branch = Branch;
            this.KindOfActivity = KindOfActivity;
            this.AddressInTheInternet = AddressInTheInternet;
        }

        void addIntegers(Integer ActualDate,
                         Integer FoundDate, Integer AmountOfEmploye, Integer PhoneNumber) {
            this.ActualDate = ActualDate;
            this.FoundDate = FoundDate;
            this.AmountOfEmploye = AmountOfEmploye;
            this.PhoneNumber = PhoneNumber;
        }

    }
}