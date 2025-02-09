/**
 * @(#) OkuyigaD002PA1.java
 * @author David Okuyiga
 * @version 1.00 2024/10/07 2:00 PM
 * 
 * PROGRAM PURPOSE:
 * generates a weekly payroll report for
 * hourly employees.
 */
import java.util.Calendar;   //Make accessible the class for getting a date.
import java.util.Scanner;   //Make accessible the class for getting input.

public class OkuyigaD002PA1
{
  private static Scanner input = new Scanner(System.in);   //REF object to capture keyboard entries.
  
  /**
   * Hourly employee work and 401k contributions are
   * entered to generate a weekly payroll report with
   * corresponding totals.
   */
  public static void main(String[] args)
  {
    String first = "",  //Stores someone's first name.
           last = "";   //Stores someone's last name.
  
    double payRate = 0.0,  //
           grossPay = 0.0, //
           empl401k = 0.0, //
           percent401k = 0.0,  //
           grossPayAllEmpl = 0.0,  //
           total401kAllEmpl = 0.0; //
    
    int hoursWorked = 0,  //
        trigger = 1;  //
    
    char cont = 'y';  //
    
    Calendar dateTime = Calendar.getInstance();  //
    
    String payrollExpense = String.format
      ("WEEKLY HOURLY EMPLOYEE PAYROLL"
      + "%n%nDate:  %tD"
      + "%nTime:  %tr"
      + "%n%n%56S %23s", dateTime, dateTime,
        "GROSS PAY", "401k");
    
    //1st Prompt
    System.out.printf("%nWEEKLY HOURLY PAYROLL SYSTEM%n"
                    + "%nContinue?  Enter \'Y\' or \'N\':  ");
    cont = input.nextLine().toLowerCase().charAt(0);
    
    if(cont == 'n')
    {
      System.out.printf("%nExiting Weekly Hourly Payroll System.%n");
    }//END if cont is n
    
    while(cont == 'y')
    {
      //2nd Prompt
      System.out.printf("%nEnter the employee\'s "
                     + "first name press enter then "
                     + "the last name press enter:  ");
      first = input.nextLine();
      last = input.nextLine();
      
      //3rd Prompt
      System.out.printf("%nEnter the number of hours "
                      + "worked for %s:  ", first);
      hoursWorked = validateIntegers(input.hasNextInt());
      while(hoursWorked > 40 || hoursWorked < 5){
        if (hoursWorked > 40) {
      System.out.printf("Hours worked cannot exceed 40. Please re-enter: ");
      } else if (hoursWorked < 5) {
        System.out.print("Hours worked cannot be LESS than 5. Please re-enter: ");
      }
      hoursWorked = validateIntegers(input.hasNextInt());
    }
      //4th Prompt
      System.out.print("Enter the employee's hourly pay rate: ");
      payRate = validateDecimals(input.hasNextDouble());
      while (payRate < 7.25 || payRate > 26.00) {
        if (payRate < 7.25) {
            System.out.print("Hourly pay cannot be LESS than $7.25. Please re-enter: ");
        } else if (payRate > 26.00) {
            System.out.print("Hourly pay cannot EXCEED $26.00. Please re-enter: ");
        }
        payRate = validateDecimals(input.hasNextDouble());
      }
      //Calculate gross pay
      grossPay = hoursWorked * payRate;
      grossPayAllEmpl += grossPay;
      
      //5th Prompt
      System.out.print("Enter the employee's 401k contribution as a percentage of salary (not to exceed 10%%): ");
      percent401k = validateDecimals(input.hasNextDouble());
      while (percent401k > 10.00){
      System.out.printf("Contribution cannot EXCEED 10%. Please re-enter: ");
      percent401k = validateDecimals(input.hasNextDouble());
      }
      //Calculate 401k contribution
      empl401k = (percent401k / 100) * grossPay;
      total401kAllEmpl += empl401k;
      
      if(trigger ==1)
      {
        payrollExpense += String.format
                          ("%n%-30s %8s $%,15.2f %8s $%,13.2f", 
                           first + " " + last, " ", grossPay,
                           " ", empl401k);
        trigger = 0;
      }
      else
      {
        payrollExpense += String.format
                          ("%n%-30s %9s $%,15.2f %9s %,13.2f", 
                           first + " " + last, " ", grossPay,
                           " ", empl401k);
      }//END if trigeer is 1 else trigger is not 1
      
      //Clear buffer
      input.nextLine();
      
      //6th Prompt
      System.out.print("Enter 'Y' to add another employee or 'N' to exit: ");
      cont = input.nextLine().toLowerCase().charAt(0);
      
      payrollExpense += String.format("%n%n%25s TOTALS %6s "
                      + "$%,15.2f %8s $%,13.2f", " ", " ",
                      grossPayAllEmpl, " ", total401kAllEmpl);
      
      System.out.printf("%n%n%s", payrollExpense);  //PRINTS
                       //THE FINAL OUTPUT SOTRED IN payrollExpense
    }
        
      System.exit(0);
}//END main()
  
  //CODE validateIntegers()
/**
 * validateIntegers() accepts a boolean reflecting
 * whether a number is an integer (true) or not (false)
 * for an employee's hours worked. Uses a while loop
 * to re-prompt until the value is an integer which is
 * returned to be the calling statement.
 */
public static int validateIntegers(boolean isInt)
{
  int anInt = 0;  //Sotres a validated integer value
  
  while(!isInt)
  {
    input.next();
    System.out.printf("%nInvalid integer! Re-enter "
       + "the hours worked:  ");
    isInt = input.hasNextInt();
  }//END while NOT an integer
  
  anInt = input.nextInt();
  
  return anInt;
}//END validateIntegers(isInt:  boolean): static int
  
  //CODE validateDecimals() HERE
/**
 * validateDecimals() accepts a boolean reflecting
 * whether a number is a decimal (true) or not (false)
 * for an employee's pay rate or 401k contribution
 * percentage. Uses a while loop to re-prompt until
 * the value is a double which is returned to the calling
 * statement.
 */
public static double validateDecimals(boolean isDouble)
{
  double aDouble = 0.0;  //Stores a validated decimal value.
  while(!isDouble)
  {
    input.next();
    System.out.printf("%nInvalid decimal!  Re-enter "
       + "the pay rate or the 401k percentage "
       + "contribution:  ");
    isDouble = input.hasNextDouble();
  }//END while payRate or percent401k is NOT a decimal
  
  aDouble = input.nextDouble();
  
  return aDouble;
}//END validateDecimals(isDouble:  boolean): static double
}//END APPLICATION CLASS OkuyigaD002PA1
    
    