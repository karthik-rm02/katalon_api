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

def userPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 1, "username": "test_user__unique__", "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "password": "password123", "phone": "1234567890"}'))
def createUserRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
createUserRequest.setBodyContent(userPayload)
addHeaderConfiguration(createUserRequest)
def createUserResponse = WSBuiltInKeywords.sendRequest(createUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createUserResponse, 200)

def updatePayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"userStatus": "active"}'))
def updateUserRequest = findTestObject('Object Repository/kt session/Swagger Petstore/updateUser', ['username': 'test_user__unique__'])
updateUserRequest.setBodyContent(updatePayload)
addHeaderConfiguration(updateUserRequest)
def updateUserResponse = WSBuiltInKeywords.sendRequest(updateUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(updateUserResponse, 400)

def responseStatusCode = updateUserResponse.getStatusCode()
println(responseStatusCode)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

