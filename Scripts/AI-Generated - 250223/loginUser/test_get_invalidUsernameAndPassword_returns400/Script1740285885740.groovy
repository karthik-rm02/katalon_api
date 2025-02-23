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

def userPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 1, "username": "testUser__unique__", "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "password": "testPassword", "phone": "1234567890", "userStatus": 1}'))
def createUserRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
createUserRequest.setBodyContent(userPayload)
addHeaderConfiguration(createUserRequest)
def createUserResponse = WSBuiltInKeywords.sendRequest(createUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createUserResponse, 200)

def loginParams = ["username": "testUser", "password": "wrongPassword"]
def loginUserRequest = findTestObject('Object Repository/kt session/Swagger Petstore/loginUser', ["username": loginParams.username])
addHeaderConfiguration(loginUserRequest)
def loginUserResponse = WSBuiltInKeywords.sendRequest(loginUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(loginUserResponse, 400)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

