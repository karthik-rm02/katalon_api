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

def testObjects = [
    {
        "testObjectId": "Object Repository/kt session/Swagger Petstore/addPet",
        "endpoint": "/pet",
        "method": "post"
    },
    {
        "testObjectId": "Object Repository/kt session/Swagger Petstore/getUserByName",
        "endpoint": "/user/{username}",
        "method": "get"
    }
]

testObjects.each { testObject ->
    def request = findTestObject(testObject.testObjectId)
    addHeaderConfiguration(request)

    if (testObject.endpoint.contains("{")) {
        def variables = ["username": "testUser"]
        request = findTestObject(testObject.testObjectId, variables)
    }

    if (testObject.method == "post") {
        def requestBody = new HttpTextBodyContent(replaceSuffixWithUUID('{"id": 0, "name": "doggie", "photoUrls": ["string"], "status": "available"}'))
        request.setBodyContent(requestBody)
    }

    def response = WSBuiltInKeywords.sendRequest(request)
    WSBuiltInKeywords.verifyResponseStatusCode(response, 200)
}

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

