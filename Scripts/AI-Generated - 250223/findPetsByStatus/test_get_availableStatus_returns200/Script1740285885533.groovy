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

def category_payload = '{"id": 1, "name": "category_name__unique__"}'
def categoryRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def categoryPayload = new HttpTextBodyContent(replaceSuffixWithUUID(category_payload))
categoryRequest.setBodyContent(categoryPayload)
addHeaderConfiguration(categoryRequest)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)

def pet_payload = '{"name": "pet_name__unique__", "photoUrls": ["url1", "url2"], "category": ' + category_payload + ', "status": "available"}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petPayload = new HttpTextBodyContent(replaceSuffixWithUUID(pet_payload))
petRequest.setBodyContent(petPayload)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def params = ["status": "available"]
def findByStatusRequest = findTestObject('Object Repository/kt session/Swagger Petstore/findPetsByStatus', ["params": params])
addHeaderConfiguration(findByStatusRequest)
def findByStatusResponse = WSBuiltInKeywords.sendRequest(findByStatusRequest)
WSBuiltInKeywords.verifyResponseStatusCode(findByStatusResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

