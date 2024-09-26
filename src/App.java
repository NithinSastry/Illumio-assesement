import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("invalid arguments passed. correct usage");
            System.out.println("<path to protocol number file> <path to lookup table file> <path to flow log file>");
            return;
        }
        /* load protocol mapping as per https://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml */
        Map<String, String> protocolMap = ProtocolData.loadData(args[0]);

        /* load look up table data */
        Map<String, String> lookupMap = LookUpData.loadData(args[1]);

        /* count of matches for each tag */
        Map<String, Long> tagCount = new HashMap<>(); 

        /** count of matches for port/protocol matches */
        Map<String, Long> portprotocolCount = new HashMap<>();

        /* read and start analyzing the flow logs */
        final String flowLogFilePath = args[2];
        try {
            BufferedReader br = new BufferedReader(new FileReader(flowLogFilePath));
            String line;

            while ((line = br.readLine()) != null) {
                String fields[] = line.split(" ");

                if (fields.length < 14) {
                    br.close();
                    throw new Exception("Invalid format for flow log file. atleast 14 fields expected as per example.");
                }

                String srcport = fields[5];
                String dstport = fields[6];
                String protocolNumber = fields[7];
                String protocol = protocolMap.get(protocolNumber);
                String lookupKey = dstport + " " + protocol;
                String tag = lookupMap.get(lookupKey);
                
                tagCount.put(tag, tagCount.getOrDefault(tag, 0L) + 1);
                    
                String portProtocol = srcport + "," + protocol;
                portprotocolCount.put(portProtocol, portprotocolCount.getOrDefault(portProtocol, 0L) + 1);
            }
            
            br.close();
        } catch (IOException e) {
            System.out.println("error while reading / processing flow log file.");
            e.printStackTrace();
        }

        /* Write tag count entries to file */
        try {
            final String tagCountFilePath = "./tagCount.txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(tagCountFilePath));

            bw.write("Tag,Count");
            bw.newLine();

            for (Map.Entry<String, Long> entry : tagCount.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e) {
            System.out.println("error in writing tag count outupt to file");
            e.printStackTrace();
        }
        
        /* Write tag count entries to file */
        try {
            final String portProtcolCountFilPath = "./portProtocolCount.txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(portProtcolCountFilPath));

            bw.write("Port,Protocol,Count");
            bw.newLine();

            for (Map.Entry<String, Long> entry : portprotocolCount.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e) {
            System.out.println("error in writing port protcol count outupt to file");
            e.printStackTrace();
        }
    }
}
