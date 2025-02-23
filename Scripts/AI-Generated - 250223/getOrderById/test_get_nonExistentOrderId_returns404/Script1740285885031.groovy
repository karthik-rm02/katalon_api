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

def getOrderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/getOrderById')
def orderId = 999
def variables = ['orderId': orderId]
def getOrderResponse

addHeaderConfiguration(getOrderRequest)
getOrderResponse = WSBuiltInKeywords.sendRequest(getOrderRequest, variables)
WSBuiltInKeywords.verifyResponseStatusCode(getOrderResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

