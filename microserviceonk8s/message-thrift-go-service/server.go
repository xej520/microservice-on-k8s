package main

import (
	"git.apache.org/thrift.git/lib/go/thrift"
	"os"
	"fmt"
	"miscroserviceonk8s/service"
	"context"
)

const (
	//0.0.0.0:9090 或者 :9090  都表示 监听所有的IP访问，不光是本机的访问
	NetworkAddr = "0.0.0.0:9090"
)

type MessageServiceImpl struct{}

func (ms *MessageServiceImpl) SendMobileMessage(ctx context.Context, mobile, message string) (r bool, err error) {
	fmt.Print("---->sendMobileMessage, mobile:\t" + mobile + ", message:\t" + message)

	//函数具体实现
	return true, nil
}

func (ms *MessageServiceImpl) SendEmailMessage(ctx context.Context, email, message string) (r bool, err error) {
	fmt.Print("===>SendEmailMessage, mobile:\t", "message:\t"+message)
	//函数具体实现
	return true, nil
}

func main() {
	transportFactory := thrift.NewTTransportFactory();

	transporFrame := thrift.NewTFramedTransportFactory(transportFactory)

	protocolFactory := thrift.NewTBinaryProtocolFactory(false, false)

	serverTransport, err := thrift.NewTServerSocket(NetworkAddr)

	if err != nil {

		fmt.Println("Error!", err)

		os.Exit(1)

	}

	handler := &MessageServiceImpl{}

	processor2 := service.NewMessageServiceProcessor(handler)
	server := thrift.NewTSimpleServer4(processor2, serverTransport, transporFrame, protocolFactory)

	fmt.Printf("--->mobile and email service has working; IP:PORT:[%s]\t", NetworkAddr)

	server.Serve()

}
