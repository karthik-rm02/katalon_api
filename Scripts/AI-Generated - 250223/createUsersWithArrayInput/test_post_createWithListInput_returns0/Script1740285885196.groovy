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

def categoryPayload = '{"name": "Test Category__unique__"}'
def categoryRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def categoryPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(categoryPayload))
categoryRequest.setBodyContent(categoryPayloadContent)
addHeaderConfiguration(categoryRequest)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)

def petPayload = '{"name": "Test Pet__unique__", "photoUrls": ["url1", "url2"], "category": {"id": 1, "name": "Test Category__unique__"}}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
petRequest.setBodyContent(petPayloadContent)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def orderPayload = '{"petId": 1, "quantity": 1, "status": "placed", "complete": true}'
def orderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/placeOrder')
def orderPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(orderPayload))
orderRequest.setBodyContent(orderPayloadContent)
addHeaderConfiguration(orderRequest)
def orderResponse = WSBuiltInKeywords.sendRequest(orderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(orderResponse, 200)

def userPayload = '{"username": "testuser", "userStatus": 1}'
def userRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
def userPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(userPayload))
userRequest.setBodyContent(userPayloadContent)
addHeaderConfiguration(userRequest)
def userResponse = WSBuiltInKeywords.sendRequest(userRequest)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse, 200)

def usersListPayload = '[${userPayload}]'
def usersListRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUsersWithListInput')
def usersListPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(usersListPayload))
usersListRequest.setBodyContent(usersListPayloadContent)
addHeaderConfiguration(usersListRequest)
def usersListResponse = WSBuiltInKeywords.sendRequest(usersListRequest)
WSBuiltInKeywords.verifyResponseStatusCode(usersListResponse, 200)

assert usersListResponse.getStatusCode() == 0

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

