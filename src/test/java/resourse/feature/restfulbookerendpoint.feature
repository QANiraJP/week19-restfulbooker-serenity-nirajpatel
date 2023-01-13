Feature: Testing different request on the Restful Booker
  As I user I want to send GET, POST, PUT, and Delete requests

  Scenario: Check if user can access all booking Ids
    When I send a GET request to booking endpoint
    Then I must get back a valid response code 200

  Scenario Outline: Check if user can create a new booking
    When I send a POST request to create a new booking with firstName"<firstName>", lastName"<lastName>", totalprice "<totalprice>", depositpaid "<depositpaid>", checkin "<checkin>", checkout"<checkout>" additionalneeds "<additionalneeds>"
    Then I must get back a valid response code 201
    And  I verify that new booking is created by <"id">
    Examples:
      | firstName | lastName | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Abc       | Xyz      | 150        | yes         | 2023-01-15 | 2023-11-05 | Dinner          |

  Scenario: Check if user can update an booking
    When I send Put request with  firstName"<firstName>", lastName"<lastName>", totalprice "<totalprice>", depositpaid "<depositpaid>", checkin "<checkin>", checkout"<checkout>" additionalneeds "<additionalneeds>"
    Then I must get back a valid response code 200

    Scenario: Check if user can delete an booking
      When I send delete requested id
      Then I should see the response code 201
