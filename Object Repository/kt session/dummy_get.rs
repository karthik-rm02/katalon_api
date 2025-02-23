<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>dummy_get</name>
   <tag></tag>
   <elementGuidId>57d2555f-e5ff-44ec-84b1-34a485ca1cb7</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <katalonVersion>10.1.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${host}/api/users?page=${page}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>4</defaultValue>
      <description></description>
      <id>144c4f0e-e83c-4e6d-a0c7-a29d6c235e20</id>
      <masked>false</masked>
      <name>page</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.host</defaultValue>
      <description></description>
      <id>7d930529-9091-4f65-919a-1795c5e3c56c</id>
      <masked>false</masked>
      <name>host</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()



jsonRes = new JsonSlurper().parseText(response.getResponseBodyContent())
//assertThat(jsonRes.page).isEqualTo(4)
//assertThat(response.getHeaderFields().get('Content-Type')[0]).toString().isEqualTo('application/json')


//assertThat(response.getHeaderFields().get('Content-Type').toString()).isEqualTo('[application/json;charset=UTF-8]')

assertThat(response.getHeaderFields().containsKey('Content-Type')).isTrue()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
