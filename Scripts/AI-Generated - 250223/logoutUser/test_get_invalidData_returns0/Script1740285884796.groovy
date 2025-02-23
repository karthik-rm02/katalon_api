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

def petPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"name": "doggie__unique__", "photoUrls": ["https://example.com/photo1"]}'))
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
petRequest.setBodyContent(petPayload)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def orderPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"petId": 1, "quantity": 1}'))
def orderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/placeOrder')
orderRequest.setBodyContent(orderPayload)
addHeaderConfiguration(orderRequest)
def orderResponse = WSBuiltInKeywords.sendRequest(orderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(orderResponse, 200)

def userPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "password": "password123", "phone": "1234567890", "userStatus": 1}'))
def userRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
userRequest.setBodyContent(userPayload)
addHeaderConfiguration(userRequest)
def userResponse = WSBuiltInKeywords.sendRequest(userRequest)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse, 400)

def logoutRequest = findTestObject('Object Repository/kt session/Swagger Petstore/logoutUser')
addHeaderConfiguration(logoutRequest)
def logoutResponse = WSBuiltInKeywords.sendRequest(logoutRequest)
WSBuiltInKeywords.verifyResponseStatusCode(logoutResponse, 200)

if (logoutResponse.getStatusCode() == 200) {
    println("Step 5 - Verify Status Code: PASSED")
} else {
    println("Step 5 - Verify Status Code: FAILED")
}

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

