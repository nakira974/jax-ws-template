package coffee.lkh.robotws;

import jakarta.xml.ws.Endpoint;

public class WebServicesPublisher {
    public static void main(String[] args) throws Exception {
        Endpoint.publish("http://localhost:8080/robotws/services/FileTransferService", new FileTransferService());
        System.out.println("Web service published successfully.");
    }
}
