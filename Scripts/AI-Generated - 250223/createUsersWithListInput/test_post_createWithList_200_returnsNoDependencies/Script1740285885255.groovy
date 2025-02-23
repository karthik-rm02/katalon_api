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

def user1Request = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
def user1Payload = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 1, "username": "user1__unique__", "userStatus": 1}'))
user1Request.setBodyContent(user1Payload)
addHeaderConfiguration(user1Request)
def user1Response = WSBuiltInKeywords.sendRequest(user1Request)
WSBuiltInKeywords.verifyResponseStatusCode(user1Response, 200)

def usersListRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUsersWithListInput')
def usersListPayload = new HttpTextBodyContent(replaceSuffixWithUUID('[{"id": 2, "username": "user2__unique__", "userStatus": 2}, {"id": 3, "username": "user3__unique__", "userStatus": 3}]'))
usersListRequest.setBodyContent(usersListPayload)
addHeaderConfiguration(usersListRequest)
def usersListResponse = WSBuiltInKeywords.sendRequest(usersListRequest)
WSBuiltInKeywords.verifyResponseStatusCode(usersListResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

