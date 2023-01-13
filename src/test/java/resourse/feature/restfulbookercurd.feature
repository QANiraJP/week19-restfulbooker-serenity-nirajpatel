Feature: Restful Booker
  As a user I want to test Restful Booker Application

  Scenario Outline: CRUD Test
    When I send POST request to create a new booking with firstName"<firstName>", lastName"<lastName>", totalprice "<totalprice>", depositpaid "<depositpaid>", checkin "<checkin>", checkout"<checkout>" additionalneeds "<additionalneeds>"
    Then I verify that new booking is created by id
    And  I send a Put request with  firstName"<firstName>", lastName"<lastName>", totalprice "<totalprice>", depositpaid "<depositpaid>", checkin "<checkin>", checkout"<checkout>" additionalneeds "<additionalneeds>"
    And  The totalprice "<totalprice>" is successfully updated
    And  I send delete booking by id
    Then The booking is successfully deleted from the record
    Examples:
      | firstName | lastName | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Abc       | Xyz      | 550        | yes         | 2023-02-18 | 2023-06-05 | Breakfast       |
      | Mno       | Pqr      | 700        | No          | 2023-05-05 | 2023-12-20 | Dinner          |



