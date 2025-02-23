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

def payload = '{"category": {"id": 5, "name": "Non-existent Category__unique__"}, "name": "Invalid Pet__unique__", "photoUrls": ["http://example.com/image.jpg"]}'
def requestObject = findTestObject('updatePet')
def bodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(payload))
requestObject.setBodyContent(bodyContent)
addHeaderConfiguration(requestObject)
def response = WSBuiltInKeywords.sendRequest(requestObject)
WSBuiltInKeywords.verifyResponseStatusCode(response, 400)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

