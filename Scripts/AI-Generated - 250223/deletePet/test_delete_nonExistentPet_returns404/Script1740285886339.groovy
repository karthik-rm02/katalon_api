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

def newPetPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"name": "Fluffy__unique__", "photoUrls": ["https://example.com/photo1"]}'))
def addPetRequest = findTestObject('addPet')
addPetRequest.setBodyContent(newPetPayload)
addHeaderConfiguration(addPetRequest)
def addPetResponse = WSBuiltInKeywords.sendRequest(addPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(addPetResponse, 200)

def petId = new JsonSlurper().parseText(addPetResponse.getResponseText())['id']

def getPetRequest = findTestObject('getPetById', ['petId': petId])
addHeaderConfiguration(getPetRequest)
def getPetResponse = WSBuiltInKeywords.sendRequest(getPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(getPetResponse, 200)

def deletePetRequest = findTestObject('deletePet', ['petId': petId])
addHeaderConfiguration(deletePetRequest)
def deletePetResponse = WSBuiltInKeywords.sendRequest(deletePetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(deletePetResponse, 200)

def deletePetRequest2 = findTestObject('deletePet', ['petId': petId])
addHeaderConfiguration(deletePetRequest2)
def deletePetResponse2 = WSBuiltInKeywords.sendRequest(deletePetRequest2)
WSBuiltInKeywords.verifyResponseStatusCode(deletePetResponse2, 404)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

