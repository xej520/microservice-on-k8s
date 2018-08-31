package main

import (
	"git.apache.org/thrift.git/lib/go/thrift"
	"os"
	"fmt"
	"miscroserviceonk8s/service"
	"context"
)

const (
	NetworkAddr = "localhost:9090"
)

type MessageServiceImpl struct{}

func (ms *MessageServiceImpl) SendMobileMessage(ctx context.Context, mobile, message string) (r bool, err error) {
	fmt.Print("sendMobileMessage, mobile:"+mobile+", message:"+message)

	//函数具体实现
	return true, nil
}

func (ms *MessageServiceImpl) SendEmailMessage(ctx context.Context, email, message string) (r bool, err error) {
	//函数具体实现
	return false, nil
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
