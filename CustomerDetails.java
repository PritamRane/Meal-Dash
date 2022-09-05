import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CustomerDetails extends Customer {
    HashMap<Integer,Customer> customerList =new HashMap<>();


    public CustomerDetails() {
    }

    public CustomerDetails(HashMap<Integer,Customer> customerList) {
        this.customerList = customerList;
    }

    public HashMap<Integer,Customer> getCustomerList() {
        return this.customerList;
    }

    public void setCustomerList(HashMap<Integer,Customer> customerList) {
        this.customerList = customerList;
    }

   

    @Override
    public String toString() {
        return "{" +
            " customerList='" + getCustomerList() + "'" +
            "}";
    }

   
    public void writeCustomer() throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\nEnter Username : "+consoleColors.RESET);
        String username = scanner.next();
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\nEnter Password : "+consoleColors.RESET);
        String password = scanner.next();
        password = encrypt(password);
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\nEnter Mobile No : "+consoleColors.RESET);
        String mobileNo = scanner.next();


        BufferedWriter out = null;

    try {
            FileWriter fstream = new FileWriter("CustomerInfo.txt", true); //true tells to append data.
    
            Integer list[] 
            = customerList.keySet().toArray(new Integer[customerList.size()]);
                    int id = customerList.get(list[list.length - 1]).getCustomerId();
                    
                    
                    
                    String customer = (id+1)+","+username+","+password+","+mobileNo;
                    consoleProgress consoleProgress = new consoleProgress();
                    consoleProgress.progress("Creating Account");
                    System.out.println(consoleColors.YELLOW_BOLD_BRIGHT+"Thankyou "+username+" Your account is created."+consoleColors.RESET);
                    
                    Admin admin = new Admin();
                    admin.enter();
                    admin.flush();
                    out = new BufferedWriter(fstream);
                    out.write("\n"+customer);
    }

        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        finally {
            if(out != null) {
            out.close();
        }
    }

}

    public void checkCustomerType() throws Exception{
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        admin.flush();
        repeatCharacters repeatCharacters = new repeatCharacters();
        
        String choice = "";
        outer:
        while (choice != "3") {
            repeatCharacters.printBox("1. New Customer","2. Existing Customer","3. Exit to Main Menu");
            System.out.print(consoleColors.YELLOW_BOLD_BRIGHT+"\n\nEnter Choice : "+consoleColors.RESET);
            choice = scanner.next();
            switch (choice) {
                case "1":
                    writeCustomer();
                    break outer;
            
                case "2":
                    break outer;

                case "3":
                    break outer;
                    
                default:
                    System.out.println("\n\n"+consoleColors.RED_BOLD_BRIGHT+"!!! Invalid Choice !!!"+consoleColors.RESET);
                    Thread.sleep(500);
                    admin.flush();
                    break;
            }
            
        }
    }


    public int checkCustomerDetails() throws Exception{
        Scanner scanner = new Scanner(System.in);
        int flag = 0;
        int id = 0;
        while(flag != 1){
            
            CommandLineTable commandLineTable = new CommandLineTable();
            commandLineTable.setShowVerticalLines(true);
        commandLineTable.setHeaders("Enter Credentials");
        commandLineTable.print();
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\n\nUsername : "+consoleColors.RESET);
        String username = scanner.next();
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\n\nPassword : "+consoleColors.RESET);
        String password = scanner.next();
        
        for (Entry<Integer, Customer>   i: customerList.entrySet()) {
        String user  = i.getValue().getUsername();
        String pass = i.getValue().getPassword();
            if(username.equals(user) && encrypt(password).equals(pass)){

                    flag = 1;
                    
                     id = i.getValue().getCustomerId();
                    
            }
                
            
        }
        if(flag == 0)
            System.out.println(consoleColors.RED_BOLD_BRIGHT+"!!! Invalid Details !!!"+consoleColors.RESET);
            Thread.sleep(500);
            Admin admin = new Admin();
            admin.flush();

            
    }
    return id;
        

    }

    

    public String orderQuantity() throws InterruptedException
    {
        MenuDetails menuDetails=new MenuDetails();
        menuDetails.readMenuDetails("Menu.txt");
        String details = "";
    
        Set<Integer> keys  = new HashSet<Integer>();
            int selection = 1;
            while (selection != 2) {
                CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setShowVerticalLines(true);
        commandLineTable.setHeaders("ID","Name");
                System.out.println("\n\n"+consoleColors.BLUE_BOLD_BRIGHT+"Select one"+consoleColors.RESET);
                int availability;
                for (int i = 1;i<menuDetails.menuDetails.size()+1;i++) {
                    availability = menuDetails.menuDetails.get(i).getAvailability();
                    if(availability == 1){
                        commandLineTable.addRow(String.valueOf(menuDetails.menuDetails.get(i).getItemId()),menuDetails.menuDetails.get(i).getName());
                        keys.add(menuDetails.menuDetails.get(i).getItemId());
                    }
                            
                }
                commandLineTable.print();

                Scanner scanner = new Scanner(System.in);
                int id = Integer.MAX_VALUE;
                System.out.print("\n\n"+consoleColors.BLUE_BOLD_BRIGHT+"Enter Choice : "+consoleColors.RESET);
                id = scanner.nextInt();
                
                while ((id<=0) || !keys.contains(id)) {
                    System.out.println(consoleColors.RED_BOLD_BRIGHT+"!!!Invalid Input!!!"+consoleColors.RESET);
                System.out.print("\n\n"+consoleColors.BLUE_BOLD_BRIGHT+"Enter Choice : "+consoleColors.RESET);
                
                id = scanner.nextInt();
            }  
                 
            
                System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"Enter Quantity : "+consoleColors.RESET);
                int quantity = scanner.nextInt();
                while(quantity<=0){
                    System.out.println(consoleColors.RED_BOLD_BRIGHT+"\n!!!Invalid Input!!!"+consoleColors.RESET);
                    Thread.sleep(200);
                    System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"Enter Quantity : "+consoleColors.RESET);
                    quantity = scanner.nextInt();

                }
                details+=id+","+quantity+",";
                repeatCharacters.printBox("1) Add More","2) Thats it");
                System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\nEnter Choice : "+consoleColors.RESET);
                selection = scanner.nextInt();

            }
            details = details.substring(0, details.length() - 1);  
            return details;
    }


    public void writeOrder(String detail, int custId) throws IOException, InterruptedException{

        BufferedWriter out = null;

try {
    FileWriter fstream = new FileWriter("Orders.txt", true); //true tells to append data.
    

    OrderList orderList = new OrderList();
            Date date = new Date();
            Random rand = new Random();
            int orderId;
            while(true){
                orderId = rand.nextInt(1000);
                if(!orderList.orderList.containsKey(orderId))
                    break; 
            }
            String order = orderId+","+custId+","+date+","+detail;
            out = new BufferedWriter(fstream);
            out.write("\n"+order);
            consoleProgress consoleProgress = new consoleProgress();
            consoleProgress.order();
            Admin.enter();
            Admin.flush();
}

catch (IOException e) {
    System.err.println("Error: " + e.getMessage());
}

finally {
    if(out != null) {
        out.close();
    }
}



        
    }

    public int confirmOrder(String detail, int custId) throws IOException, InterruptedException{
        System.out.println("Confirm Order ?");
        repeatCharacters.printBox("1. Yes","2. No");
        Scanner scanner = new Scanner(System.in);
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\n\nEnter Option : "+consoleColors.RESET);
        int confirm =0;
        int choice = scanner.nextInt();
        outer:
        switch (choice) {
            case 1:
                writeOrder(detail, custId);
                confirm = 1;
            
            case 2:
                break outer;
        
            default:
                break;
        }

        return confirm;
    }


    public void getRecipts(int custId){
        OrderList orderList = new OrderList();
        orderList.readOrderDetails("Orders.txt");
        MenuDetails menuDetails=new MenuDetails();
        menuDetails.readMenuDetails("Menu.txt");
        Double total = 0.0;
        Double cgst = 0.09;
        Double sgst = 0.09;
        Double grandTotal = 0.0;
        System.out.println("Name: "+customerList.get(custId).getUsername());

        for (Map.Entry<Integer,Order>  i: orderList.orderList.entrySet()) {
            total = 0.0;
                CommandLineTable st = new CommandLineTable();
                int customerId = i.getValue().getCustomerId();
                
            

            if(customerId == custId ){
                System.out.println("\n\n\n");
                System.out.println(consoleColors.YELLOW_UNDERLINED +"ORD#"+i.getValue().getOrderId()+consoleColors.RESET);
                repeatCharacters.printBox("UserName : "+customerList.get(customerId).getUsername(),"Order Id : ORD#"+i.getValue().getOrderId(),"Order Time : "+i.getValue().getOrderTime());
                System.out.println("");
                st.setShowVerticalLines(true);
                st.setHeaders("Name","Quantity","Price");
          
                for (Map.Entry<Integer,Integer>  j: i.getValue().getDetails().entrySet()) {
                    st.addRow(menuDetails.menuDetails.get(j.getKey()).getName(),String.valueOf(j.getValue()) ,String.valueOf(menuDetails.menuDetails.get(j.getKey()).getPrice()) );
             
                    total+=j.getValue()*menuDetails.menuDetails.get(j.getKey()).getPrice();
                }
                st.print();
                System.out.println("");


                grandTotal = total+sgst*total+cgst*total;

               
                System.out.println(consoleColors.CYAN_BOLD_BRIGHT+"SGST  : "+String.format("%.2f", cgst*total)+consoleColors.RESET);
                System.out.println(consoleColors.CYAN_BOLD_BRIGHT+"CGST  : "+String.format("%.2f", cgst*total)+consoleColors.RESET);
                System.out.println(consoleColors.CYAN_BOLD_BRIGHT+"Total : "+String.format("%.2f", grandTotal)+consoleColors.RESET);
                

            }
        }
        Admin.enter();
        Admin.flush();
    }

    public void customerOptions(int custId) throws IOException, InterruptedException{
        

        String name = repeatCharacters.printBox("Welcome, "+customerList.get(custId).getUsername());
        Admin.flush();
        repeatCharacters.slowChars(name,20);
String choice = "";
       
        Scanner scanner = new Scanner(System.in);

        
while(choice !="3"){
    System.out.println("\n"+consoleColors.YELLOW_UNDERLINED+"Select an option"+consoleColors.RESET+"\n");
    repeatCharacters.printBox("1. Order Something","2. Get All Recipts","3. Logout");
        System.out.print("\n\n"+consoleColors.BLUE_BOLD_BRIGHT+"Enter Choice : "+consoleColors.RESET);
        choice = scanner.next();
        switch (choice) {
            case "1":
                String detail = orderQuantity();
                confirmOrder(detail, custId);
                break;

            case "2":
                getRecipts(custId);
                break;

            case "3":
            choice = "3";
            Admin.flush();
            break;
        
            default:
                System.out.println(consoleColors.RED_BRIGHT+"\n!!!  Invalid Input  !!!\n"+consoleColors.RESET);
                Thread.sleep(1000);
                Admin.flush();
                break;
        }


    }

        

    }

    public static String encrypt(String strClearText) throws Exception{
        String encryptedpassword = null;  
        try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(strClearText.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }  

        return encryptedpassword;
          
    }

    public void readCustomerDetails(String filename){


        try {
			File file = new File(filename);
			FileReader fr = new FileReader(file);

			BufferedReader br = new BufferedReader(fr);

			String line;
            Admin admin = new Admin();
            admin.removeEmptyLines(filename);
            customerList.clear(); // in order to clear hashmaps
			while ((line = br.readLine()) != null) { 
                String[] detail = line.split(",");
                if(!customerList.containsKey(Integer.valueOf(detail[0]))){
                    Customer customer = new Customer(Integer.valueOf(detail[0]) ,(String)detail[1],(String)detail[2],(String)(detail[3]));
                    customerList.put(customer.getCustomerId(), customer);
                }
                
                
            }

			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occured while reading the file.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An error occured while reading the file.");
			e.printStackTrace();
		}


        
    }


}
