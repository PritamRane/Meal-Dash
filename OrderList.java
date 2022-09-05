import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class OrderList extends Order{
    HashMap<Integer,Order> orderList =new HashMap<>();



    public OrderList() {
       
    }

    public OrderList(HashMap<Integer,Order> orderList) {
        this.orderList = orderList;
    }

    public HashMap<Integer,Order> getOrderList() {
        return this.orderList;
    }

    public void setOrderList(HashMap<Integer,Order> orderList) {
        this.orderList = orderList;
    }

    
    


    public void readOrderDetails(String filename){


        try {
			File file = new File(filename);
			FileReader fr = new FileReader(file);

			BufferedReader br = new BufferedReader(fr);

			String line;
            orderList.clear(); // in order to clear hashmaps
			while ((line = br.readLine()) != null) { 
                String[] detail = line.split(",");
                HashMap<Integer,Integer> products =new HashMap<>();
                for(int i=3;i<detail.length-1;i+=2){
                    products.put(Integer.valueOf(detail[i]) ,Integer.valueOf(detail[i+1]));

                }
                    Order order = new Order((long)Integer.valueOf(detail[0]),Integer.valueOf(detail[1]),(String)(detail[2]),products);
                    orderList.put((int)Integer.valueOf(detail[0]), order);
                
                
                
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
