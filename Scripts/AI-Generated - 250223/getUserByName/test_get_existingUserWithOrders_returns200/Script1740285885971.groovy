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

def userPayload = '{"username": "userWithOrders__unique__", "firstName": "John", "lastName": "Doe", "email": "johndoe@example.com", "password": "password123", "phone": "1234567890", "userStatus": 1}'
def orderPayload = '{"petId": 1, "quantity": 1, "shipDate": "2022-12-31T23:59:59Z", "status": "placed", "complete": true}'

def createUserRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
def createOrderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/placeOrder')
def getUserRequest = findTestObject('Object Repository/kt session/Swagger Petstore/getUserByName', ['username': 'userWithOrders'])

def createUserPayload = new HttpTextBodyContent(replaceSuffixWithUUID(userPayload))
def createOrderPayload = new HttpTextBodyContent(replaceSuffixWithUUID(orderPayload))

addHeaderConfiguration(createUserRequest)
addHeaderConfiguration(createOrderRequest)
addHeaderConfiguration(getUserRequest)

createUserRequest.setBodyContent(createUserPayload)
createOrderRequest.setBodyContent(createOrderPayload)

def createUserResponse = WSBuiltInKeywords.sendRequest(createUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createUserResponse, 200)

def createOrderResponse = WSBuiltInKeywords.sendRequest(createOrderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createOrderResponse, 200)

def getUserResponse = WSBuiltInKeywords.sendRequest(getUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(getUserResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

