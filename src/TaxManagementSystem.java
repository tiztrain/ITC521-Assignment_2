import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* TODO: 2/09/2019 - comment out file better
 *   create the search function*/


public class TaxManagementSystem {
    public TaxManagementSystem() {

    }

    public void ViewRates() throws FileNotFoundException {
        // variables
        String output = "";
        List<Double> valList = new ArrayList<>();

        // Create a File instance
        File inputRateFile = new File("taxrates.txt");
        if (inputRateFile.exists()) {

            // Create a Scanner for the file
            Scanner input = new Scanner(inputRateFile);

            // Read data from a file
            while (input.hasNext()) {
                // Save value to output variable
                output += input.next() + " ";
            }

            // remove commas from output
            output = output.replace(",", "");
            output = output.replace("h $1", "h");
            output = output.replace("$", "");

            // Output header to screen
            System.out.printf("%-28s%-28s%-28s%-28s%-28s", "Min", "Max", "Tax Previous Bracket", "TaxRate", "Over");
            System.out.println();

            // Creating regex pattern object
            Pattern p = Pattern.compile("\\d+(?:\\.\\d+)?");
            Matcher m = p.matcher(output);

            // add values from regex list to defined list of values
            while (m.find()) {
                valList.add(Double.parseDouble(m.group()));
            }

            // add values as placeholders where there is missing data
            for (int i = 0; i < valList.size(); i++) {
                if (i == 3) {
                    valList.add(i, 0.0);
                } else if (i == 4) {
                    valList.add(i, 0.0);
                } else if (i == 7) {
                    valList.add(i, 0.0);
                } else if (i == 21) {
                    valList.add(i, 9999999.0);
                }
            }

            // start on new line after the over column value
            for (int i = 0; i < valList.size(); i++) {
                System.out.printf("%-28s", (valList.get(i)));
                if (i == 4) {
                    System.out.println();
                }
                if (i == 9) {
                    System.out.println();
                }
                if (i == 14) {
                    System.out.println();
                }
                if (i == 19) {
                    System.out.println();
                }
            }

            System.out.println();
            System.out.println();

            // Close the file
            input.close();

        }

        // covers if user did not have a taxrate file
        else {
            System.out.println("Provide a taxrates.txt file as input and rerun program");
        }
    }


    public static void main(String[] args) throws IOException {
        int selectionNumber;

        System.out.println("Welcome to Tax Management System of XYZ");

        TaxManagementSystem task1 = new TaxManagementSystem();
        selectionNumber = task1.mainMenu();

        while (selectionNumber != 3) {
            if (selectionNumber == 1) {
                task1.ViewRates();
                task1.writeTaxReport();
                selectionNumber = task1.mainMenu();
            } else if (selectionNumber == 2) {
                task1.searchTaxReport();
                selectionNumber = task1.mainMenu();
            } else {
                System.out.println("Incorrect value given. Please provide a number that is associated with the task required");
                selectionNumber = task1.mainMenu();
            }
        }
    }

    public void writeTaxReport() {
        //create employee object from employeetax object
        EmployeeTax employee = new EmployeeTax();

        //create scan object to read user input in console
        Scanner scan = new Scanner(System.in);

        //asking the user for input
        System.out.println("Please enter enter the Employee's four digit ID number:");
        employee.setEmpID(scan.nextInt());
        System.out.println("Please enter the Employee's annual income as a number with two decimal places:");
        employee.setSalaryBeforeTax(scan.nextDouble());

        //call the setTax method of the object to calculate the tax number
        employee.setTax();

        // assign method results to variables
        int empID = employee.getEmpID();
        double salaryBeforeTax = employee.getSalaryBeforeTax();
        double tax = employee.getTax();

        //using this display to show that it works
        /* % means the value after the comma
        - mean that there is a buffer of x characters to the right
        28 is the buffer length
        s means that it displays the string
         */
        System.out.printf("\nThe following information has been written to taxreport.txt\n");
        System.out.printf("%-28s%-28s%-28s", "Employee ID", "Taxable Income", "Tax");
        System.out.println();
        System.out.printf("%-28s%-28s%-28s", empID, salaryBeforeTax, tax);

        //writing the data to file
        PrintWriter printWriter = null;
        try {
            File file = new File("taxreport.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            printWriter = new PrintWriter(fileWriter);
            printWriter.printf("%-28s%-28s%-28s", empID, salaryBeforeTax, tax);
            printWriter.println();
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    public void searchTaxReport() throws FileNotFoundException {
        // variables
        String output = "";
        // list that holds all items from the text file
        LinkedList<String> valList = new LinkedList<>();
        // list that removes the string values from valList
        LinkedList<Double> numList = new LinkedList<>();
        /*list used to hold the final details of the employee. If the employee is in the
         * numList twice, just show the last occurance*/
        LinkedList<Double> empDetailList = new LinkedList<>();
        empDetailList.add(0, 0.0);
        empDetailList.add(1, 0.0);
        empDetailList.add(2, 0.0);

        // Create a File instance
        File file = new File("taxreport.txt");
        if (file.exists()) {
            //variables
            int i = 0;

            System.out.println("Found taxreport.txt");

            // Create a Scanner for the file
            Scanner scanner = new Scanner(file);
            // Read data from a file
            while (scanner.hasNext()) {
                // Save value to output variable if the value is a number
                String tempStr = (scanner.next());
                valList.add(i, tempStr);
                i += 1;
            }
            System.out.println("valList = " + valList.toString());

            // j used as a counter in numList in the forloop
            int j = 0;
            // if value in ValList is a number, add to numList otherwise skip
            for (i = 0; i < valList.size(); i++) {
                /* checking whether the value was numeric or not
                if numeric, create a new cluster, if not then move to next
                 */
                boolean numeric = true;
                // if the value is not numeric the catch stops the program from ending but changes numeric to false
                try {
                    double num = Double.parseDouble(valList.get(i));
                }
                // changes numeric to false if there is an error
                catch (NumberFormatException e) {
                    numeric = false;
                }

                // only runs if numeric is true
                if (numeric) {
                    numList.add(j, Double.parseDouble(valList.get(i)));
                    j++;
                }
            }
            System.out.println("numList = " + numList.toString());
        } else {
            System.out.println("Could not find taxreport.txt");
        }

        //ask user for employee id and co
        System.out.println("Please enter an employee ID to search through the tax file for: ");
        Scanner scan = new Scanner(System.in);
        int userIdInput = scan.nextInt();


        //check if employee id is the same as every 3rd number value
        int i;

        for (i = 0; i < numList.size(); i = i + 3) {
            //convert double number in numList to integer
            Double d = numList.get(i);
            int listValueInt = d.intValue();

            //check if user input matches any of the employee ids in list and display details
            if (userIdInput == listValueInt) {
//                //#used to display values to screen to test
//                System.out.println();
//                System.out.println("EmployeeID: " + userIdInput);
//                System.out.println("Salary Before Tax: " + numList.get(i+1));
//                System.out.println("Tax: " + numList.get(i+2));

                //overwrites the values in empDetailList if found
                empDetailList.set(0, numList.get(i));
                empDetailList.set(1, numList.get(i + 1));
                empDetailList.set(2, numList.get(i + 2));
            }
        }

        //Display results
        System.out.println();
        System.out.println("EmployeeID: " + empDetailList.get(0));
        System.out.println("Salary Before Tax: " + empDetailList.get(1));
        System.out.println("Tax amount for year: " + empDetailList.get(2));

    }


    public int mainMenu() {
        //create scanner object to accept input from user into the console
        Scanner input = new Scanner(System.in);

        //instructions for user
        System.out.println("\n\nPlease select one of the following options:");
        System.out.println("1. Calculate tax\n2. Search tax\n3. Exit\n");
        System.out.print("Please enter a integer: ");
        int result = input.nextInt();

        return result;
    }
}

class EmployeeTax {
    //temporary variables for testing
    double taxRate;

    int empID;
    double salaryBeforeTax;
    double tax = 0;

    public EmployeeTax() {

    }

    public EmployeeTax(int empID, double salaryBeforeTax, double tax) {
        this.empID = empID;
        this.salaryBeforeTax = salaryBeforeTax;
        this.tax = tax;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public double getSalaryBeforeTax() {
        return salaryBeforeTax;
    }

    public void setSalaryBeforeTax(double salaryBeforeTax) {
        this.salaryBeforeTax = salaryBeforeTax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax() {
        // TODO: 2/09/2019 - fix up the hard coding
        if (salaryBeforeTax >= 0 && salaryBeforeTax <= 18200) {
            tax = 0;
        } else if (salaryBeforeTax > 18200 && salaryBeforeTax <= 37000) {
            taxRate = 0.19;
            tax = taxRate * (salaryBeforeTax - 18200);
        } else if (salaryBeforeTax > 37000 && salaryBeforeTax <= 90000) {
            taxRate = 0.325;
            tax = 3572 + taxRate * (salaryBeforeTax - 37000);
        } else if (salaryBeforeTax > 90000 && salaryBeforeTax <= 180000) {
            taxRate = 0.37;
            tax = 20797 + taxRate * (salaryBeforeTax - 90000);
        } else if (salaryBeforeTax > 180000) {
            taxRate = 0.45;
            tax = 54097 + taxRate * (salaryBeforeTax - 180000);
        } else {
            System.out.println("The input that has been provided is invalid");
            System.exit(0);
        }
        //tax = taxPreviousBracket + (salaryBeforeTax - maxPreviousBracket) * taxRate;
    }
}