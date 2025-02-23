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

def createPetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def createPetPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"name": "Fluffy__unique__", "photoUrls": ["https://example.com/photo1"]}'))
createPetRequest.setBodyContent(createPetPayload)
addHeaderConfiguration(createPetRequest)
def createPetResponse = WSBuiltInKeywords.sendRequest(createPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createPetResponse, 200)
def petId = new JsonSlurper().parseText(createPetResponse.getResponseText())['id']

def getPetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/getPetById', ['petId': petId])
addHeaderConfiguration(getPetRequest)
def getPetResponse = WSBuiltInKeywords.sendRequest(getPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(getPetResponse, 200)

def deletePetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/deletePet', ['petId': petId])
addHeaderConfiguration(deletePetRequest)
def deletePetResponse = WSBuiltInKeywords.sendRequest(deletePetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(deletePetResponse, 200)

def verifyDeleteRequest = findTestObject('Object Repository/kt session/Swagger Petstore/deletePet', ['petId': petId])
addHeaderConfiguration(verifyDeleteRequest)
def verifyDeleteResponse = WSBuiltInKeywords.sendRequest(verifyDeleteRequest)
WSBuiltInKeywords.verifyResponseStatusCode(verifyDeleteResponse, 404)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

