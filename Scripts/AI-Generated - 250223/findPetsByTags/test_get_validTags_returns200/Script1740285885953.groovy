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

def petPayload = '{"name": "pet_name__unique__", "photoUrls": ["url1", "url2"], "category": {"id": 1, "name": "category_name__unique__"}}'
def petRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def petPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
petRequest.setBodyContent(petPayloadContent)
addHeaderConfiguration(petRequest)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

def tagsPayload = '["tag1", "tag2", "tag3"]'
def tagsRequest = findTestObject('Object Repository/kt session/Swagger Petstore/findPetsByTags')
def tagsPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(tagsPayload))
tagsRequest.setBodyContent(tagsPayloadContent)
addHeaderConfiguration(tagsRequest)
def tagsResponse = WSBuiltInKeywords.sendRequest(tagsRequest)
WSBuiltInKeywords.verifyResponseStatusCode(tagsResponse, 200)

def tagsQueryParam = "tag1,tag2,tag3"
def findByTagsRequest = findTestObject('Object Repository/kt session/Swagger Petstore/findPetsByTags')
def findByTagsResponse = WSBuiltInKeywords.sendRequest(findByTagsRequest)
WSBuiltInKeywords.verifyResponseStatusCode(findByTagsResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

