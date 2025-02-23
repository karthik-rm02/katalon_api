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

def pet_payload = '{"name": "pet_name__unique__", "photoUrls": ["url1", "url2"], "category": ' + category_payload + ', "status": "invalid_status__unique__"}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petPayload = new HttpTextBodyContent(replaceSuffixWithUUID(pet_payload))
petRequest.setBodyContent(petPayload)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def invalid_status = "invalid_status__unique__"
def findByStatusRequest = findTestObject('Object Repository/kt session/Swagger Petstore/findPetsByStatus')
def findByStatusResponse = WSBuiltInKeywords.sendRequest(findByStatusRequest, ['status': invalid_status])
WSBuiltInKeywords.verifyResponseStatusCode(findByStatusResponse, 400)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

