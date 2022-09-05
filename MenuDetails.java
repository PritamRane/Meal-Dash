import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class MenuDetails extends Item{
    

    HashMap<Integer,Item> menuDetails =new HashMap<>();

    public MenuDetails() {
    }

    public MenuDetails(HashMap<Integer,Item> menuDetails) {
        this.menuDetails = menuDetails;
    }

    public HashMap<Integer,Item> getMenuDetails() {
        return this.menuDetails;
    }

    public void setMenuDetails(HashMap<Integer,Item> menuDetails) {
        this.menuDetails = menuDetails;
    }

    

    @Override
    public String toString() {
        return "{" +
            " menuDetails='" + getMenuDetails() + "'" +
            "}";
    }

   

    public void readMenuDetails(String filename){


        try {
           
            
			File file = new File(filename);
			FileReader fr = new FileReader(file);

			BufferedReader br = new BufferedReader(fr);

			String line;
            menuDetails.clear();
			while ((line = br.readLine()) != null) { 
                String[] detail = line.split(",");
                Item item = new Item(Integer.valueOf(detail[0]) ,(String)detail[1],Integer.valueOf(detail[2]),(String)(detail[3]),Integer.valueOf(detail[4]));
                menuDetails.put(item.getItemId(), item);
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
