import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def addHeaderConfiguration(request) {
    def content_type_header = new TestObjectProperty("content-type", ConditionType.EQUALS, "application/json")
    request.getHttpHeaderProperties().add(content_type_header)
}

uuid = UUID.randomUUID().toString()

def orderPayload = '{"petId": 1, "quantity": 1, "shipDate": "2022-01-01T00:00:00Z", "status": "invalid_status", "complete": true}'

def placeOrderRequest = findTestObject('placeOrder')
def placeOrderPayload = new HttpTextBodyContent(replaceSuffixWithUUID(orderPayload))
placeOrderRequest.setBodyContent(placeOrderPayload)
addHeaderConfiguration(placeOrderRequest)
def placeOrderResponse = WSBuiltInKeywords.sendRequest(placeOrderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(placeOrderResponse, 400)

println("Step 2 - Verify response status code: " + placeOrderResponse.getStatusCode())

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

