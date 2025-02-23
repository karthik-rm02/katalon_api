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

def categoryPayload = '{"name": "Test Category"}'
def categoryRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def categoryBodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(categoryPayload))
categoryRequest.setBodyContent(categoryBodyContent)
addHeaderConfiguration(categoryRequest)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)

def petPayload = '{"name": "Test Pet", "photoUrls": ["url1", "url2"], "category": {"id": 1}}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petBodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
petRequest.setBodyContent(petBodyContent)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def orderPayload = '{"petId": 1, "quantity": 1, "status": "placed", "complete": true}'
def orderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/placeOrder')
def orderBodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(orderPayload))
orderRequest.setBodyContent(orderBodyContent)
addHeaderConfiguration(orderRequest)
def orderResponse = WSBuiltInKeywords.sendRequest(orderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(orderResponse, 200)

def userPayload = '{"username": "testuser", "userStatus": 1}'
def userRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
def userBodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(userPayload))
userRequest.setBodyContent(userBodyContent)
addHeaderConfiguration(userRequest)
def userResponse = WSBuiltInKeywords.sendRequest(userRequest)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse, 200)

def usersPayload = '[{"username": "testuser", "userStatus": 1}]'
def usersRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUsersWithListInput')
def usersBodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(usersPayload))
usersRequest.setBodyContent(usersBodyContent)
addHeaderConfiguration(usersRequest)
def usersResponse = WSBuiltInKeywords.sendRequest(usersRequest)
WSBuiltInKeywords.verifyResponseStatusCode(usersResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

