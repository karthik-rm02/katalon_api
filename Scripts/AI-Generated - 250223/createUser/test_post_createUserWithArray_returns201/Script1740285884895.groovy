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

def petPayload = '{"name": "doggie__unique__", "photoUrls": ["photo_url1", "photo_url2"]}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petBodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
petRequest.setBodyContent(petBodyContent)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def petId = new JsonSlurper().parseText(petResponse.getResponseText())['id']

def orderPayload = '{"petId": ' + petId + ', "quantity": 1}'
def orderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/placeOrder')
def orderBodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(orderPayload))
orderRequest.setBodyContent(orderBodyContent)
addHeaderConfiguration(orderRequest)
def orderResponse = WSBuiltInKeywords.sendRequest(orderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(orderResponse, 200)

def userPayload = '{"username": "user1__unique__", "userStatus": 1}'
def userRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUsersWithArrayInput')
def userBodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(userPayload))
userRequest.setBodyContent(userBodyContent)
addHeaderConfiguration(userRequest)
def userResponse = WSBuiltInKeywords.sendRequest(userRequest)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse, 201)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

