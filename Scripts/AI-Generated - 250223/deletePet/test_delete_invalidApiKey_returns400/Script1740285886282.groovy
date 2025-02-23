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
addHeaderConfiguration(createPetRequest)
def createPetPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"name": "doggie__unique__", "photoUrls": ["photoUrl1__unique__", "photoUrl2__unique__"]}'))
createPetRequest.setBodyContent(createPetPayload)
def createPetResponse = WSBuiltInKeywords.sendRequest(createPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(createPetResponse, 200)

def pet_id = new JsonSlurper().parseText(createPetResponse.getResponseText())['id']

def deletePetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/deletePet', ['petId': pet_id])
addHeaderConfiguration(deletePetRequest)
def deletePetResponse = WSBuiltInKeywords.sendRequest(deletePetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(deletePetResponse, 400)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

