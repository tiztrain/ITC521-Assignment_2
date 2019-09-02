import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: 2/09/2019 - comment out file better 

public class TaxManagementSystem {
    public TaxManagementSystem(){

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
                task1.writeTax();
                selectionNumber = task1.mainMenu();
            }
        }


    }

    public void writeTax() {
        EmployeeTax employee = new EmployeeTax();

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter enter the Employee's four digit ID number:");
        employee.setEmpID(scan.nextInt());
        //int empID = scan.nextInt();
        System.out.println("Please enter the Employee's annual income as a number with two decimal places:");
        employee.setSalaryBeforeTax(scan.nextDouble());
        //double salaryBeforeTax = scan.nextDouble();


        employee.setTax();

        int empID = employee.getEmpID();
        double salaryBeforeTax = employee.getSalaryBeforeTax();
        double tax = employee.getTax();

        /* % means the value after the comma
        - mean that there is a buffer of x characters to the right
        28 is the buffer length
        s means that it displays the string
         */
        System.out.printf("\nThe following information has been written to taxreport.txt");
        System.out.printf("%-28s%-28s%-28s", "Employee ID", "Taxable Income", "Tax");
        System.out.println();
        System.out.printf("%-28s%-28s%-28s", empID, salaryBeforeTax, tax);

        // TODO: 2/09/2019 - write the data to file
    }

    public int mainMenu() {
        Scanner input = new Scanner(System.in);

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


//    @Override
//    public String toString() {
//        return "Tax{" +
//                "empID=" + empID +
//                ", salaryBeforeTax=" + salaryBeforeTax +
//                ", tax=" + tax +
//                '}';
//    }

}