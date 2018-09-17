package demo.cxf.server;

import demo.cxf.HelloService;
import demo.cxf.HelloServiceImpl;
import org.apache.cxf.frontend.ServerFactoryBean;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 基于Simple，用来发布WS服务的服务器
 * WSDL 是基于 XML 的用于描述 Web Services 以及如何访问 Web Services 的语言。
 * 即将后端接口信息转换成xml给前端，前端可根据xml的信息进行调用接口方法
 */
public class SimpleServer {

    public static void main(String[] args) {
        ServerFactoryBean factory = new ServerFactoryBean();

        // 设置服务器地址
        factory.setAddress("http://localhost:8080/ws/soap/hello");

        // 设置接口
        factory.setServiceClass(HelloService.class);

        // 设置接口实现类
        factory.setServiceBean(new HelloServiceImpl());

        // 创建工厂
        factory.create();

        System.out.println("soap ws is published");

        // 查询内容在浏览器中输入 http://localhost:8080/ws/soap/hello?wsdl，内容如下：
    }

        /*
<wsdl:definitions xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:tns="http://cxf.demo/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:ns1="http://schemas.xmlsoap.org/soap/http" name="HelloServiceService" targetNamespace="http://cxf.demo/">

    // <types>：web service 使用的数据类型
    <wsdl:types>
        <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:tns="http://cxf.demo/" elementFormDefault="unqualified" targetNamespace="http://cxf.demo/" version="1.0">
            <xs:element name="say" type="tns:say"/>
            <xs:element name="sayResponse" type="tns:sayResponse"/>
        <xs:complexType name="say">
            <xs:sequence>
                <xs:element minOccurs="0" name="arg0" type="xs:string"/>
            </xs:sequence>
        </xs:complexType>
        <xs:complexType name="sayResponse">
            <xs:sequence>
                <xs:element minOccurs="0" name="return" type="xs:string"/>
            </xs:sequence>
        </xs:complexType>
        </xs:schema>
    </wsdl:types>

    // <message>：web service 使用的消息
    <wsdl:message name="say">
        <wsdl:part element="tns:say" name="parameters"> </wsdl:part>
    </wsdl:message>
    <wsdl:message name="sayResponse">
        <wsdl:part element="tns:sayResponse" name="parameters"> </wsdl:part>
    </wsdl:message>

    // 	<portType>：web service 执行的操作
    <wsdl:portType name="HelloService">
        <wsdl:operation name="say">
        <wsdl:input message="tns:say" name="say"> </wsdl:input>
        <wsdl:output message="tns:sayResponse" name="sayResponse"> </wsdl:output>
        </wsdl:operation>
    </wsdl:portType>

    // <binding>：web service 使用的通信协议
    <wsdl:binding name="HelloServiceServiceSoapBinding" type="tns:HelloServicePortType">
        <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
        <wsdl:operation name="say">
        <soap:operation soapAction="" style="document"/>
        <wsdl:input name="say">
        <soap:body use="literal"/>
        </wsdl:input>
        <wsdl:output name="sayResponse">
        <soap:body use="literal"/>
        </wsdl:output>
        </wsdl:operation>
    </wsdl:binding>

    // <service>：web service 的服务
    <wsdl:service name="HelloServiceService">
        <wsdl:port binding="tns:HelloServiceServiceSoapBinding" name="HelloServicePort">
        <soap:address location="http://localhost:8080/ws/soap/hello"/>
        </wsdl:port>
    </wsdl:service>

</wsdl:definitions>
     */

}
