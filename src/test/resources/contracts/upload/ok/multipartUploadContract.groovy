package contracts.upload.ok

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
      name("upload file ok")
      request {
        description("""
          Represents a successful scenario of POST against /uploadMultiPart to upload a file along with a String containing json
          given:
            a multipart containing some string in json format and a jar file
          when:
            calling POST against /uploadMultiPart
          then:
            receives a 200 OK with a JSON body containing the request id and a status message
          """)
        method 'POST'
        headers {
          contentType('multipart/form-data;')
        }
        url '/uploadMultiPart'
        multipart(
            uploadInformation: $(consumer(regex(nonEmpty())), producer('{"name": "anyName", "version": "1.0", "category": "aCategory"}')),
            multipartFile: named(
                // name of the file
                name: $(consumer(regex(nonEmpty())), producer('test-1.0.jar')),
                // content of the file
                content: $(consumer(regex(nonEmpty())), producer('the fake file contents'))
            )
        )
      }
      response {
        status 200
        headers {
          contentType(applicationJson())
        }
        body(
            requestId: $(consumer('eb04db2b-3bb6-4188-8c32-49a57159e51e'), producer(regex(uuid()))),
            statusMessage: 'File Upload Successful'
        )
      }
    }
]
