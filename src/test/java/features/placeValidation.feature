Feature: Validating place API's

  Scenario Outline: Verify if place is being successfully added using addPlaceAPI
    Given add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name       | language  | address          |
      |JM-CREATOR  | Spanish   | Villa Rio Negro  |
  #   |Green-House | English   | Barrio Guiraldes |

