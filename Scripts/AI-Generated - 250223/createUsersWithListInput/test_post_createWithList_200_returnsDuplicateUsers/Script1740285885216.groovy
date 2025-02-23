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

def userPayload = [
    id: 1,
    username: "user__unique__",
    userStatus: 1
]

def createNewUserRequest = findTestObject("Object Repository/kt session/Swagger Petstore/createUser")
def createNewUserPayload = new HttpTextBodyContent(replaceSuffixWithUUID(JsonOutput.toJson(userPayload)))
createNewUserRequest.setBodyContent(createNewUserPayload)
addHeaderConfiguration(createNewUserRequest)
def createNewUserResponse = WSBuiltInKeywords.sendRequest(createNewUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createNewUserResponse, 200)

def usersListPayload = [userPayload]
def createUsersListRequest = findTestObject("Object Repository/kt session/Swagger Petstore/createUsersWithListInput")
def createUsersListPayload = new HttpTextBodyContent(replaceSuffixWithUUID(JsonOutput.toJson(usersListPayload)))
createUsersListRequest.setBodyContent(createUsersListPayload)
addHeaderConfiguration(createUsersListRequest)
def createUsersListResponse = WSBuiltInKeywords.sendRequest(createUsersListRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createUsersListResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

