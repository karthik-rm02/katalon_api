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

def categoryPayload = '{"id": 4, "name": "Fourth Category"}'
def categoryRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def categoryPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(categoryPayload))
categoryRequest.setBodyContent(categoryPayloadContent)
addHeaderConfiguration(categoryRequest)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)

def petPayload = '{"id": 3, "category": {"id": 4, "name": "Fourth Category"}, "name": "Valid Pet", "photoUrls": ["http://example.com/image.jpg"]}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
petRequest.setBodyContent(petPayloadContent)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def putPayload = '{"id": 3, "category": {"id": 4, "name": "Fourth Category"}, "name": "Valid Pet", "photoUrls": ["http://example.com/image.jpg"]}'
def putRequest = findTestObject('Object Repository/kt session/Swagger Petstore/updatePet')
def putPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(putPayload))
putRequest.setBodyContent(putPayloadContent)
addHeaderConfiguration(putRequest)
def putResponse = WSBuiltInKeywords.sendRequest(putRequest)
WSBuiltInKeywords.verifyResponseStatusCode(putResponse, 405)

println("Response status code for Step 4: ${putResponse.getStatusCode()}")

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

