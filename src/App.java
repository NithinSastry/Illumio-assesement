public class App {
    public static void main(String[] args) throws Exception {
        /* load protocol mapping as per https://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml */
        ProtocolData.loadData();

        /* load look up table data */
        LookUpData.loadData();

        /* start analyzing the flow logs */
        
    }
}
