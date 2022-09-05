
import java.util.Scanner;


public class mealDash {


    public mealDash() throws Exception {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.readCustomerDetails("CustomerInfo.txt");
        Admin admin = new Admin();
        
        int choice = 0;
        while(choice!=3){
            repeatCharacters.printBox("Welcome to Meal Dash");
            Thread.sleep(500);
        System.out.println("\n  **Select an option**\n");
        repeatCharacters.printBox("1. Admin 2.Customer 3.Exit");
        Scanner scanner = new Scanner(System.in);
        System.out.print(consoleColors.YELLOW_BOLD_BRIGHT+"\nEnter Option : "+consoleColors.RESET);
         choice= scanner.nextInt();
        
        switch (choice) {
            case 1:
                admin.checkCredentials();
                break;
        
            case 2:

                customerDetails.checkCustomerType();
                customerDetails.readCustomerDetails("CustomerInfo.txt");
               int custId = customerDetails.checkCustomerDetails();
               customerDetails.customerOptions(custId);
               

                break;

            case 3:
                System.out.println(consoleColors.CYAN_BOLD_BRIGHT+"Thankyou for using Meal Dash. Visit Again!!!"+consoleColors.RESET);
                Thread.sleep(3000);
                break;


            default:
            System.out.println(consoleColors.RED_BRIGHT+"\n!!!  Invalid Input  !!!\n"+consoleColors.RESET);
                break;
        }
        }
        
        

    }
}
