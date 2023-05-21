package coffee.lkh.robotws;

import jakarta.activation.DataHandler;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.soap.*;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

@WebService
public class FileTransferService {

    @WebMethod
    public SOAPMessage sendFile(@NotNull @XmlMimeType("application/octet-stream") byte[] remoteOctetStream) throws SOAPException, IOException {
        // Convert ByteBuffer to InputStream
        final AtomicReference<InputStream> inputStream = new AtomicReference<>(new ByteArrayInputStream(remoteOctetStream));

        // Create DataHandler from InputStream
        final DataHandler dataHandler = new DataHandler(new InputStreamDataSource(inputStream.get()));

        // Create SOAP Message with attachment
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // Add attachment to SOAP Message
        AttachmentPart attachment = soapMessage.createAttachmentPart(dataHandler);
        attachment.setContentId("file_attachment");
        soapMessage.addAttachmentPart(attachment);

        return soapMessage;
    }
}



