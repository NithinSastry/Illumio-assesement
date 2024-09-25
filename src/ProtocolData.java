import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProtocolData {
    
    public static Map<String, String> loadData() {
        Map<String, String> protocolMap = new HashMap<>();
        
        try {
            final String filePath = "/home/ns/Illumio-assesement/protocol-numbers-1.csv";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            
            br.readLine();
            while ((line = br.readLine()) != null) {
                String fields[] = line.split(",");

                if (fields.length == 1) {
                    protocolMap.put(fields[0], "");
                }
                else {
                    protocolMap.put(fields[0], fields[1]);
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
