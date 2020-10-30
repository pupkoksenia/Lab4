package com.bsu.by;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.simple.coollection.Coollection.*;

public class Main {
    public static void main(String[] args) {
        try {


            System.out.println("the name of input file is: " + args[0]);
            System.out.println("the name of output file is: " + args[1]);

            List<Company> arrayOfCompanies = null;
            Scanner sc = new Scanner(System.in);


            File myObj = new File("C:\\Users\\Lab4\\" + args[0] + ".txt");


            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            List<String> arrayOfLines = readAndMakeListOfLines(args[0] + ".txt");
            arrayOfCompanies = makeClassesCompany(arrayOfLines);


            for (Company com : arrayOfCompanies) {
                System.out.println(com);
            }

            System.out.println("----------------------------------------------------------");
            File file = new File("C:\\Users\\Lab4\\" + args[1] + ".txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            int CountFound = 0;



            file = new File("C:\\Users\\Lab4\\logfile.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fr);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            bw.write(dateFormat.format(new Date()) + ";" + CountFound + "\n");



            File mySQL = new File("C:\\Users\\Lab4\\requests.txt");

            FileReader frr = new FileReader(mySQL);
            try (BufferedReader br = new BufferedReader(frr)) {

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);


                    switch (line){
                        case "SELECT * from company_table where shortName=@shortName":
                            String ShortName = sc.next();
                            for (Company com : from(arrayOfCompanies).where("shortName", eqIgnoreCase(ShortName)).all()) {
                                writer.write(com.toString());
                                CountFound++;
                            }
                            break;
                        case "SELECT * from company_table order by kindOfActivity":
                            for (Company com : from(arrayOfCompanies).orderBy("kindOfActivity").all()) {
                                writer.write(com.toString());
                                CountFound++;
                            }
                            break;
                        case "SELECT * from company_table  where amountOfEmploye >@start and amountOfEmploye <@end order by amountOfEmploye":
                            System.out.print("Write up value of amount of people");
                            int upNumber = sc.nextInt();
                            System.out.print("Write down value of amount of people");
                            int downNumber = sc.nextInt();

                            for (Company com : from(arrayOfCompanies).where("amountOfEmploye", greaterThan(downNumber)).and("amountOfEmploye", lessThan(upNumber)).orderBy("amountOfEmploye").all()) {
                                writer.write(com.toString());
                                CountFound++;
                            }
                            break;
                    }

                }
                frr.close();
                bw.close();
                fr.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            frr.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<String> readAndMakeListOfLines(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fr)) {

            String line;

            List<String> arrayOfLines = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                arrayOfLines.add(line);
            }
            br.close();
            fr.close();
            return arrayOfLines;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static boolean isDigit(String s) {
        String regex = "[0-9]*\\.?[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }


    private static List<Company> makeClassesCompany(List<String> arrayOfLines) {
        List<Company> companies = new ArrayList<Company>();


        for (String line : arrayOfLines) {
            List<String> first = new ArrayList<>();
            for (String splitLine : line.split(";")) {
                first.add(splitLine);
            }

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

            companies.add(a);
        }


        return companies;


    }


    private static class Company {

        private String name;
        private String shortName;
        private Integer actualDate;
        private String address;
        private Integer foundDate;
        private Integer amountOfEmploye;
        private String auditor;
        private Integer phoneNumber;
        private String email;
        private String branch;
        private String kindOfActivity;
        private String addressInTheInternet;

        public String getName() {
            return name;
        }

        public Integer getActualDate() {
            return actualDate;
        }

        public String getShortName() {
            return shortName;
        }

        public String getAddress() {
            return address;
        }

        public Integer getFoundDate() {
            return foundDate;
        }

        public Integer getAmountOfEmploye() {
            return amountOfEmploye;
        }

        public String getAuditor() {
            return auditor;
        }

        public Integer getPhoneNumber() {
            return phoneNumber;
        }

        public String getAddressInTheInternet() {
            return addressInTheInternet;
        }

        public String getEmail() {
            return email;
        }

        public String getBranch() {
            return branch;
        }

        public String getKindOfActivity() {
            return kindOfActivity;
        }

        @Override
        public String toString() {
            return name + ";" + shortName + ";" + actualDate + ";" + address + ";" + foundDate + ";" + amountOfEmploye
                    + ";" + auditor + ";" + phoneNumber + ";" + email + ";" + branch + ";" + kindOfActivity + ";" + addressInTheInternet + "\n";
        }


        void addStrings(String Name, String ShortName, String Address, String Auditor, String Email,
                        String Branch, String KindOfActivity, String AddressInTheInternet) {
            this.name = Name;
            this.shortName = ShortName;
            this.address = Address;
            this.auditor = Auditor;
            this.email = Email;
            this.branch = Branch;
            this.kindOfActivity = KindOfActivity;
            this.addressInTheInternet = AddressInTheInternet;
        }

        void addIntegers(Integer ActualDate,
                         Integer FoundDate, Integer AmountOfEmploye, Integer PhoneNumber) {
            this.actualDate = ActualDate;
            this.foundDate = FoundDate;
            this.amountOfEmploye = AmountOfEmploye;
            this.phoneNumber = PhoneNumber;
        }

    }


}