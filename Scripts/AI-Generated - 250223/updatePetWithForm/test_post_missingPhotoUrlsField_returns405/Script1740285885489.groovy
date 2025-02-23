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

def categoryPayload = '{"id": 1, "name": "category_name__unique__"}'
def categoryRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def categoryPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(categoryPayload))
categoryRequest.setBodyContent(categoryPayloadContent)
addHeaderConfiguration(categoryRequest)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)

def categoryId = new JsonSlurper().parseText(categoryResponse.getResponseText())['id']

def petPayload = '{"name": "pet_name__unique__", "photoUrls": ["photo_url"], "category": {"id": ' + categoryId + ', "name": "category_name__unique__"}}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
petRequest.setBodyContent(petPayloadContent)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def petId = new JsonSlurper().parseText(petResponse.getResponseText())['id']

def petPayloadMissingPhotoUrls = '{"name": "pet_name__unique__", "category": {"id": ' + categoryId + ', "name": "category_name__unique__"}}'
def petRequestMissingPhotoUrls = findTestObject('Object Repository/kt session/Swagger Petstore/updatePet')
def petPayloadMissingPhotoUrlsContent = new HttpTextBodyContent(replaceSuffixWithUUID(petPayloadMissingPhotoUrls))
petRequestMissingPhotoUrls.setBodyContent(petPayloadMissingPhotoUrlsContent)
addHeaderConfiguration(petRequestMissingPhotoUrls)
def petResponseMissingPhotoUrls = WSBuiltInKeywords.sendRequest(petRequestMissingPhotoUrls)
WSBuiltInKeywords.verifyResponseStatusCode(petResponseMissingPhotoUrls, 405)

println petResponseMissingPhotoUrls.getStatusCode()

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

