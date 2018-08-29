package com.bonc.user.thrift;

import com.bonc.thrift.message.MessageService;
import com.bonc.thrift.user.UserService;
import com.sun.org.apache.regexp.internal.RESyntaxException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {
    @Value("${thrift.user.ip}")
    private String serverIp;
    @Value("${thrift.user.port}")
    private int serverPort;

    @Value("${thrift.message.ip}")
    private String messageServerIp;
    @Value("${thrift.message.port}")
    private int messageServerPort;


    public UserService.Client getUserService() {

        return getService(serverIp, serverPort, ServiceType.USER);
    }

    public MessageService.Client getMessasgeService() {
        return getService(messageServerIp, messageServerPort, ServiceType.MESSAGE);
    }

    public <T> T getService(String ip, int port, ServiceType serviceType) {
        TSocket socket = new TSocket(ip, port, 3000);
//         定义传输层
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
//        定义协议层
        TProtocol protocol = new TBinaryProtocol(transport);
        TServiceClient result = null;
        switch (serviceType) {
            // 用户业务核心代码
            case USER:
                result = new UserService.Client(protocol);
                System.out.println("-----------------5------------------->result:\t"+result);
                break;
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
        }

        return (T)result;

    }

    private enum ServiceType{
        USER,
        MESSAGE
    }



}
