Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if place being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"
    Examples:
        | name | language | address |
        | Om   |  Marathi | Shirdi  |
#        | Sai  |  hindi   | Bhokar  |
#        | Ram  | English  | Pune    |

  @DeletePlace
  Scenario: Verify if Delete Place Functionality is working
    Given DeletePlace Payload
    When User calls "DeletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"