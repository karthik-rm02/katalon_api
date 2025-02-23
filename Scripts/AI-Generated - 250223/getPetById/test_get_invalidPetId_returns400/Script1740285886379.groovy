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

def invalid_pet_id = 999999

def findPetRequest = findTestObject('Object Repository/kt session/Swagger Petstore/getPetById', ['petId': invalid_pet_id])
addHeaderConfiguration(findPetRequest)
def findPetResponse = WSBuiltInKeywords.sendRequest(findPetRequest)
WSBuiltInKeywords.verifyResponseStatusCode(findPetResponse, 400)

println("Step 2 - Verify the response status code is 400: ${findPetResponse.getStatusCode()}")

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

