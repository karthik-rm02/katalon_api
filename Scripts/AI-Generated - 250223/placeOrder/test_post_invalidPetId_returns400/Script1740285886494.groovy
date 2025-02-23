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

def payload_step1 = '{"petId": 9999, "quantity": 1, "shipDate": "2022-01-01T00:00:00Z", "status": "placed", "complete": true}'
def requestObject = findTestObject('placeOrder')
def bodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(payload_step1))
requestObject.setBodyContent(bodyContent)
addHeaderConfiguration(requestObject)
def response_step1 = WSBuiltInKeywords.sendRequest(requestObject)
WSBuiltInKeywords.verifyResponseStatusCode(response_step1, 400)


def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

