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

def petPayload = [
    "name": "doggie__unique__",
    "photoUrls": ["photoUrl1__unique__", "photoUrl2__unique__"]
]

def addPetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def addPetPayload = new HttpTextBodyContent(replaceSuffixWithUUID(JsonOutput.toJson(petPayload)))
addHeaderConfiguration(addPetRequest)
addPetRequest.setBodyContent(addPetPayload)
def addPetResponse = WSBuiltInKeywords.sendRequest(addPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(addPetResponse, 200)

def updatePetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/updatePet')
def updatePetPayload = new HttpTextBodyContent(replaceSuffixWithUUID(JsonOutput.toJson(petPayload)))
addHeaderConfiguration(updatePetRequest)
updatePetRequest.setBodyContent(updatePetPayload)
def updatePetResponse = WSBuiltInKeywords.sendRequest(updatePetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(updatePetResponse, 200)

def findPetsByStatusRequest = findTestObject('Object Repository/kt session/Swagger Petstore/findPetsByStatus')
def findPetsByStatusResponse = WSBuiltInKeywords.sendRequest(findPetsByStatusRequest)
WSBuiltInKeywords.verifyResponseStatusCode(findPetsByStatusResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

