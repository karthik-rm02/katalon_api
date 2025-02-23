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

def categoryRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def categoryPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"name": "TestCategory__unique__"}'))
categoryRequest.setBodyContent(categoryPayload)
addHeaderConfiguration(categoryRequest)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)

def orderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/placeOrder')
def orderPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"petId": 1__unique__, "quantity": 1, "shipDate": "2022-01-01T00:00:00Z", "status": "placed", "complete": true}'))
orderRequest.setBodyContent(orderPayload)
addHeaderConfiguration(orderRequest)
def orderResponse = WSBuiltInKeywords.sendRequest(orderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(orderResponse, 200)

def petId = new JsonSlurper().parseText(orderPayload.getText())['petId']
def uploadImageRequest = findTestObject('Object Repository/kt session/Swagger Petstore/uploadFile', ['petId': petId])
addHeaderConfiguration(uploadImageRequest)
def uploadImageResponse = WSBuiltInKeywords.sendRequest(uploadImageRequest)
WSBuiltInKeywords.verifyResponseStatusCode(uploadImageResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

