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
    "username": "test_user__unique__",
    "userStatus": 1
]

def createWithArrayRequest = findTestObject("Object Repository/kt session/Swagger Petstore/createUsersWithArrayInput")
addHeaderConfiguration(createWithArrayRequest)
createWithArrayRequest.setBodyContent(new HttpTextBodyContent(replaceSuffixWithUUID(JsonOutput.toJson([userPayload]))))
def createWithArrayResponse = WSBuiltInKeywords.sendRequest(createWithArrayRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createWithArrayResponse, 200)

def updateUsername = userPayload["username"]
def updateRequest = findTestObject("Object Repository/kt session/Swagger Petstore/updateUser", ["username": updateUsername])
addHeaderConfiguration(updateRequest)
updateRequest.setBodyContent(new HttpTextBodyContent(replaceSuffixWithUUID(JsonOutput.toJson(userPayload))))
def updateResponse = WSBuiltInKeywords.sendRequest(updateRequest)
WSBuiltInKeywords.verifyResponseStatusCode(updateResponse, 200)

def verifyRequest = findTestObject("Object Repository/kt session/Swagger Petstore/getUserByName", ["username": updateUsername])
addHeaderConfiguration(verifyRequest)
def verifyResponse = WSBuiltInKeywords.sendRequest(verifyRequest)
WSBuiltInKeywords.verifyResponseStatusCode(verifyResponse, 404)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

