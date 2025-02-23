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

def categoryPayload = '{"id": 1, "name": "category__unique__"}'
def petPayload = '{"name": "pet__unique__", "photoUrls": ["url1", "url2"], "category": ' + categoryPayload + '}'

def addCategoryRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def addCategoryPayload = new HttpTextBodyContent(replaceSuffixWithUUID(categoryPayload))
addCategoryRequest.setBodyContent(addCategoryPayload)
addHeaderConfiguration(addCategoryRequest)
def addCategoryResponse = WSBuiltInKeywords.sendRequest(addCategoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(addCategoryResponse, 200)

def addPetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/addPet')
def addPetPayload = new HttpTextBodyContent(replaceSuffixWithUUID(petPayload))
addPetRequest.setBodyContent(addPetPayload)
addHeaderConfiguration(addPetRequest)
def addPetResponse = WSBuiltInKeywords.sendRequest(addPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(addPetResponse, 200)

def findByTagsRequest = findTestObject('Object Repository/kt session/Swagger Petstore/findPetsByTags')
def findByTagsResponse = WSBuiltInKeywords.sendRequest(findByTagsRequest)
WSBuiltInKeywords.verifyResponseStatusCode(findByTagsResponse, 400)

println("Test case executed successfully.")

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

