import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;







public class Admin {
    

    String[] detail;

    public static void flush(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void enter(){
        Scanner scanner = new Scanner(System.in);
        System.out.print(consoleColors.YELLOW_BOLD+"\nPress Enter to continue..."+consoleColors.RESET);
        scanner.nextLine();
    }

    public void checkCredentials() throws Exception{
        repeatCharacters repeatCharacters = new repeatCharacters();
        
        Scanner scanner = new Scanner(System.in);
        int flag = 0;
        String user = "";
        String pass = "";

        try {
			File file = new File("admin.txt");
			FileReader fr = new FileReader(file);

			BufferedReader br = new BufferedReader(fr);

			String line;
            line = br.readLine();
		 
                detail = line.split(",");
                user = detail[0];
                pass = detail[1];
                
                
            

			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occured while reading the file.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An error occured while reading the file.");
			e.printStackTrace();
		}



    while(flag != 1){
        System.out.println("\n");
        repeatCharacters.printBox("    Enter Credentials   ");
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\n\nUsername : "+consoleColors.RESET);
        String username = scanner.next();
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\n\nPassword : "+consoleColors.RESET);
        String password = scanner.next();

        
            if(username.equals(user) && CustomerDetails.encrypt(password).equals(pass)){
                    adminOptions();
                    flag = 1;   
            }
                
            
        
        if(flag == 0){
            System.out.println(consoleColors.RED_BOLD_BRIGHT+"!!!  Invalid Details  !!!"+consoleColors.RESET);
            Thread.sleep(500);
            flush();
        }
            

            
    }
    }


    public void getAllOrders(){
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.readCustomerDetails("CustomerInfo.txt");
        OrderList orderList = new OrderList();
        orderList.readOrderDetails("Orders.txt");
        MenuDetails menuDetails=new MenuDetails();
        menuDetails.readMenuDetails("Menu.txt");
        Double total = 0.0;
        Double cgst = 0.09;
        Double sgst = 0.09;
        Double grandTotal = 0.0;
        
        for (Entry<Integer, Order>  i: orderList.orderList.entrySet()) {
                total = 0.0;
                CommandLineTable st = new CommandLineTable();
                int customerId = i.getValue().getCustomerId();
                System.out.println("\n\n\n");
                System.out.println(consoleColors.YELLOW_UNDERLINED +"ORD#"+i.getValue().getOrderId()+consoleColors.RESET);
                repeatCharacters.printBox("UserName : "+customerDetails.customerList.get(customerId).getUsername(),"Order Id : ORD#"+i.getValue().getOrderId(),"Order Time : "+i.getValue().getOrderTime());
                System.out.println("");
                st.setShowVerticalLines(true);
                st.setHeaders("Name","Quantity","Price");

                for (Entry<Integer, Integer>  j: i.getValue().getDetails().entrySet()) {
                    
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
            enter();
            flush();
        }
    


    public void addFoodItem() throws IOException, InterruptedException{

        BufferedWriter out = null;

try {
    FileWriter fstream = new FileWriter("Menu.txt", true); //true tells to append data.
    
    MenuDetails menuDetails=new MenuDetails();
    menuDetails.readMenuDetails("Menu.txt");
    
    int id = menuDetails.menuDetails.get(menuDetails.menuDetails.size()).getItemId();
    
    Scanner scanner = new Scanner(System.in);
    System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\nEnter Food Name: "+consoleColors.RESET);
    String name = scanner.next();
    System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\nEnter Price: "+consoleColors.RESET);
    int price = scanner.nextInt();
    String type = "";
    String choice = "";
    System.out.println(consoleColors.BLUE_BOLD_BRIGHT+"\nSelect Type\n"+consoleColors.RESET);
    outer:
    while (choice != "5") {
        repeatCharacters.printBox("1. Veg","2. Non-Veg","3. Desert","4. Beverage");
       
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"Enter Choice : "+consoleColors.RESET);
        choice = scanner.next();

        switch (choice) {
            case "1":
                type = "veg";
                break outer;
            case "2":
                type = "non-veg";
                break outer;
            case "3":
                type = "desert";
                break outer;

            case "4":
                type = "beverage";
                break outer;
        
            default:
                break;
        }
    }
    int availability;
    System.out.println(consoleColors.BLUE_BOLD_BRIGHT+"\n\nSelect Availability "+consoleColors.RESET);
    repeatCharacters.printBox("1. Yes","2. No");
    System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"Enter Choice : "+consoleColors.RESET);
    availability= scanner.nextInt();

    
            String menu = (id+1)+","+name+","+price+","+type+","+availability;
            out = new BufferedWriter(fstream);
            out.write("\n"+menu);
            consoleProgress consoleProgress = new consoleProgress();
            consoleProgress.progress("Updating");
            enter();
            flush();
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


    public void editMenu(String details,String oldDetail,String filename) throws FileNotFoundException{
   //Instantiating the File class
   String filePath = filename;
   //Instantiating the Scanner class to read the file
   Scanner sc = new Scanner(new File(filePath));
   //instantiating the StringBuffer class
   StringBuffer buffer = new StringBuffer();
   //Reading lines of the file and appending them to StringBuffer
   while (sc.hasNextLine()) {
      buffer.append(sc.nextLine()+System.lineSeparator());
   }
   String fileContents = buffer.toString();
//    System.out.println("Contents of the file: "+fileContents);
   //closing the Scanner object
   sc.close();
   String oldLine = oldDetail;
   String newLine = details;
   //Replacing the old line with new line
   fileContents = fileContents.replaceAll(oldLine, newLine);
   try (//instantiating the FileWriter class
FileWriter writer = new FileWriter(filePath)) {
    System.out.println("");
    //    System.out.println("new data: "+fileContents);
       writer.append(fileContents);
       writer.flush();
} catch (FileNotFoundException e) {
    throw e;
} catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
    }


    public void generateInsights(){
        flush();
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.readCustomerDetails("CustomerInfo.txt");
        OrderList orderList = new OrderList();
        orderList.readOrderDetails("Orders.txt");
        MenuDetails menuDetails=new MenuDetails();
        menuDetails.readMenuDetails("Menu.txt");
        HashMap<Integer, Integer> MostSold = new HashMap<>();
        Double total = 0.0;
        Double cgst = 0.09;
        Double sgst = 0.09;
        Double grandTotal = 0.0;
        Double revenue= 0.0;
        System.out.println(consoleColors.CYAN_BOLD_BRIGHT+"\n\t\t\t| INSIGHTS |\n\n"+consoleColors.RESET);
        for (Entry<Integer, Order>  i: orderList.orderList.entrySet()){
            
                for (Entry<Integer, Integer>  j: i.getValue().getDetails().entrySet()) {
                    
               
                    if(MostSold.containsKey(j.getKey())){
                        MostSold.merge(j.getKey(), j.getValue(), (oldValue, newValue) -> oldValue + newValue);
 
                    }
                    else{
                        MostSold.put(j.getKey(), j.getValue());
                    }
               
                    total+=j.getValue()*menuDetails.menuDetails.get(j.getKey()).getPrice();
                }
                grandTotal = total+sgst*total+cgst*total;

                revenue+=grandTotal;
        }

        int maxValueInMap = (Collections.max(MostSold.values()));
        int mostSoldId = 0;
        int mostSoldValue = 0;
        // Iterate through HashMap
        for (Entry<Integer, Integer> entry :
             MostSold.entrySet()) {
 
            if (entry.getValue() == maxValueInMap) {
 
                mostSoldId =  entry.getKey();
                mostSoldValue = entry.getValue();
            }
        }
        String name = menuDetails.menuDetails.get(mostSoldId).getName();
        CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setShowVerticalLines(true);
        
        commandLineTable.setHeaders("Total Revenue Generated","Most Sold Product","No sold");
        commandLineTable.addRow(String.valueOf(revenue),name,String.valueOf(mostSoldValue));
        commandLineTable.print();
       

        enter();
        flush();



    }

    public void changeAvailability() throws FileNotFoundException, InterruptedException{
        flush();
        MenuDetails menuDetails=new MenuDetails();
        menuDetails.readMenuDetails("Menu.txt");
        String details = "";
        System.out.println(consoleColors.BLUE_BOLD_BRIGHT+"\n\t| Select one |"+consoleColors.RESET);
        CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setShowVerticalLines(true);
        commandLineTable.setHeaders("ID","Name");
        for (int i = 1;i<menuDetails.menuDetails.size()+1;i++) {
            commandLineTable.addRow(String.valueOf(menuDetails.menuDetails.get(i).getItemId()) ,menuDetails.menuDetails.get(i).getName());
        }
        commandLineTable.addRow("0","Go Back to main menu");
        commandLineTable.print();
        Scanner scanner = new Scanner(System.in);
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"Enter Choice : "+consoleColors.RESET);
        int id = scanner.nextInt();
        if(id == 0){
            flush();
            return;
        }
        System.out.println(consoleColors.BLUE_BOLD_BRIGHT+"\nMake It Available? "+consoleColors.RESET);
        repeatCharacters.printBox("1. Yes","2. No");
        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"Enter Choice: "+consoleColors.RESET);
        int availability = scanner.nextInt();
        String name = menuDetails.menuDetails.get(id).getName();
        int price = (int)menuDetails.menuDetails.get(id).getPrice();
        String type = menuDetails.menuDetails.get(id).getType();
        int avail = menuDetails.menuDetails.get(id).getAvailability();
        details=id+","+name+","+price+","+type+","+availability;
        String oldDetails=id+","+name+","+price+","+type+","+avail;
        consoleProgress consoleProgress = new consoleProgress();
        consoleProgress.progress("Updating");
        editMenu(details, oldDetails,"Menu.txt");

        flush();
    }
    

    public void getAllUsers(){
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.readCustomerDetails("CustomerInfo.txt");
        CommandLineTable commandLineTable = new CommandLineTable();
        
        commandLineTable.setHeaders("ID","Name","Mobile No");
        commandLineTable.setShowVerticalLines(true);
        for (Entry<Integer, Customer>   i: customerDetails.customerList.entrySet()) {

            int id = i.getValue().getCustomerId();
            String username = i.getValue().getUsername();
            String mobileNo = i.getValue().getMobileNo();

      
            commandLineTable.addRow(String.valueOf(id),username,String.valueOf(mobileNo));
            
        }

        commandLineTable.print();
        enter();
        flush();
    }

    public void removeEmptyLines(String filename) throws IOException{

        Scanner file;
        PrintWriter writer;

        try {

            file = new Scanner(new File(filename));
            writer = new PrintWriter("temp.txt");

            while (file.hasNext()) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.write("\n");
                }
            }

            file.close();
            writer.close();
            file = new Scanner(new File("temp.txt"));
            writer = new PrintWriter(filename);

            while (file.hasNext()) {
                String line = file.nextLine();
                    writer.write(line);
                    writer.write("\n");
                
            }
            
            file.close();
            writer.close();
            RandomAccessFile raf = new RandomAccessFile(filename,"rw");
            raf.seek(raf.length()-1);
            raf.close();
            File delete = new File("temp.txt");
 
        if (delete.delete()) {
            // System.out.println("File deleted successfully");
        }
        else {
            // System.out.println("Failed to delete the file");
        }




        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void removeCustomer() throws IOException, InterruptedException{
        flush();
        CustomerDetails customerDetails = new CustomerDetails();
        Scanner scanner = new Scanner(System.in);
        customerDetails.readCustomerDetails("CustomerInfo.txt");
        Set<Integer> keys  = new HashSet<Integer>();
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("ID","Username");
        for (Entry<Integer, Customer>   i: customerDetails.customerList.entrySet()) {

            int id = i.getValue().getCustomerId();
            String username = i.getValue().getUsername();
            st.addRow(String.valueOf(id),username);
            
        }
        st.addRow("0","Go Back to main menu");
        st.print();

        for (Entry<Integer,Customer>  i:customerDetails.customerList.entrySet()) {
               
                keys.add(i.getValue().getCustomerId());
            
                    
        }

        System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\n\nEnter ID : "+consoleColors.RESET);
        int id = Integer.MAX_VALUE;
        id = scanner.nextInt();
        if(id == 0){
            flush();
            return;
        }
        while ((id<0) || !keys.contains(id)) {
            System.out.println(consoleColors.RED_BOLD_BRIGHT+"!!! Invalid Input !!!"+consoleColors.RESET);
            Thread.sleep(400);    
            System.out.print(consoleColors.BLUE_BOLD_BRIGHT+"\n\nEnter ID : "+consoleColors.RESET);
                id = scanner.nextInt();
        }

        String username = customerDetails.customerList.get(id).getUsername();
        String password = customerDetails.customerList.get(id).getPassword();
        String mobileNo = customerDetails.customerList.get(id).getMobileNo();

        String details = id+","+username+","+password+","+mobileNo;
        consoleProgress consoleProgress = new consoleProgress();
        consoleProgress.progress("Removing");
        editMenu("",details,"CustomerInfo.txt"); 
        removeEmptyLines("CustomerInfo.txt");

    }


    public void adminOptions() throws IOException, InterruptedException{
        consoleProgress consoleProgress = new consoleProgress();
        consoleProgress.loginAndOut("Loggin In");
        flush();
        System.out.println("\n\n");
        repeatCharacters.printBox("Welcome, "+detail[0]);
        String choice = "";

        Scanner scanner = new Scanner(System.in);
        outer:
        while(choice != "6"){
           
            

            
                System.out.println(consoleColors.YELLOW_UNDERLINED+consoleColors.YELLOW_BOLD_BRIGHT+"\n\nSelect an option"+consoleColors.RESET);
                System.out.println("\n");
                repeatCharacters.printBox("1. Get All orders","2. Add Food","3. Edit Availability","4. Get All User Info","5. Get Insights","6. Remove Customers","7. Logout");     
                System.out.print(consoleColors.YELLOW_BOLD_BRIGHT+"\n\nEnter Option : "+consoleColors.RESET);
                choice = scanner.next();
                switch (choice) {
                    case "1":
                        getAllOrders();
                        break;

                    case "2":
                        addFoodItem();
                        break;

                    case "3":
                        changeAvailability();
                        break;
                    
                    case "4":
                        getAllUsers();
                        break;
                    
                    case "5":
                        generateInsights();
                        break;
                    
                    case "6":
                        removeCustomer();
                        break;
                    
                    case "7":
                        consoleProgress.loginAndOut("Logging Out");
                        flush();
                        break outer;
                    
                    default:
                        System.out.println(consoleColors.RED_BOLD_BRIGHT+"\n  Invalid Input!!!  "+consoleColors.RESET);
                        Thread.sleep(1000);
                        flush();
                        break;
                }
        
        

            
        }


    }


}
