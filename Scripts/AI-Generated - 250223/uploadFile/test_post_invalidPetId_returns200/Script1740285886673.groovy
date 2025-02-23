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

// Step 1: Create a new Category
def categoryRequest = findTestObject("Object Repository/kt session/Swagger Petstore/addPet")
addHeaderConfiguration(categoryRequest)
def categoryPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"name": "category_name__unique__"}'))
categoryRequest.setBodyContent(categoryPayload)
def categoryResponse = WSBuiltInKeywords.sendRequest(categoryRequest)
WSBuiltInKeywords.verifyResponseStatusCode(categoryResponse, 200)

// Step 2: Create a new Pet
def petRequest = findTestObject("Object Repository/kt session/Swagger Petstore/addPet")
addHeaderConfiguration(petRequest)
def petPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"name": "pet_name__unique__", "photoUrls": ["photo_url__unique__"], "category": {"id": 1, "name": "category_name__unique__"}}'))
petRequest.setBodyContent(petPayload)
def petResponse = WSBuiltInKeywords.sendRequest(petRequest)
WSBuiltInKeywords.verifyResponseStatusCode(petResponse, 200)

// Step 3: Create a new Order
def orderRequest = findTestObject("Object Repository/kt session/Swagger Petstore/placeOrder")
addHeaderConfiguration(orderRequest)
def orderPayload = new HttpTextBodyContent(replaceSuffixWithUUID('{"petId": 1, "quantity": 1, "shipDate": "2022-01-01T00:00:00Z", "status": "placed", "complete": true}'))
orderRequest.setBodyContent(orderPayload)
def orderResponse = WSBuiltInKeywords.sendRequest(orderRequest)
WSBuiltInKeywords.verifyResponseStatusCode(orderResponse, 200)

// Step 4: Make a POST request to /pet/{invalidPetId}/uploadImage with an invalid petId
def invalidPetId = 999999
def uploadImageRequest = findTestObject("Object Repository/kt session/Swagger Petstore/uploadFile", ["petId": invalidPetId])
addHeaderConfiguration(uploadImageRequest)
def uploadImageResponse = WSBuiltInKeywords.sendRequest(uploadImageRequest)
WSBuiltInKeywords.verifyResponseStatusCode(uploadImageResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

