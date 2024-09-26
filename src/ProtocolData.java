import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProtocolData {
    
    public static Map<String, String> loadData(String filePath) {
        Map<String, String> protocolMap = new HashMap<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            
            br.readLine(); // skipping one line for the header
            while ((line = br.readLine()) != null) {
                String fields[] = line.split(",");
                
                if (fields.length == 1) {
                    String decimal = fields[0];
                    
                    protocolMap.put(decimal, "");
                }
                else {
                    String decimal = fields[0];
                    String protocol = fields[1].toLowerCase();
                
                    protocolMap.put(decimal, protocol);
                }
            }
            
            br.close();
        } catch (IOException e) {
            System.out.println("error reading protocol file numbers");
            e.printStackTrace();
        }
        
        return protocolMap;
    }
}
