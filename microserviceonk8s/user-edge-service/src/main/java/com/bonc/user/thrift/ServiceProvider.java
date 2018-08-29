package com.bonc.user.thrift;

import com.bonc.thrift.user.UserService;
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

    public UserService.Client getUserService() {
        TSocket socket = new TSocket(serverIp, serverPort, 3000);

//         定义传输层
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
//        定义协议层
        TProtocol protocol = new TBinaryProtocol(transport);

//        用户业务核心代码
        UserService.Client client = new UserService.Client(protocol);

        return client;

    }


}
