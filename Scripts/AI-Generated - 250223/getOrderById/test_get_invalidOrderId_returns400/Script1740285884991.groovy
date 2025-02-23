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

def getOrderByIdRequest = findTestObject('Object Repository/kt session/Swagger Petstore/getOrderById')
def orderId = 0 // Set the order ID here
def variables = ['orderId': orderId]
def getOrderByIdResponse = WSBuiltInKeywords.sendRequest(findTestObject('Object Repository/kt session/Swagger Petstore/getOrderById', variables))
WSBuiltInKeywords.verifyResponseStatusCode(getOrderByIdResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

