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
def categoryPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(categoryPayload))
categoryRequest.setBodyContent(categoryPayloadContent)
addHeaderConfiguration(categoryRequest)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)
def categoryId = new JsonSlurper().parseText(categoryResponse.getResponseText())['id']

def petPayload = '{"name": "Test Pet", "photoUrls": ["url1", "url2"], "category": {"id": ' + categoryId + '}}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
petRequest.setBodyContent(petPayloadContent)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)
def petId = new JsonSlurper().parseText(petResponse.getResponseText())['id']

def orderPayload = '{"petId": ' + petId + ', "quantity": 1, "status": "placed", "complete": true}'
def orderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/placeOrder')
def orderPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(orderPayload))
orderRequest.setBodyContent(orderPayloadContent)
addHeaderConfiguration(orderRequest)
def orderResponse = WSBuiltInKeywords.sendRequest(orderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(orderResponse, 200)

def userPayload1 = '{"username": "testuser", "userStatus": 1}'
def userRequest1 = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
def userPayloadContent1 = new HttpTextBodyContent(replaceSuffixWithUUID(userPayload1))
userRequest1.setBodyContent(userPayloadContent1)
addHeaderConfiguration(userRequest1)
def userResponse1 = WSBuiltInKeywords.sendRequest(userRequest1)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse1, 200)

def userPayload2 = '{"username": "testuser2", "userStatus": 2}'
def userRequest2 = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
def userPayloadContent2 = new HttpTextBodyContent(replaceSuffixWithUUID(userPayload2))
userRequest2.setBodyContent(userPayloadContent2)
addHeaderConfiguration(userRequest2)
def userResponse2 = WSBuiltInKeywords.sendRequest(userRequest2)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse2, 200)

def usersPayload = '[{"username": "testuser", "userStatus": 1}, {"username": "testuser2", "userStatus": 2}]'
def usersRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUsersWithListInput')
def usersPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(usersPayload))
usersRequest.setBodyContent(usersPayloadContent)
addHeaderConfiguration(usersRequest)
def usersResponse = WSBuiltInKeywords.sendRequest(usersRequest)
WSBuiltInKeywords.verifyResponseStatusCode(usersResponse, 200)

assert usersResponse.getStatusCode() == 0

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

