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

def user1Data = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 1, "username": "user1__unique__", "userStatus": 1}'))
def user2Data = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 2, "username": "user2__unique__", "userStatus": 1}'))
def usersListData = new HttpTextBodyContent(replaceSuffixWithUUID('[{"id": 1, "username": "user1__unique__", "userStatus": 1}, {"id": 2, "username": "user2__unique__", "userStatus": 1}]'))

def createUser1Request = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
createUser1Request.setBodyContent(user1Data)
addHeaderConfiguration(createUser1Request)
def createUser1Response = WSBuiltInKeywords.sendRequest(createUser1Request)
WSBuiltInKeywords.verifyResponseStatusCode(createUser1Response, 200)

def createUser2Request = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
createUser2Request.setBodyContent(user2Data)
addHeaderConfiguration(createUser2Request)
def createUser2Response = WSBuiltInKeywords.sendRequest(createUser2Request)
WSBuiltInKeywords.verifyResponseStatusCode(createUser2Response, 200)

def createUsersListRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUsersWithListInput')
createUsersListRequest.setBodyContent(usersListData)
addHeaderConfiguration(createUsersListRequest)
def createUsersListResponse = WSBuiltInKeywords.sendRequest(createUsersListRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createUsersListResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

