import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class LookUpData {
    
    public static Map<String, String> loadData() {
        Map<String, String> lookupTable = new HashMap<>();
        
        try {
            final String filePath = "/home/ns/Illumio-assesement/lookup_table.csv";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            
            br.readLine();
            while ((line = br.readLine()) != null) {
                String fields[] = line.split(",");
                
                if (fields.length != 3) {
                    br.close();
                    throw new Exception("Incorrect format for look up table. 3 columns required");
                }

                String dstport = fields[0];
                String protocol = fields[1];
                String tag = fields[2];
                String key = dstport + " " + protocol;

                lookupTable.put(key, tag);
            }
            
            br.close();
        } catch (Exception e) {
            System.out.println("error reading lookup table file");
            e.printStackTrace();
        }
        
        for (Map.Entry<String, String> entry : lookupTable.entrySet()) {
            System.out.println("key : " + entry.getKey() + " " + "value : "  + entry.getValue());
        }

        return lookupTable;
    }
}