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

// Step 1: Create a new Category
def categoryRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
addHeaderConfiguration(categoryRequest)
def categoryPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 1, "name": "category__unique__"}'))
categoryRequest.setBodyContent(categoryPayload)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)

// Step 2: Create a new Pet
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
addHeaderConfiguration(petRequest)
def petPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 1, "category": {"id": 1, "name": "category__unique__"}, "name": "pet__unique__", "photoUrls": ["url"]}'))
petRequest.setBodyContent(petPayload)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

// Step 3: Create a new Order
def orderRequest = findTestObject('Object Repository/kt session/Swagger Petstore/placeOrder')
addHeaderConfiguration(orderRequest)
def orderPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 1, "petId": 1, "quantity": 1, "shipDate": "2022-01-01T00:00:00.000Z", "status": "placed", "complete": true}'))
orderRequest.setBodyContent(orderPayload)
def orderResponse = WSBuiltInKeywords.sendRequest(orderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(orderResponse, 200)

// Step 4: Create a new User
def userRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
addHeaderConfiguration(userRequest)
def userPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 1, "username": "user__unique__", "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "password": "password", "phone": "1234567890", "userStatus": 1}'))
userRequest.setBodyContent(userPayload)
def userResponse = WSBuiltInKeywords.sendRequest(userRequest)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse, 200)

// Step 5: Create a list of Users
def usersListRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUsersWithListInput')
addHeaderConfiguration(usersListRequest)
def usersListPayload = new HttpTextBodyContent(replaceSuffixWithUUID('[{"id": 1, "username": "user__unique__", "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "password": "password", "phone": "1234567890", "userStatus": 1}]'))
usersListRequest.setBodyContent(usersListPayload)
def usersListResponse = WSBuiltInKeywords.sendRequest(usersListRequest)
WSBuiltInKeywords.verifyResponseStatusCode(usersListResponse, 200)

// Step 6: Verify response status code is 200
println("Step 6 - Verify Response Status Code: Status Code - 200")

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

