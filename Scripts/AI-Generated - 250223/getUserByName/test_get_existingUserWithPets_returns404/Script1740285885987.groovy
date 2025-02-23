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

def userPayload = '''
{
    "username": "userWithPets__unique__",
    "firstName": "John",
    "lastName": "Doe",
    "email": "johndoe@example.com",
    "password": "password123",
    "phone": "1234567890",
    "userStatus": 1
}
'''

def petPayload = '''
{
    "name": "Fluffy",
    "photoUrls": ["https://example.com/fluffy.jpg"]
}
'''

def createUserRequest = findTestObject('Object Repository/kt session/Swagger Petstore/createUser')
def createUserPayload = new HttpTextBodyContent(replaceSuffixWithUUID(userPayload))
createUserRequest.setBodyContent(createUserPayload)
addHeaderConfiguration(createUserRequest)
def createUserResponse = WSBuiltInKeywords.sendRequest(createUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createUserResponse, 200)

def createPetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def createPetPayload = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
createPetRequest.setBodyContent(createPetPayload)
addHeaderConfiguration(createPetRequest)
def createPetResponse = WSBuiltInKeywords.sendRequest(createPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createPetResponse, 200)

def getUserRequest = findTestObject('Object Repository/kt session/Swagger Petstore/getUserByName', ['username': 'userWithPets__unique__'])
addHeaderConfiguration(getUserRequest)
def getUserResponse = WSBuiltInKeywords.sendRequest(getUserRequest)
WSBuiltInKeywords.verifyResponseStatusCode(getUserResponse, 404)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

