<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>uploadFile</name>
   <tag></tag>
   <elementGuidId>7bd31e7c-4dc8-4422-a368-f86a9fbb590a</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;parameters&quot;: [
    {
      &quot;name&quot;: &quot;additionalMetadata&quot;,
      &quot;value&quot;: &quot;&quot;,
      &quot;type&quot;: &quot;string&quot;,
      &quot;contentType&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;file&quot;,
      &quot;value&quot;: &quot;&quot;,
      &quot;type&quot;: &quot;file&quot;,
      &quot;contentType&quot;: &quot;&quot;
    }
  ]
}</httpBodyContent>
   <httpBodyType>form-data</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>12e24256-0ab3-43c1-b5ad-605041419d1c</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Accept</name>
      <type>Main</type>
      <value>multipart/form-data</value>
      <webElementGuid>ea375688-26ca-4166-b6ad-14c8854efe1b</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>10.1.0</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path>/pet/{petId}/uploadImage</path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://petstore.swagger.io/v2/pet/${petId}${/uploadImage}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>7fffd788-b1ba-44d9-b81a-69b34334305b</id>
      <masked>false</masked>
      <name>petId</name>
   </variables>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
